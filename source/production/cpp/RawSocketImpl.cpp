/*
 * Copyright 2010-2011 Nick Williams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Some parts Copyright 2004-2007 Daniel F. Savarese and
 *     Copyright 2007-2009 Savarese Software Research Corporation
 *     (used with permission under Apache License, Version 2.0)
 */

#include <errno.h>
#include <string>
#include <cstring>

#if _WIN32

#include <winsock2.h>
#include <ws2tcpip.h>

#ifndef close
#define close(fd) closesocket(fd)
#endif

#else /* if defined(_WIN32) */

#include <netdb.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
#include <sys/time.h>

#endif /* if defined(_WIN32) else */

#ifndef SOCKET_ERROR
#define SOCKET_ERROR -1
#endif

#include "io_oddsource_java_net_socket_RawSocketImpl.h"

static void raiseError(JNIEnv * environment, std::string error, std::string message);
static void handleSocketError(JNIEnv *environment);
static int setIntegerSocketOption(int socket, int level, int option, int value);
static int getIntegerSocketOption(int socket, int level, int option);
static int setTimeout(int socket, int option, int timeout);
static int getTimeout(int socket, int option);
static struct sockaddr* initIPv4SocketAddress(JNIEnv *environment, struct sockaddr_in *sin, jbyteArray address);
static struct sockaddr* initIPv6SocketAddress(JNIEnv *environment, struct sockaddr_in6 *sin6, jbyteArray address);

/*
 * Java Methods for class io.oddsource.java.net.socket.RawSocketImpl
 */

JNIEXPORT void JNICALL Java_io_oddsource_java_net_socket_RawSocketImpl_nativeStaticInitialize(JNIEnv *, jclass)
{
#if defined(_WIN32)
    WORD version = MAKEWORD(2, 0);
    WSADATA data;
    errno = WSAStartup(version, &data);
#endif
}

JNIEXPORT void JNICALL Java_io_oddsource_java_net_socket_RawSocketImpl_nativeStaticShutdown(JNIEnv *, jclass)
{
#if defined(_WIN32)
    WSACleanup();
#endif
}

JNIEXPORT void JNICALL Java_io_oddsource_java_net_socket_RawSocketImpl_setSocketOption
    (JNIEnv *environment, jobject, jint socket, jint level, jint option, jint value)
{
    int result = setIntegerSocketOption(socket, level, option, value);

    if(result == SOCKET_ERROR)
        handleSocketError(environment);
}

JNIEXPORT jint JNICALL Java_io_oddsource_java_net_socket_RawSocketImpl_getSocketOption
    (JNIEnv *environment, jobject, jint socket, jint level, jint option)
{
    int result = getIntegerSocketOption(socket, level, option);

    if(result == SOCKET_ERROR)
        handleSocketError(environment);

    return result;
}

JNIEXPORT void JNICALL Java_io_oddsource_java_net_socket_RawSocketImpl_setTimeout
    (JNIEnv *environment, jobject, jint socket, jint option, jint timeoutValue)
{
    int result = setTimeout(socket, option, timeoutValue);

    if(result == SOCKET_ERROR)
        handleSocketError(environment);
}

JNIEXPORT jint JNICALL Java_io_oddsource_java_net_socket_RawSocketImpl_getTimeout
    (JNIEnv *environment, jobject, jint socket, jint option)
{
    int result = getTimeout(socket, option);

    if(result == SOCKET_ERROR)
        handleSocketError(environment);

    return result;
}

/*
 * Utility Functions
 */

static void raiseError(JNIEnv * environment, std::string error, std::string message)
{
    if(error.empty() || error.length() == 0)
        error = "io/oddsource/java/net/socket/exception/RawSocketException";

    if(message.empty())
        message = "";

    environment->ThrowNew(
        environment->FindClass(error.c_str()),
        message.c_str()
    );
}

static void handleSocketError(JNIEnv *environment)
{
    if(errno)
    {
        char * message = NULL;

#if defined(_WIN32)
        int formatted = FormatMessage(
            FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            errno,
            MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
            (LPTSTR) &message,
            0,
            NULL
        );

        if(!formatted)
            message = strerror(errno);
#else
        message = strerror(errno);
#endif

        std::string messageString(message);

#if defined(_WIN32)
        if(formatted)
            LocalFree(message);
#endif

        raiseError(environment, NULL, messageString);
    }
}

static int setIntegerSocketOption(int socket, int level, int option, int value)
{
    return setsockopt(socket, level, option, (void*)&value, sizeof(value));
}

static int getIntegerSocketOption(int socket, int level, int option)
{
    int value  = -1;
    socklen_t size   = sizeof(value);
    int result = getsockopt(socket, level, option, (void*)&value, &size);

    if(result < 0)
        return result;

    return value;
}


static int setTimeout(int socket, int option, int timeout)
{
#if defined(_WIN32)
    return setIntegerSocketOption(socket, SOL_SOCKET, option, timeout);
#else
    int seconds;
    struct timeval value;

    seconds = timeout / 1000;

    if(seconds > 0)
        timeout -= (seconds*1000);

    value.tv_sec  = seconds;
    value.tv_usec = timeout * 1000;

    return setsockopt(socket, SOL_SOCKET, option, (void*)&value, sizeof(value));
#endif
}


static int getTimeout(int socket, int option)
{
    int result;
    struct timeval value;
    socklen_t size = sizeof(value);

    result = getsockopt(socket, SOL_SOCKET, option, (void*)&value, &size);

    if(result < 0)
        return result;

    return (value.tv_sec * 1000 + value.tv_usec / 1000);
}

static struct sockaddr* initIPv4SocketAddress(JNIEnv *environment, struct sockaddr_in *sin, jbyteArray address)
{
    jbyte *buf;

    memset(sin, 0, sizeof(struct sockaddr_in));
    sin->sin_family = PF_INET;
    buf = environment->GetByteArrayElements(address, NULL);
    memcpy(&sin->sin_addr, buf, sizeof(sin->sin_addr));
    environment->ReleaseByteArrayElements(address, buf, JNI_ABORT);

    return (struct sockaddr *)sin;
}


static struct sockaddr* initIPv6SocketAddress(JNIEnv *environment, struct sockaddr_in6 *sin6, jbyteArray address)
{
    jbyte *buf;

    memset(sin6, 0, sizeof(struct sockaddr_in6));
    sin6->sin6_family = PF_INET6;
    buf = environment->GetByteArrayElements(address, NULL);
    memcpy(&sin6->sin6_addr, buf, sizeof(sin6->sin6_addr));
    environment->ReleaseByteArrayElements(address, buf, JNI_ABORT);

    return (struct sockaddr *)sin6;
}

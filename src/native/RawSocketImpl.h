/*
 * RawSocketImpl.h from RawSockets modified Wednesday, June 29, 2011 10:58:53 CDT (-0500).
 *
 * Some parts Copyright 2004-2007 Daniel F. Savarese and
 *     Copyright 2007-2009 Savarese Software Research Corporation
 *     (used with permission under Apache License, Version 2.0)
 *
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

#ifndef __RAWSOCKETS_RAW_SOCKET_IMPL_H
#define __RAWSOCKETS_RAW_SOCKET_IMPL_H

#include <jni.h>
#include <string>

/*
 * Utility Functions
 */

static void raiseError(JNIEnv *, std::string, std::string);
static void handleSocketError(JNIEnv *);
static int setIntegerSocketOption(int socket, int level, int option, int value);
static int getIntegerSocketOption(int socket, int level, int option);
static int setTimeout(int socket, int option, int timeout);
static int getTimeout(int socket, int option);
static struct sockaddr* initIPv4SocketAddress(JNIEnv *, struct sockaddr_in *, jbyteArray);
static struct sockaddr* initIPv6SocketAddress(JNIEnv *, struct sockaddr_in6 *, jbyteArray);

/*
 * Java Methods for class net.nicholaswilliams.java.net.socket.RawSocketImpl
 */

#ifdef __cplusplus
extern "C" {
#endif /* ifdef __cplusplus */

JNIEXPORT void JNICALL Java_net_nicholaswilliams_java_net_socket_RawSocketImpl_nativeStaticInitialize
	(JNIEnv *, jclass);

JNIEXPORT void JNICALL Java_net_nicholaswilliams_java_net_socket_RawSocketImpl_nativeStaticShutdown
	(JNIEnv *, jclass);

JNIEXPORT void JNICALL Java_net_nicholaswilliams_java_net_socket_RawSocketImpl_setSocketOption
	(JNIEnv *, jobject, jint, jint, jint, jint);

JNIEXPORT jint JNICALL Java_net_nicholaswilliams_java_net_socket_RawSocketImpl_getSocketOption
	(JNIEnv *, jobject, jint, jint, jint);

JNIEXPORT void JNICALL Java_net_nicholaswilliams_java_net_socket_RawSocketImpl_setTimeout
	(JNIEnv *, jobject, jint, jint, jint);

JNIEXPORT jint JNICALL Java_net_nicholaswilliams_java_net_socket_RawSocketImpl_getTimeout
	(JNIEnv *, jobject, jint, jint);

#ifdef __cplusplus
}
#endif /* ifdef __cplusplus */

#endif /* ifndef __RAWSOCKETS_RAW_SOCKET_IMPL_H */

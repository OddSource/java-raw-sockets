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
#include <map>

#ifdef _WIN32

#   include <winsock2.h>
#   include <ws2tcpip.h>

#else /* if defined(_WIN32) */

#include <netdb.h>
#include <sys/socket.h>
#include <unistd.h>
#include <sys/time.h>
//#define __APPLE_USE_RFC_2292
#include <netinet/in.h>
#include <netinet/in_systm.h>
#include <netinet/ip.h>
#include <netinet/ip6.h>
#include <netinet/icmp6.h>
#include <netinet/ip_icmp.h>
//#undef __APPLE_USE_RFC_2292

#endif /* if defined(_WIN32) else */

#include "io_oddsource_java_net_socket_Constants.h"

std::map<std::string, int> constantMap;
std::map<std::string, int>::iterator constantMapEnd;
static void populateConstantMap();

/*
 * Java Methods
 */

/*
 * Class: io_oddsource_java_net_socket_Constants
 * Method: registerNumericConstant();
 * Signature: (Ljava/lang/String;)I
 *
 * Returns the value of the operating system constant with the name constantNameJstring if that constant is
 * supported. If it is not supported, a SocketConstantNotDefinedException is raised.
 */
JNIEXPORT jint JNICALL Java_io_oddsource_java_net_socket_Constants_registerNumericConstant
    (JNIEnv *environment, jclass, jstring constantNameJstring)
{
    if(constantMap.size() == 0)
    {
        populateConstantMap();
    }

    jboolean isCopy;
    const char* constantName = environment->GetStringUTFChars(constantNameJstring, &isCopy);

    int returnValue = 0;
    if(constantMap.find(constantName) != constantMapEnd)
    {
        returnValue = constantMap[constantName];
    }
    else
    {
        std::string message("The specified constant, ");
        message += constantName;
        message += ", is not natively defined.";

        environment->ThrowNew(
            environment->FindClass("io/oddsource/java/net/socket/exception/SocketConstantNotDefinedException"),
            message.c_str()
        ); /* this does not return, so we can wait to clean up below */
    }

    environment->ReleaseStringUTFChars(constantNameJstring, constantName);
    return returnValue;
}

/*
 * Utility Functions
 */

static void populateConstantMap()
{
    constantMap["IPPROTO_IP"]      = IPPROTO_IP;
    constantMap["IPPROTO_IPIP"]    = IPPROTO_IPIP;
#ifdef IPPROTO_IPV4
    constantMap["IPPROTO_IPV4"]    = IPPROTO_IPV4;
#else
    constantMap["IPPROTO_IPV4"]    = IPPROTO_IPIP;
#endif
    constantMap["IPPROTO_IPV6"]    = IPPROTO_IPV6;
    constantMap["IPPROTO_TCP"]     = IPPROTO_TCP;
    constantMap["IPPROTO_UDP"]     = IPPROTO_UDP;
    constantMap["IPPROTO_ICMP"]    = IPPROTO_ICMP;

    constantMap["IP_HDRINCL"]      = IP_HDRINCL;
    constantMap["IP_OPTIONS"]      = IP_OPTIONS;
    constantMap["IP_RECVOPTS"]     = IP_RECVOPTS;
    constantMap["IP_RECVRETOPTS"]  = IP_RECVRETOPTS;
    constantMap["IP_TOS"]          = IP_TOS;
    constantMap["IP_TTL"]          = IP_TTL;

    /*
     * In Unix systems, IP_HDRINCL can be passed to setsockopt for IPv6 sockets, so IPV6_HDRINCL isn't defined. Windows
     * doesn't allow this, so it defines IPV6_HDRINCL. We want our Java code to be system independent, so we always
     * define this, and set it equal to IP_HDRINCL on systems that don't have IPV6_HDRINCL defined.
     */
#ifdef IPV6_HDRINCL
    constantMap["IPV6_HDRINCL"]    = IPV6_HDRINCL;
#else
    constantMap["IPV6_HDRINCL"]    = IP_HDRINCL;
#endif
    constantMap["IPV6_HOPLIMIT"]   = IPV6_HOPLIMIT;
#ifdef IPV6_MAXHLIM
    constantMap["IPV6_MAXHLIM"]    = IPV6_MAXHLIM;
#else
    constantMap["IPV6_MAXHLIM"]    = 255;
#endif
#ifdef IPV6_VERSION
    constantMap["IPV6_VERSION"]    = IPV6_VERSION;
#else
    constantMap["IPV6_VERSION"]    = 0x60;
#endif

    constantMap["SO_RCVBUF"]       = SO_RCVBUF;
    constantMap["SO_RCVTIMEO"]     = SO_RCVTIMEO;
    constantMap["SO_SNDBUF"]       = SO_SNDBUF;
    constantMap["SO_SNDTIMEO"]     = SO_SNDTIMEO;

    constantMap["SOL_SOCKET"]      = SOL_SOCKET;

    constantMap["AF_INET"]         = AF_INET;
    constantMap["AF_INET6"]        = AF_INET6;
    constantMap["PF_INET"]         = PF_INET;
    constantMap["PF_INET6"]        = PF_INET6;

    constantMapEnd = constantMap.end();
}

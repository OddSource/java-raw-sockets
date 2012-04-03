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

#ifndef __RAWSOCKETS_RAW_SOCKET_H
#define __RAWSOCKETS_RAW_SOCKET_H

#include <jni.h>

/*
 * Utility Functions
 */

static jobject getProtocolObjectByProtocolStruct(JNIEnv *, struct protoent *);

/*
 * Java Methods for class net.nicholaswilliams.java.net.socket.SocketUtilities
 */

#ifdef __cplusplus
extern "C" {
#endif /* ifdef __cplusplus */

JNIEXPORT jobject JNICALL Java_net_nicholaswilliams_java_net_socket_SocketUtilities_getProtocolByName
	(JNIEnv *, jclass, jstring);

JNIEXPORT jobject JNICALL Java_net_nicholaswilliams_java_net_socket_SocketUtilities_getProtocolByNumber
	(JNIEnv *, jclass, jint);

JNIEXPORT jobjectArray JNICALL Java_net_nicholaswilliams_java_net_socket_SocketUtilities_getProtocolList
	(JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif /* ifdef __cplusplus */

#endif /* ifndef __RAWSOCKETS_RAW_SOCKET_H */

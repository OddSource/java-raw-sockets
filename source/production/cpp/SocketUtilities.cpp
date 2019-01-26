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
#include <vector>

#ifdef _WIN32

#include <winsock2.h>
#include <ws2tcpip.h>

#ifndef close
#define close(fd) closesocket(fd)
#endif

#else /* if defined(_WIN32) */

#include <netdb.h>

#endif /* if defined(_WIN32) else */

#include "SocketUtilities.h"

static jobject getProtocolObjectByProtocolStruct(JNIEnv *environment, struct protoent *protocol);

/*
 * Java Methods for class io.oddsource.java.net.socket.SocketUtilities
 */

/*
 * Class: io_oddsource_java_net_socket_SocketUtilities
 * Method: getProtocolByName
 * Signature: (Ljava/lang/String;)Lio/oddsource/java/net/socket/Protocol;
 *
 * Looks up the protocol by its name, and returns null if it's not found.
 */
JNIEXPORT jobject JNICALL Java_io_oddsource_java_net_socket_SocketUtilities_getProtocolByName
	(JNIEnv *environment, jclass, jstring name)
{
	const char *utf = environment->GetStringUTFChars(name, NULL);
	struct protoent *protocol = getprotobyname(utf);
	environment->ReleaseStringUTFChars(name, utf);

	return getProtocolObjectByProtocolStruct(environment, protocol);
}

/*
 * Class: io_oddsource_java_net_socket_SocketUtilities
 * Method: getProtocolByNumber
 * Signature: (I)Lio/oddsource/java/net/socket/Protocol;
 *
 * Looks up the protocol by its number, and returns null if it's not found.
 */
JNIEXPORT jobject JNICALL Java_io_oddsource_java_net_socket_SocketUtilities_getProtocolByNumber
	(JNIEnv *environment, jclass, jint number)
{
	return getProtocolObjectByProtocolStruct(environment, getprotobynumber(number));
}

/*
 * Class: io_oddsource_java_net_socket_SocketUtilities
 * Method: getProtocolList
 * Signature: ()[Lio/oddsource/java/net/socket/Protocol;
 *
 * Compiles a list of all protocols supported by the system; returns an empty array if no protocols are found.
 */
JNIEXPORT jobjectArray JNICALL Java_io_oddsource_java_net_socket_SocketUtilities_getProtocolList
	(JNIEnv *environment, jclass SocketUtilities)
{
	environment->MonitorEnter(SocketUtilities); // synchronize access to the protocol list methods

	std::vector<jobject> protocolList;

	setprotoent(true);

	while(true)
	{
		struct protoent *protocol = getprotoent();
		if(protocol == 0 || protocol == NULL)
			break;
		protocolList.push_back(getProtocolObjectByProtocolStruct(environment, protocol));
	}

	endprotoent();

	environment->MonitorExit(SocketUtilities); // end synchronization

	jclass Protocol = environment->FindClass("io/oddsource/java/net/socket/Protocol");
	if(Protocol == 0)
	{
		environment->ThrowNew(
			environment->FindClass("java/lang/ClassNotFoundException"),
			"Could not find class io.oddsource.java.net.socket.Protocol."
		);
		return NULL;
	}

	jobjectArray protocols = environment->NewObjectArray(protocolList.size(), Protocol, NULL);

	for(unsigned int i = 0; i < protocolList.size(); i++)
	{
		environment->SetObjectArrayElement(protocols, i, protocolList[i]);
	}

	return protocols;
}

/*
 * Utility Functions
 */

static jobject getProtocolObjectByProtocolStruct(JNIEnv *environment, struct protoent *protocol)
{
	if(protocol == 0 || protocol == NULL)
		return NULL;

	jclass Protocol = environment->FindClass("io/oddsource/java/net/socket/Protocol");
	if(Protocol == 0)
	{
		environment->ThrowNew(
			environment->FindClass("java/lang/ClassNotFoundException"),
			"Could not find class io.oddsource.java.net.socket.Protocol."
		);
		return NULL;
	}

	jmethodID constructor = environment->GetMethodID(Protocol, "<init>", "(Ljava/lang/String;[Ljava/lang/String;I)V");

	if(constructor == 0)
	{
		environment->ThrowNew(
			environment->FindClass("java/lang/NoSuchMethodError"),
			"Could not find method <init>(String, String[], int) in class io.oddsource.java.net.socket.Protocol."
		);
		return NULL;
	}

	jstring name = environment->NewStringUTF(protocol->p_name);

	int aliasesSize = sizeof(protocol->p_aliases) / sizeof(char *);
	jobjectArray aliases = environment->NewObjectArray(aliasesSize, environment->FindClass("java/lang/String"), NULL);

	for(int i = 0; i < aliasesSize; i++)
	{
		environment->SetObjectArrayElement(aliases, i, environment->NewStringUTF(protocol->p_aliases[i]));
	}

	return environment->NewObject(Protocol, constructor, name, aliases, protocol->p_proto);
}

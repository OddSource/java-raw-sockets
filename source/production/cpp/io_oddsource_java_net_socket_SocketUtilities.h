/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class io_oddsource_java_net_socket_SocketUtilities */

#ifndef _Included_io_oddsource_java_net_socket_SocketUtilities
#define _Included_io_oddsource_java_net_socket_SocketUtilities
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     io_oddsource_java_net_socket_SocketUtilities
 * Method:    getProtocolByName
 * Signature: (Ljava/lang/String;)Lio/oddsource/java/net/socket/Protocol;
 */
JNIEXPORT jobject JNICALL Java_io_oddsource_java_net_socket_SocketUtilities_getProtocolByName
  (JNIEnv *, jclass, jstring);

/*
 * Class:     io_oddsource_java_net_socket_SocketUtilities
 * Method:    getProtocolByNumber
 * Signature: (I)Lio/oddsource/java/net/socket/Protocol;
 */
JNIEXPORT jobject JNICALL Java_io_oddsource_java_net_socket_SocketUtilities_getProtocolByNumber
  (JNIEnv *, jclass, jint);

/*
 * Class:     io_oddsource_java_net_socket_SocketUtilities
 * Method:    getProtocolList
 * Signature: ()[Lio/oddsource/java/net/socket/Protocol;
 */
JNIEXPORT jobjectArray JNICALL Java_io_oddsource_java_net_socket_SocketUtilities_getProtocolList
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif

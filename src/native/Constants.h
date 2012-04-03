/*
 * Constants.h from RawSockets modified Thursday, June 30, 2011 10:12:25 CDT (-0500).
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

#ifndef __RAWSOCKETS_CONSTANTS_H
#define __RAWSOCKETS_CONSTANTS_H

#include <jni.h>

/*
 * Utility Functions
 */

static void populateConstantMap();

/*
 * Java Methods
 */

#ifdef __cplusplus
extern "C" {
#endif /* ifdef __cplusplus */

JNIEXPORT jint JNICALL Java_net_nicholaswilliams_java_net_socket_Constants_registerNumericConstant
	(JNIEnv *, jclass, jstring);

#ifdef __cplusplus
}
#endif /* ifdef __cplusplus */

#endif /* ifndef __RAWSOCKETS_CONSTANTS_H */

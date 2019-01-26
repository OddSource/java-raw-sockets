/*
 * SocketConstantNotDefinedException.java from RawSockets modified Thursday, June 30, 2011 11:24:20 CDT (-0500).
 *
 * Copyright 2010-2011 the original author or authors.
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

package io.oddsource.java.net.socket.exception;

/**
 * Thrown when {@link io.oddsource.java.net.socket.Constants} is first loaded if any of the constants specified
 * in the Java code were not defined by the native code when it was compiled.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class SocketConstantNotDefinedException extends RawSocketRuntimeException
{
    public SocketConstantNotDefinedException()
    {
        this("The specified socket constant is not defined.");
    }

    public SocketConstantNotDefinedException(String message)
    {
        super(message);
    }

    public SocketConstantNotDefinedException(Throwable cause)
    {
        this("The specified socket constant is not defined.", cause);
    }

    public SocketConstantNotDefinedException(String message, Throwable cause)
    {
        super(message, cause);
    }
}

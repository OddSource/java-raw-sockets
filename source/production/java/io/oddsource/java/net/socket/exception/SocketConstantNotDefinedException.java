/*
 * Copyright © 2010-2019 OddSource Code (license@oddsource.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
    /**
     * Constructor.
     */
    public SocketConstantNotDefinedException()
    {
        this("The specified socket constant is not defined.");
    }

    /**
     * Constructor.
     *
     * @param message The message
     */
    public SocketConstantNotDefinedException(final String message)
    {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param cause The cause
     */
    public SocketConstantNotDefinedException(final Throwable cause)
    {
        this("The specified socket constant is not defined.", cause);
    }

    /**
     * Constructor.
     *
     * @param message The message
     * @param cause The cause
     */
    public SocketConstantNotDefinedException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}

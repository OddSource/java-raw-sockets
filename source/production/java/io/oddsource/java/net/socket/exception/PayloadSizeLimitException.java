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
 * This exception is thrown whenever a payload is specified that is too large for the packet type.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class PayloadSizeLimitException extends RawSocketRuntimeException
{
    /**
     * Constructor.
     */
    public PayloadSizeLimitException()
    {
        this("The payload size limit has been exceeded for this packet type.");
    }

    /**
     * Constructor.
     *
     * @param sizeLimit The size limit that was exceeded
     */
    public PayloadSizeLimitException(final int sizeLimit)
    {
        this("The payload size limit (" + sizeLimit + " bytes) has been exceeded for this packet type.");
    }

    /**
     * Constructor.
     *
     * @param message The message
     */
    public PayloadSizeLimitException(final String message)
    {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param cause The cause
     */
    public PayloadSizeLimitException(final Throwable cause)
    {
        this("The payload size limit has been exceeded for this packet type.", cause);
    }

    /**
     * Constructor.
     *
     * @param sizeLimit The size limit that was exceeded
     * @param cause The cause
     */
    public PayloadSizeLimitException(final int sizeLimit, final Throwable cause)
    {
        this("The payload size limit (" + sizeLimit + " bytes) has been exceeded for this packet type.", cause);
    }

    /**
     * Constructor.
     *
     * @param message The message
     * @param cause The cause
     */
    public PayloadSizeLimitException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}

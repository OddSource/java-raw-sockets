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
 * This exception is thrown when the hop limit (IPv6) or time-to-live/TTL (IPv4) is not an integer between 1 and 255
 * (inclusive).
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class IllegalHopLimitException extends RawSocketRuntimeException
{
    /**
     * Constructor.
     */
    public IllegalHopLimitException()
    {
        this("The hop limit (IPv6) or time-to-live/TTL (IPv4) must be an integer between 1 and 255 (inclusive).");
    }

    /**
     * Constructor.
     *
     * @param message The message
     */
    public IllegalHopLimitException(final String message)
    {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param cause The cause
     */
    public IllegalHopLimitException(final Throwable cause)
    {
        this(
            "The hop limit (IPv6) or time-to-live/TTL (IPv4) must be an integer between 1 and 255 (inclusive).",
            cause
        );
    }

    /**
     * Constructor.
     *
     * @param message The message
     * @param cause The cause
     */
    public IllegalHopLimitException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}

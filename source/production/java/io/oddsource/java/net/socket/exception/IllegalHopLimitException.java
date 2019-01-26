/*
 * IllegalHopLimitException.java from RawSockets modified Tuesday, June 28, 2011 13:16:35 CDT (-0500).
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
	public IllegalHopLimitException()
	{
		this("The hop limit (IPv6) or time-to-live/TTL (IPv4) must be an integer between 1 and 255 (inclusive).");
	}

	public IllegalHopLimitException(String message)
	{
		super(message);
	}

	public IllegalHopLimitException(Throwable cause)
	{
		this("The hop limit (IPv6) or time-to-live/TTL (IPv4) must be an integer between 1 and 255 (inclusive).", cause);
	}

	public IllegalHopLimitException(String message, Throwable cause)
	{
		super(message, cause);
	}
}

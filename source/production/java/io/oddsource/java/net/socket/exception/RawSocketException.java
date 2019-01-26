/*
 * RawSocketException.java from RawSockets modified Tuesday, June 28, 2011 13:35:05 CDT (-0500).
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

import java.net.SocketException;

/**
 * Generic exception thrown whenever an expected error occurs using the Raw Sockets library.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class RawSocketException extends SocketException
{
	public RawSocketException()
	{
		super();
	}

	public RawSocketException(String message)
	{
		super(message);
	}

	public RawSocketException(Throwable cause)
	{
		super();
		this.initCause(cause);
	}

	public RawSocketException(String message, Throwable cause)
	{
		super(message);
		this.initCause(cause);
	}
}

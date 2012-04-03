/*
 * RawSocketImpl.java from RawSockets modified Tuesday, July 5, 2011 07:19:20 CDT (-0500).
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

package net.nicholaswilliams.java.net.socket;

/**
 * Class description here.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public class RawSocketImpl implements RawSocket
{
	static
	{
		System.loadLibrary("rawsockets");

		RawSocketImpl.nativeStaticInitialize();

		Runtime.getRuntime().addShutdownHook(new Thread(
				new Runnable()
				{
					@Override
					public void run()
					{
						RawSocketImpl.nativeStaticShutdown();
					}
				}
			));
	}
	private static native void nativeStaticInitialize();
	private static native void nativeStaticShutdown();

	private static final int UNDEFINED = -1;

	private int nativeSocketIdentifier;

	private IpVersion ipVersion;

	private boolean useSelectTimeout;

	private final TimeoutValue sendTimeout;

	private final TimeoutValue receiveTimeout;

	public RawSocketImpl()
	{
		this.nativeSocketIdentifier = UNDEFINED;
		this.sendTimeout = new TimeoutValue();
		this.receiveTimeout = new TimeoutValue();

		String os = System.getProperty("os.name");

		if(os != null && os.startsWith("SunOS"))
			setUseSelectTimeout(true);
		else
			setUseSelectTimeout(false);
	}

	protected final int getNativeSocketIdentifier()
	{
		return this.nativeSocketIdentifier;
	}

	protected final IpVersion getIpVersion()
	{
		return this.ipVersion;
	}

	@Override
	public void setSocketOption(final SocketLevel level, final int option, final int value)
	{
		this.setSocketOption(this.getNativeSocketIdentifier(), level.getOsConstant(), option, value);
	}
	protected native void setSocketOption(int socket, int level, int option, int value);

	@Override
	public int getSocketOption(final SocketLevel level, final int option)
	{
		return this.getSocketOption(this.getNativeSocketIdentifier(), level.getOsConstant(), option);
	}
	protected native int getSocketOption(int socket, int level, int option);

	@Override
	public void setUseSelectTimeout(boolean useSelectTimeout)
	{
		this.useSelectTimeout = useSelectTimeout;
	}

	@Override
	public boolean getUseSelectTimeout()
	{
		return this.useSelectTimeout;
	}

	protected void setTimeout(final int option, final int milliseconds)
	{
		this.setTimeout(this.getNativeSocketIdentifier(), option, milliseconds);
	}
	protected native void setTimeout(int socket, int option, int milliseconds);

	protected int getTimeout(final int option)
	{
		return getTimeout(this.getNativeSocketIdentifier(), option);
	}
	protected native int getTimeout(int socket, int option);

	@Override
	public void setSendTimeout(final int milliseconds)
	{
		this.sendTimeout.setByMilliseconds(milliseconds);

		if(!this.getUseSelectTimeout())
			this.setTimeout(Constants.SO_SNDTIMEO, milliseconds);
	}

	@Override
	public int getSendTimeout()
	{
		return this.getUseSelectTimeout() ?
					this.sendTimeout.getInMilliseconds() :
					this.getTimeout(Constants.SO_SNDTIMEO);
	}

	@Override
	public void setReceiveTimeout(final int milliseconds)
	{
		this.receiveTimeout.setByMilliseconds(milliseconds);

		if(!this.getUseSelectTimeout())
			this.setTimeout(Constants.SO_RCVTIMEO, milliseconds);
	}

	@Override
	public int getReceiveTimeout()
	{
		return this.getUseSelectTimeout() ?
					this.receiveTimeout.getInMilliseconds() :
					this.getTimeout(Constants.SO_RCVTIMEO);
	}

	@Override
	public void setSendBufferSize(final int bytes)
	{
		this.setSocketOption(SocketLevel.SOCKET, Constants.SO_SNDBUF, bytes);
	}

	@Override
	public int getSendBufferSize()
	{
		return this.getSocketOption(SocketLevel.SOCKET, Constants.SO_SNDBUF);
	}

	@Override
	public void setReceiveBufferSize(final int bytes)
	{
		this.setSocketOption(SocketLevel.SOCKET, Constants.SO_RCVBUF, bytes);
	}

	@Override
	public int getReceiveBufferSize()
	{
		return this.getSocketOption(SocketLevel.SOCKET, Constants.SO_RCVBUF);
	}

	@Override
	public void setIpHeaderInclude(final boolean on)
	{
		final int value = on ? 1 : 0;

		if(this.getIpVersion() == IpVersion.IPv6)
			this.setSocketOption(SocketLevel.IPv6, Constants.IPV6_HDRINCL, value);
		else
			this.setSocketOption(SocketLevel.IP, Constants.IP_HDRINCL, value);
	}

	@Override
	public boolean getIpHeaderInclude()
	{
		if(this.getIpVersion() == IpVersion.IPv6)
			return this.getSocketOption(SocketLevel.IPv6, Constants.IPV6_HDRINCL) == 1;
		else
			return this.getSocketOption(SocketLevel.IP, Constants.IP_HDRINCL) == 1;
	}
}

/*
 * Constants.java from RawSockets modified Thursday, June 30, 2011 10:18:14 CDT (-0500).
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

package io.oddsource.java.net.socket;

/**
 * A group of constants to support I/O operations.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Constants
{
	static
	{
		System.loadLibrary("rawsockets");

		IPPROTO_IP		= Constants.registerNumericConstant("IPPROTO_IP");
		IPPROTO_IPIP	= Constants.registerNumericConstant("IPPROTO_IPIP");
		IPPROTO_IPV4	= Constants.registerNumericConstant("IPPROTO_IPV4");
		IPPROTO_IPV6	= Constants.registerNumericConstant("IPPROTO_IPV6");
		IPPROTO_TCP		= Constants.registerNumericConstant("IPPROTO_TCP");
		IPPROTO_UDP		= Constants.registerNumericConstant("IPPROTO_UDP");
		IPPROTO_ICMP	= Constants.registerNumericConstant("IPPROTO_ICMP");

		IP_HDRINCL		= Constants.registerNumericConstant("IP_HDRINCL");
		IP_OPTIONS		= Constants.registerNumericConstant("IP_OPTIONS");
		IP_RECVOPTS		= Constants.registerNumericConstant("IP_RECVOPTS");
		IP_RECVRETOPTS	= Constants.registerNumericConstant("IP_RECVRETOPTS");
		IP_TOS			= Constants.registerNumericConstant("IP_TOS");
		IP_TTL			= Constants.registerNumericConstant("IP_TTL");

		IPV6_HDRINCL	= Constants.registerNumericConstant("IPV6_HDRINCL");
		IPV6_HOPLIMIT	= Constants.registerNumericConstant("IPV6_HOPLIMIT");
		IPV6_MAXHLIM	= Constants.registerNumericConstant("IPV6_MAXHLIM");
		IPV6_VERSION	= Constants.registerNumericConstant("IPV6_VERSION");

		SO_RCVBUF		= Constants.registerNumericConstant("SO_RCVBUF");
		SO_RCVTIMEO		= Constants.registerNumericConstant("SO_RCVTIMEO");
		SO_SNDBUF		= Constants.registerNumericConstant("SO_SNDBUF");
		SO_SNDTIMEO		= Constants.registerNumericConstant("SO_SNDTIMEO");

		SOL_IP			= Constants.IPPROTO_IP;
		SOL_IPV4		= Constants.IPPROTO_IPV4;
		SOL_IPV6		= Constants.IPPROTO_IPV6;
		SOL_SOCKET		= Constants.registerNumericConstant("SOL_SOCKET");
		SOL_TCP			= Constants.IPPROTO_TCP;
		SOL_UDP			= Constants.IPPROTO_UDP;

		AF_INET			= Constants.registerNumericConstant("AF_INET");
		AF_INET6		= Constants.registerNumericConstant("AF_INET6");
		PF_INET			= Constants.registerNumericConstant("PF_INET");
		PF_INET6		= Constants.registerNumericConstant("PF_INET6");
	}

	public static final int IPPROTO_IP;
	public static final int IPPROTO_IPIP;
	public static final int IPPROTO_IPV4;
	public static final int IPPROTO_IPV6;
	public static final int IPPROTO_TCP;
	public static final int IPPROTO_UDP;
	public static final int IPPROTO_ICMP;

	public static final int IP_HDRINCL;
	public static final int IP_OPTIONS;
	public static final int IP_RECVOPTS;
	public static final int IP_RECVRETOPTS;
	public static final int IP_TOS;
	public static final int IP_TTL;

	public static final int IPV6_HDRINCL;
	public static final int IPV6_HOPLIMIT;
	public static final int IPV6_MAXHLIM;
	public static final int IPV6_VERSION;

	public static final int SO_RCVBUF;
	public static final int SO_RCVTIMEO;
	public static final int SO_SNDBUF;
	public static final int SO_SNDTIMEO;

	public static final int SOL_IP;
	public static final int SOL_IPV4;
	public static final int SOL_IPV6;
	public static final int SOL_SOCKET;
	public static final int SOL_TCP;
	public static final int SOL_UDP;

	public static final int AF_INET;
	public static final int AF_INET6;
	public static final int PF_INET;
	public static final int PF_INET6;

	/**
	 * Takes the name of a constant, finds the C equivalent of that constant, and returns the value of that
	 * constant. See Constants.cpp for more details on what this method does.
	 *
	 * @param constantName The name of the constant to register the value for
	 * @return the integer value of the constant.
	 */
	private static native int registerNumericConstant(String constantName);

	/**
	 * This class is not meant to be instantiated.
	 */
	private Constants()
	{
		throw new UnsupportedOperationException("This class is not meant to be instantiated.");
	}
}

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
package io.oddsource.java.net.socket;

/**
 * A group of constants to support I/O operations.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Constants extends DynamicNativeObject
{
    /*
     Protocol constants
     */
    /**
     * The IP protocol.
     */
    public static final int IPPROTO_IP;

    /**
     * The IP tunnel protocol.
     */
    public static final int IPPROTO_IPIP;

    /**
     * IPv4 identifier.
     */
    public static final int IPPROTO_IPV4;

    /**
     * IPv6 identifier.
     */
    public static final int IPPROTO_IPV6;

    /**
     * The TCP protocol.
     */
    public static final int IPPROTO_TCP;

    /**
     * The UDP protocol (datagrams).
     */
    public static final int IPPROTO_UDP;

    /**
     * The ICMP protocol.
     */
    public static final int IPPROTO_ICMP;

    /*
     IP constants
     */
    /**
     * A flag indicating that the IP header will be manually specified. If not set, the kernel will generate the IP
     * header automatically.
     */
    public static final int IP_HDRINCL;

    /**
     * A flag for setting IP layer options.
     */
    public static final int IP_OPTIONS;

    /**
     * A flag to pass all incoming IP options to the user in a IP_OPTIONS control message.
     */
    public static final int IP_RECVOPTS;

    /**
     * The same as {@code IP_RECVOPTS}.
     */
    public static final int IP_RECVRETOPTS;

    /**
     * Set or receive the Type-Of-Service (TOS) field that is sent with every IP packet originating from this socket.
     */
    public static final int IP_TOS;

    /**
     * Set or retrieve the current time-to-live field that is used in every packet sent from this socket.
     */
    public static final int IP_TTL;

    /*
     IPv6 constants
     */
    /**
     * Indicates the application provides the IPv6 header on all outgoing data.
     */
    public static final int IPV6_HDRINCL;

    /**
     * Indicates that hop (TTL) information should be returned in the received message.
     */
    public static final int IPV6_HOPLIMIT;

    /**
     * The maximum hop limit for IPv6 packets (should be 255).
     */
    public static final int IPV6_MAXHLIM;

    /**
     * Unknown purpose. May be removed soon.
     */
    public static final int IPV6_VERSION;

    /*
     Socket options
     */
    /**
     * Sets or gets the maximum socket receive buffer in bytes.
     */
    public static final int SO_RCVBUF;

    /**
     * Specify the sending timeout.
     */
    public static final int SO_RCVTIMEO;

    /**
     * Sets or gets the maximum socket send buffer in bytes.
     */
    public static final int SO_SNDBUF;

    /**
     * Specify the receive timeout.
     */
    public static final int SO_SNDTIMEO;

    /*
     Socket level identifiers.
     */
    /**
     * Non-portable variant of {@code IPPROTO_*}. Might be removed.
     */
    public static final int SOL_IP;

    /**
     * Non-portable variant of {@code IPPROTO_*}. Might be removed.
     */
    public static final int SOL_IPV4;

    /**
     * Non-portable variant of {@code IPPROTO_*}. Might be removed.
     */
    public static final int SOL_IPV6;

    /**
     * Used for protocol-independent socket options.
     */
    public static final int SOL_SOCKET;

    /**
     * Non-portable variant of {@code IPPROTO_*}. Might be removed.
     */
    public static final int SOL_TCP;

    /**
     * Non-portable variant of {@code IPPROTO_*}. Might be removed.
     */
    public static final int SOL_UDP;

    /*
     Address and packet format identifiers.
     */
    /**
     * Address format fimaly identifier for IPv4 addresses.
     */
    public static final int AF_INET;

    /**
     * Address format family identifier for IPv6 addresses.
     */
    public static final int AF_INET6;

    /**
     * Packet format family identifier for IPv4 addresses.
     */
    public static final int PF_INET;

    /**
     * Packet format family identifier for IPv6 addresses.
     */
    public static final int PF_INET6;

    static
    {
        IPPROTO_IP = Constants.registerNumericConstant("IPPROTO_IP");
        IPPROTO_IPIP = Constants.registerNumericConstant("IPPROTO_IPIP");
        IPPROTO_IPV4 = Constants.registerNumericConstant("IPPROTO_IPV4");
        IPPROTO_IPV6 = Constants.registerNumericConstant("IPPROTO_IPV6");
        IPPROTO_TCP = Constants.registerNumericConstant("IPPROTO_TCP");
        IPPROTO_UDP = Constants.registerNumericConstant("IPPROTO_UDP");
        IPPROTO_ICMP = Constants.registerNumericConstant("IPPROTO_ICMP");

        IP_HDRINCL = Constants.registerNumericConstant("IP_HDRINCL");
        IP_OPTIONS = Constants.registerNumericConstant("IP_OPTIONS");
        IP_RECVOPTS = Constants.registerNumericConstant("IP_RECVOPTS");
        IP_RECVRETOPTS = Constants.registerNumericConstant("IP_RECVRETOPTS");
        IP_TOS = Constants.registerNumericConstant("IP_TOS");
        IP_TTL = Constants.registerNumericConstant("IP_TTL");

        IPV6_HDRINCL = Constants.registerNumericConstant("IPV6_HDRINCL");
        IPV6_HOPLIMIT = Constants.registerNumericConstant("IPV6_HOPLIMIT");
        IPV6_MAXHLIM = Constants.registerNumericConstant("IPV6_MAXHLIM");
        IPV6_VERSION = Constants.registerNumericConstant("IPV6_VERSION");

        SO_RCVBUF = Constants.registerNumericConstant("SO_RCVBUF");
        SO_RCVTIMEO = Constants.registerNumericConstant("SO_RCVTIMEO");
        SO_SNDBUF = Constants.registerNumericConstant("SO_SNDBUF");
        SO_SNDTIMEO = Constants.registerNumericConstant("SO_SNDTIMEO");

        SOL_IP = Constants.IPPROTO_IP;
        SOL_IPV4 = Constants.IPPROTO_IPV4;
        SOL_IPV6 = Constants.IPPROTO_IPV6;
        SOL_SOCKET = Constants.registerNumericConstant("SOL_SOCKET");
        SOL_TCP = Constants.IPPROTO_TCP;
        SOL_UDP = Constants.IPPROTO_UDP;

        AF_INET = Constants.registerNumericConstant("AF_INET");
        AF_INET6 = Constants.registerNumericConstant("AF_INET6");
        PF_INET = Constants.registerNumericConstant("PF_INET");
        PF_INET6 = Constants.registerNumericConstant("PF_INET6");
    }

    /**
     * This class is not meant to be instantiated.
     */
    private Constants()
    {
        throw new AssertionError("This class is not meant to be instantiated.");
    }

    /**
     * Takes the name of a constant, finds the C equivalent of that constant, and returns the value of that
     * constant. See Constants.cpp for more details on what this method does.
     *
     * @param constantName The name of the constant to register the value for
     *
     * @return the integer value of the constant.
     */
    private static native int registerNumericConstant(String constantName);
}

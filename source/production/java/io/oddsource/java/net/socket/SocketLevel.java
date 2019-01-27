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
 * An enum for indicating the level at which socket options apply.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public enum SocketLevel
{
    /**
     * Indicates the socket itself for IPv4 and IPv6 packets.
     */
    SOCKET(Constants.SOL_SOCKET, true),

    /**
     * Indicates the IP layer for only IPv4 packets.
     */
    IP(Constants.SOL_IP, false),

    /**
     * Indicates the IP layer for only IPv6 packets.
     */
    IPv6(Constants.SOL_IPV6, true),

    /**
     * Indicates the TCP layer for IPv4 and IPv6 packets.
     */
    TCP(Constants.SOL_TCP, true),

    /**
     * Indicates the UDP layer for IPv4 and IPv6 packets.
     */
    UDP(Constants.SOL_UDP, true),

    /**
     * Indicates the ICMP layer for IPv4 and IPv6 packets.
     */
    ICMP(Constants.IPPROTO_ICMP, true);

    private final int osConstant;

    private final boolean supportsIPv6;

    SocketLevel(final int osConstant, final boolean supportsIPv6)
    {
        this.osConstant = osConstant;
        this.supportsIPv6 = supportsIPv6;
    }

    /**
     * Get the OS constant for this level.
     *
     * @return the OS constant.
     */
    public int getOsConstant()
    {
        return osConstant;
    }

    /**
     * Indicates whether this level supports IPv6.
     *
     * @return whether it supports IPv6.
     */
    public boolean supportsIPv6()
    {
        return this.supportsIPv6;
    }
}

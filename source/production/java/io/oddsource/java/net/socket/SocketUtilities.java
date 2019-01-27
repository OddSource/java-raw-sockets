/*
 * SocketUtilities.java from RawSockets modified Tuesday, July 5, 2011 17:51:18 CDT (-0500).
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

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A collection of network utilities that don't belong to any particular object definition.
 *
 * @see Protocol
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public final class SocketUtilities
{
    static
    {
        System.loadLibrary("rawsockets");
    }

    /**
     * This class is not meant to be instantiated.
     */
    private SocketUtilities()
    {
        throw new AssertionError("This class is not meant to be instantiated.");
    }

    /**
     * Looks up the protocol by its name, and returns null if it's not found.
     *
     * @param name The name of the protocol to lookup
     *
     * @return the protocol named by {@code name}, or null if it wasn't found.
     */
    public static native Protocol getProtocolByName(String name);

    /**
     * Looks up the protocol by its number, and returns null if it's not found.
     *
     * @param number The number of the protocol to lookup
     *
     * @return the protocol numbered {@code number}, or null if it wasn't found.
     */
    public static native Protocol getProtocolByNumber(int number);

    /**
     * Compiles a list of all protocols supported by the system; returns an empty array if no protocols are found.
     *
     * @return a list of all protocols supported by the system or an empty array if none were found.
     */
    public static native Protocol[] getProtocolList();

    /**
     * Get the source address for the destination.
     *
     * @param destination The destination address
     *
     * @return the source address.
     *
     * @throws UnknownHostException If the destination host cannot be resolved
     */
    public static InetAddress getSourceAddressForDestination(final InetAddress destination) throws UnknownHostException
    {
        throw new UnsupportedOperationException("Not implemented yet.");
        /*int family = (destination instanceof Inet6Address) ?
                     IpVersion.IPv6.getAddressFormatFamily() : IpVersion.IPv4.getAddressFormatFamily();

        return InetAddress.getByAddress(
                SocketUtilities.getSourceAddressForDestination(
                        destination.getAddress(),
                        family
                )
        );*/
    }
    //public static native byte[] getSourceAddressForDestination(byte[] destination, int addressFormatFamily);
}

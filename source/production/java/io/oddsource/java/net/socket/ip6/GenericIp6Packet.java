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
package io.oddsource.java.net.socket.ip6;

/**
 * Class description here. TODO
 *
 * <b>IPv6 IP Header Format</b>
 * <pre>
 * +--------+-------+-----------------------+-----------------------+-----------------------+-----------------------+
 * | Offset | Octet |           0           |           1           |           2           |           3           |
 * +--------+-------+-----------------------+-----------------------+-----------------------+-----------------------+
 * | Octet  | Bits  |0 ·1 ·2 ·3 ·4 ·5 ·6 ·7 |8 ·9 ·10·11·12·13·14·15|16·17·18·19·20·21·22·23|24·25·26·27·28·29·30·31|
 * +--------+-------+-----------+-----------+-----------+-----------+-----------------------+-----------------------+
 * |   0    |  0    |  Version  |      DSCP       | ECN |                        Flow Label                         |
 * +--------+-------+-----------+-----------------------+-----------+-----------------------+-----------------------+
 * |   4    |  32   |                Payload Length                 |      Next Header      |       Hop Limit       |
 * +--------+-------+-----------------------+-----------------------+-----------------------+-----------------------+
 * |   8    |  64   |                                                                                               |
 * +--------+-------+                                                                                               |
 * |   12   |  96   |                                                                                               |
 * +--------+-------+                                     Source IPv6 Address                                       |
 * |   16   |  128  |                                                                                               |
 * +--------+-------+                                                                                               |
 * |   20   |  160  |                                                                                               |
 * +--------+-------+-----------------------------------------------------------------------------------------------+
 * |   24   |  192  |                                                                                               |
 * +--------+-------+                                                                                               |
 * |   28   |  224  |                                                                                               |
 * +--------+-------+                                   Destination IPv6 Address                                    |
 * |   32   |  256  |                                                                                               |
 * +--------+-------+                                                                                               |
 * |   36   |  288  |                                                                                               |
 * +--------+-------+-----------------------------------------------------------------------------------------------+
 * </pre>
 * <br>
 * Note: The IPv6 packet header is a fixed header with a constant length of 40 octets/bytes (320 bits). As such, there
 * is no header length field like there is in IPv4 packets. Also, together, the 6 DSCP bits and the 2 ECN bits are
 * often simply referred to as the "Traffic Class" field.
 * <br>
 * <ul>
 *     <li>
 *         <b>Version</b><br>
 *         This value is always the number 4.
 *     </li>
 *     <li>
 *         <b>Traffic Class 6 MSB (DSCP)</b><br>
 *         See {@link io.oddsource.java.net.socket.ip4.GenericIp4Packet}'s documentation on this field.
 *     </li>
 *     <li>
 *         <b>Traffic Class 2 LSB (ECN)</b><br>
 *         See {@link io.oddsource.java.net.socket.ip4.GenericIp4Packet}'s documentation on this field.
 *     </li>
 *     <li>
 *         <b>Flow Label</b><br>
 *         This was originally intended for giving real-time applications special treatment. In practice, it indicates
 *         to the router that all matching packets with this flow label should take the exact same path through the
 *         network.
 *     </li>
 *     <li>
 *         <b>Payload Length</b><br>
 *         The total length of this packet's payload, expressed in bytes, excluding this IPv6 header but including any
 *         subsequent protocol/extension headers. This value is set to 0 if the extension headers is a Hop-by-Hop
 *         header (protocol 0x00 or 0) with the Jumbo Payload option enabled.
 *     </li>
 *     <li>
 *         <b>Next Header</b><br>
 *         The protocol type for the next header, such as ICMP (0x01 or 1), UDP (0x11 or 17), and TCP (0x06 or 6). See
 *         the IANA's <a href="https://en.wikipedia.org/wiki/List_of_IP_protocol_numbers">list of protocol numbers</a>.
 *     </li>
 *     <li>
 *         <b>Hop Limit</b><br>
 *         Replaces the Time to Live field from the IPv4 header. This is explicitly the limit on the number of hops,
 *         whereas the IPv4 TTL field is used that way only in practice.
 *     </li>
 *     <li>
 *         <b>Source IPv6 Address</b><br>
 *         The origin IPv6 address, which may be modified in transit by routers performing Network Address Translation,
 *         though this is very unusual since NATv6 is not commonly used.
 *     </li>
 *     <li>
 *         <b>Destination IPv4 Address</b><br>
 *         The destination IPv4 address, which may be modified in transit by routers performing Network Address
 *         Translation,though this is very unusual since NATv6 is not commonly used.
 *     </li>
 * </ul>
 * <br>
 * (Source: https://en.wikipedia.org/wiki/IPv6_packet#Fixed_header)
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public class GenericIp6Packet
{
    /**
     * Constructor.
     */
    public GenericIp6Packet()
    {

    }
}

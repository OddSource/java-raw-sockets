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
package io.oddsource.java.net.socket.ip4;

/**
 * Class description here. TODO
 *
 * <b>IPv4 IP Header Format</b>
 * <pre>
 * +--------+-------+-----------------------+-----------------------+-----------------------+-----------------------+
 * | Offset | Octet |           0           |           1           |           2           |           3           |
 * +--------+-------+-----------------------+-----------------------+-----------------------+-----------------------+
 * | Octet  | Bits  |0 ·1 ·2 ·3 ·4 ·5 ·6 ·7 |8 ·9 ·10·11·12·13·14·15|16·17·18·19·20·21·22·23|24·25·26·27·28·29·30·31|
 * +--------+-------+-----------+-----------+-----------------+-----+-----------------------+-----------------------+
 * |   0    |  0    |  Version  |    IHL    |      DSCP       | ECN |                 Total Length                  |
 * +--------+-------+-----------+-----------+-----------------+-----+--------+--------------+-----------------------+
 * |   4    |  32   |                Identification                 | Flags  |           Fragment Offset            |
 * +--------+-------+-----------------------+-----------------------+--------+--------------+-----------------------+
 * |   8    |  64   |     Time to Live      |        Protocol       |               Header Checksum                 |
 * +--------+-------+-----------------------+-----------------------+-----------------------------------------------+
 * |   12   |  96   |                                     Source IPv4 Address                                       |
 * +--------+-------+-----------------------------------------------------------------------------------------------+
 * |   16   |  128  |                                   Destination IPv4 Address                                    |
 * +--------+-------+-----------------------------------------------------------------------------------------------+
 * |   20   |  160  |                                                                                               |
 * +--------+-------+                                                                                               |
 * |   24   |  192  |                                                                                               |
 * +--------+-------+                                     Options (if IHL &gt; 5)                                      |
 * |   28   |  224  |                                                                                               |
 * +--------+-------+                                                                                               |
 * |   32   |  256  |                                                                                               |
 * +--------+-------+-----------------------------------------------------------------------------------------------+
 * </pre>
 * <br>
 * <ul>
 *     <li>
 *         <b>Version</b><br>
 *         This value is always the number 4.
 *     </li>
 *     <li>
 *         <b>Internet Header Length (IHL)</b><br>
 *         The number of 32-bit words in this header. The length of the header in bits is this value times 32. The
 *         length of the header in bytes is this value times 4. The minimum value is 5 (20 bytes). The maximum value is
 *         15 (60 bytes).
 *     </li>
 *     <li>
 *         <b>Differentiated Services Code Point (DSCP)</b><br>
 *         Often used for ToS, this indicates things such as streaming video or VoIP, for prioritization purposes. It
 *         is 6 bits, and the most common value is 0b000000 (0), meaning "routine." See
 *         <a href="https://en.wikipedia.org/wiki/Differentiated_services">Differentiated Services</a>.
 *     </li>
 *     <li>
 *         <b>Explicit Congestion Notification (ECN)</b><br>
 *         A special field that allows end-to-end notification of network congestion that only works if both ends
 *         support it. At this time, this field is not supported by this library, and the value will always be 0b00 (0).
 *     </li>
 *     <li>
 *         <b>Total Length</b><br>
 *         The total length of this packet, expressed in bytes, including the the IP header, any subsequent protocol
 *         headers, and the packet payload. The minimum value is 20 (20 bytes), which is the minimum IP header size
 *         without any payload. The maximum value is 65,535 bytes. Note that 65,535 often exceeds a network interface's
 *         Maximum Transmission Unit (MTU). All interfaces are required to support packets of at least 576 bytes
 *         (have a minimum MTU of 576). Most support an MTU of about 1500. The loopback interface typically supports
 *         65,535 bytes on Linux systems and 6000 bytes on BSD systems (such as macOS). Any packet that exceeds the MTU
 *         of an interface will be fragmented. This library automatically sets this field based on the contents of the
 *         packet.
 *     </li>
 *     <li>
 *         <b>Identification</b><br>
 *         If a packet is fragmented to be successfully transmitted under a given MTU, this 2-byte number indicates the
 *         sequential fragment ID number. The maximum value is 65,535, but realistically it will never be more than
 *         204, since that's the maximum number of fragments into which a 65,535-byte-long packet must be divided to
 *         not exceed the minimum MTU of 576. This library does not allow modification of this field.
 *     </li>
 *     <li>
 *         <b>Flags</b><br>
 *         A three-bit flag field. The bits are, in order from MSB to LSB:
 *         <ul>
 *             <li>0: reserved, must always be set to 0 (this library prohibits modification)</li>
 *             <li>
 *                 1: DF - a value of 1 means fragmentation is prohibited, a value of 0 means it is allowed (an error
 *                 will be returned if this is set and the packet exceeds the MTU of any interface it needs to traverse)
 *             </li>
 *             <li>
 *                 2: MF - a value of 1 means there is another fragment after this fragment, while 0 means this is the
 *                 last fragment or the packet did not require any fragmentation (this library prohibits modification)
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         <b>Fragment Offset</b><br>
 *         The offset of this fragment from the beginning of the packet as measured in 8-byte blocks. This library does
 *         not allow modification of this field.
 *     </li>
 *     <li>
 *         <b>Time to Live (TTL)</b><br>
 *         Contrary to what the name might indicate, this has nothing to do with time in practice. Per the
 *         specification, it's the maximum life of the packet in seconds, but each hop is required to round seconds up
 *         to the next second. As such, this field has become the indication of the maximum hop count. It keeps
 *         packets from going in circles on the internet indefinitely, and it provides support for a useful diagnostic
 *         tool called the traceroute.
 *     </li>
 *     <li>
 *         <b>Protocol</b><br>
 *         This field defines the protocol used in the payload of this packet. Some protocols include their own header
 *         as part of that payload (such as ICMP, TCP, and UDP). The IANA maintains a list of reserved protocol numbers.
 *         Among them are ICMP (0x01 or 1), UDP (0x11 or 17), and TCP (0x06 or 6). See the IANA's
 *         <a href="https://en.wikipedia.org/wiki/List_of_IP_protocol_numbers">list of protocol numbers</a>.
 *     </li>
 *     <li>
 *         <b>Header Checksum</b><br>
 *         The 16-bit checksum of the contents of the IPv4 header. When a packet reaches a router, it recalculates the
 *         checksum and discards the packet if the checksums do not match. The checksum does not include the payload.
 *         Other protocols (such as UDP and TCP) may include separate checksums in their headers as well. This library
 *         or the underlying socket layer (depending on mode of operation) calculates the checksum for you
 *         automatically and does not permit this value to be manually set.
 *     </li>
 *     <li>
 *         <b>Source IPv4 Address</b><br>
 *         The origin IPv4 address, which may be modified in transit by routers performing Network Address Translation.
 *     </li>
 *     <li>
 *         <b>Destination IPv4 Address</b><br>
 *         The destination IPv4 address, which may be modified in transit by routers performing Network Address
 *         Translation.
 *     </li>
 *     <li>
 *         <b>Options</b><br>
 *         The options field has varying purposes and is not often used. This library does not support any particular
 *         options but does permit you to change the raw data of this field. See
 *         <a href="https://en.wikipedia.org/wiki/IPv4#Options">here</a> for more information about how to use this.
 *     </li>
 * </ul>
 * <br>
 * (Source: https://en.wikipedia.org/wiki/IPv4#Header)
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public class GenericIp4Packet
{
    /**
     * Constructor.
     */
    public GenericIp4Packet()
    {

    }
}

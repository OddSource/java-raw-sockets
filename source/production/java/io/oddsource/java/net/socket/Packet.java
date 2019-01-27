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

import java.net.InetAddress;

import io.oddsource.java.net.socket.exception.FinalizedPacketException;
import io.oddsource.java.net.socket.exception.IllegalHopLimitException;

/**
 * Specifies a strict interface and behavioral contract for all packet objects. The behavior defined in the comments
 * for each method should be adhered to strictly to fulfill this contract. More generally, the following rule should
 * be observed when any new mutator (setter) methods are added in any future implementations:<br />
 * <br />
 * If {@link #finalizePacket()} has previously been called, a {@link FinalizedPacketException} must be thrown. The
 * only exception to this is {@link #setSourceAddress(InetAddress)}, which should only throw this exception for
 * {@link Packet.Source#INCOMING} packets that have been finalized.<br />
 * <br />
 * The default hop limit (IPv6) or time-to-live/TTL (IPv4) should be {@link #DEFAULT_HOP_LIMIT} if not otherwise
 * specified.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Packet
{
    /**
     * The minimum allowed hop limit.
     */
    public static final short MIN_HOP_LIMIT = 1;

    /**
     * The default hop limit.
     */
    public static final short DEFAULT_HOP_LIMIT = 60;

    /**
     * The maximum allowed hop limit.
     */
    public static final short MAX_HOP_LIMIT = 255;

    /**
     * Returns the entire packet represented in bytes. To fulfill the contract for this method, the implementing class
     * should return the concatenated return values from (1) {@link #getHeaderData()} and (2) {@link #getPayloadData()}.
     *
     * @return the entire packet (header followed by content).
     */
    public abstract byte[] getPacketData();

    /**
     * Gets the content of the packet IP header. A copy of the internal array, if one exists, must be made to avoid
     * external modification.
     *
     * @return the content of the packet IP header.
     */
    public abstract byte[] getHeaderData();

    /**
     * Gets the content of the packet payload. A copy of the internal array, if one exists, must be made to avoid
     * external modification.
     *
     * @return the content of the packet payload.
     */
    public abstract byte[] getPayloadData();

    /**
     * Sets the content of the packet payload. A copy of the array argument must be made to avoid external modification.
     *
     * @param payloadData The content of the packet payload
     *
     * @throws FinalizedPacketException if this packet was finalized prior to the invocation of this method.
     */
    public abstract void setPayloadData(byte[] payloadData) throws FinalizedPacketException;

    /**
     * Gets the total packet length (header + payload).
     *
     * @return the packet length, in bytes.
     */
    public abstract int getPacketLength();

    /**
     * Gets the total header length.
     *
     * @return the IP header length, in bytes.
     */
    public abstract int getHeaderLength();

    /**
     * Gets the payload length.
     *
     * @return the payload length, in bytes.
     */
    public abstract int getPayloadLength();

    /**
     * Gets the hop limit (IPv6 terminology) or the Time-To-Live (IPv4 terminology). By whatever term, this is
     * (unofficially in IPv4) the maximum number of hops the packet is allowed to make to its destination before a TTL
     * exceeded message should be returned. This value will be between 1 and 255, but is represented as a short due to
     * the absence of unsigned numbers in Java.
     *
     * @return the hop limit / TTL.
     */
    public abstract short getHopLimit();

    /**
     * Sets the hop limit (IPv6 terminology) or the Time-To-Live (IPv4 terminology). By whatever term, this is
     * (unofficially in IPv4) the maximum number of hops the packet is allowed to make to its destination before a TTL
     * exceeded message should be returned. This value must be between 1 and 255 (and the implementing class should
     * throw an IllegalHopLimitException otherwise), but is represented as a short due to the absence of unsigned
     * numbers in Java.
     *
     * @param hopLimit The hop limit / TTL
     *
     * @throws FinalizedPacketException if this packet was finalized prior to the invocation of this method.
     * @throws IllegalHopLimitException if {@code hopLimit} was not an integer between 1 and 255 (inclusive).
     */
    public abstract void setHopLimit(short hopLimit) throws FinalizedPacketException, IllegalHopLimitException;

    /**
     * Gets the packet source address. In many operating systems, you cannot specify a source address other than the IP
     * address of the interface the packet is leaving the machine through. In some operating systems, the transmission
     * will be prohibited; in others, the source address will simply be overridden (causing the checksum to fail
     * verification).<br />
     * <br />
     * This field only exists for the purpose of representing incoming packets. For the purpose of outgoing packets,
     * the value of this field will be overwritten by the consumer of the packet.
     *
     * @return the source address that the packet originated from.
     */
    public abstract InetAddress getSourceAddress();

    /**
     * Sets the packet source address. In many operating systems, you cannot specify a source address other than the IP
     * address of the interface the packet is leaving the machine through. In some operating systems, the transmission
     * will be prohibited; in others, the source address will simply be overridden (causing the checksum to fail
     * verification).<br />
     * <br />
     * This field only exists for the purpose of representing incoming packets. For the purpose of outgoing packets,
     * the value of this field will be overwritten by the consumer of the packet.
     *
     * @param sourceAddress The source address that the packet originated from
     *
     * @return {@code true} if this packet is of source {@link Source#INCOMING} and setting the address is allowed,
     *     {@code false} if this packet is of source {@link Source#OUTGOING} and setting the address is not allowed.
     *
     * @throws FinalizedPacketException if this packet is of source {@link Source#INCOMING} and was finalized prior
     *     to the invocation of this method.
     */
    public abstract boolean setSourceAddress(InetAddress sourceAddress) throws FinalizedPacketException;

    /**
     * Gets the packet destination address.
     *
     * @return the packet destination address.
     */
    public abstract InetAddress getDestinationAddress();

    /**
     * Gets the packet destination address.
     *
     * @param destinationAddress The packet destination address
     *
     * @throws FinalizedPacketException if this packet was finalized prior to the invocation of this method.
     */
    public abstract void setDestinationAddress(InetAddress destinationAddress) throws FinalizedPacketException;

    /**
     * Protects this packet against future modification. This method will be called when an incoming packet has been
     * received and parsing completed. If this packet has already been finalized, this method should be a no-op.
     */
    public abstract void finalizePacket();

    /**
     * Indicates whether this packed has been protected against future modification.
     *
     * @return {@code true} if this packet has been protected, {@code false} otherwise.
     */
    public abstract boolean isFinalized();

    /**
     * Gets the source of this packet (incoming or outgoing). To fulfill the contract of this interface, the source of
     * the packet should be set in the packet constructor and should never again be changed (it should be a final
     * field).
     *
     * @return the source of this packet.
     */
    public abstract Packet.Source getSource();

    /**
     * The source of the packet, indicating whether it is incoming (it has been received) or outgoing (it is meant for
     * transmission).
     */
    public static enum Source
    {
        /**
         * This is an incoming packet that has been received.
         */
        INCOMING,

        /**
         * This is an outgoing packet that is to be sent.
         */
        OUTGOING,
    }
}

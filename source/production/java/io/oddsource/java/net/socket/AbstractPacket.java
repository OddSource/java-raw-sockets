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
import java.util.Arrays;

import io.oddsource.java.net.socket.exception.FinalizedPacketException;
import io.oddsource.java.net.socket.exception.IllegalHopLimitException;

/**
 * An abstract implementation of {@link Packet} that implements common methods and/or methods that should not be
 * implemented otherwise.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractPacket implements Packet
{
    private final Packet.Source source;

    private boolean finalized;

    private short hopLimit = Packet.DEFAULT_HOP_LIMIT;

    private InetAddress sourceAddress;

    private InetAddress destinationAddress;

    /**
     * Constructor.
     *
     * @param source The source of this packet.
     */
    public AbstractPacket(final Packet.Source source)
    {
        this.source = source;
    }

    /**
     * Returns the entire packet represented in bytes. To fulfill the contract for this method, the implementing class
     * should return the concatenated return values from (1) {@link #getHeaderData()} and (2) {@link #getPayloadData()}.
     *
     * @return the entire packet (header followed by content).
     */
    @Override
    public byte[] getPacketData()
    {
        final byte[] header = this.getHeaderData();
        final byte[] payload = this.getPayloadData();

        final byte[] data = Arrays.copyOf(header, header.length + payload.length);
        System.arraycopy(payload, 0, data, header.length, payload.length);
        return data;
    }

    /**
     * Gets the total packet length (header + payload).
     *
     * @return the packet length, in bytes.
     */
    @Override
    public int getPacketLength()
    {
        return this.getHeaderLength() + this.getPayloadLength();
    }

    /**
     * Gets the hop limit (IPv6 terminology) or the Time-To-Live (IPv4 terminology). By whatever term, this is
     * (unofficially in IPv4) the maximum number of hops the packet is allowed to make to its destination before a TTL
     * exceeded message should be returned. This value will be between 1 and 255, but is represented as a short due to
     * the absence of unsigned numbers in Java.
     *
     * @return the hop limit / TTL.
     */
    @Override
    public final short getHopLimit()
    {
        return this.hopLimit;
    }

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
    @Override
    public final void setHopLimit(final short hopLimit) throws FinalizedPacketException, IllegalHopLimitException
    {
        if(this.isFinalized())
        {
            throw new FinalizedPacketException();
        }
        if(hopLimit < Packet.MIN_HOP_LIMIT || hopLimit > Packet.MAX_HOP_LIMIT)
        {
            throw new IllegalHopLimitException();
        }

        this.hopLimit = hopLimit;
    }

    /**
     * Protects this packet against future modification. This method will be called when an incoming packet has been
     * received and parsing completed. If this packet has already been finalized, this method should be a no-op.
     */
    @Override
    public final void finalizePacket()
    {
        this.finalized = true;
    }

    /**
     * Indicates whether this packed has been protected against future modification.
     *
     * @return {@code true} if this packet has been protected, {@code false} otherwise.
     */
    @Override
    public final boolean isFinalized()
    {
        return this.finalized;
    }

    /**
     * Gets the source of this packet (incoming or outgoing). To fulfill the contract of this interface, the source of
     * the packet should be set in the packet constructor and should never again be changed (it should be a final
     * field).
     *
     * @return the source of this packet.
     */
    @Override
    public final Source getSource()
    {
        return this.source;
    }

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
    @Override
    public final InetAddress getSourceAddress()
    {
        return this.sourceAddress;
    }

    /**
     * Sets the packet source address. In many operating systems, you cannot specify a source address other than the IP
     * address of the interface the packet is leaving the machine through. In some operating systems, the transmission
     * will be prohibited; in others, the source address will simply be overridden (causing the checksum to fail
     * verification).<br />
     * <br />
     * This field only exists for the purpose of representing incoming packets. For the purpose of outgoing packets,
     * the value of this field will be overwritten by the consumer of the packet.<br />
     * <br />
     * Subclasses <em>are strongly encouraged</em> to override this method and check that {@code sourceAddress} is-a
     * {@link java.net.Inet4Address} for IPv4 packets and is-a {@link java.net.Inet6Address} for IPv6 packets.
     *
     * @param sourceAddress The source address that the packet originated from
     *
     * @return {@code true} if this packet is of source {@link io.oddsource.java.net.socket.Packet.Source#INCOMING} and
     *     setting the address is allowed, {@code false} if this packet is of source {@link
     *     io.oddsource.java.net.socket.Packet.Source#OUTGOING} and setting the address is not allowed.
     *
     * @throws FinalizedPacketException if this packet is of source
     * {@link io.oddsource.java.net.socket.Packet.Source#INCOMING}
     *     and was finalized prior to the invocation of this method.
     */
    @Override
    public boolean setSourceAddress(final InetAddress sourceAddress) throws FinalizedPacketException
    {
        if(this.source == Packet.Source.INCOMING)
        {
            if(this.isFinalized())
            {
                throw new FinalizedPacketException();
            }
            if(sourceAddress == null)
            {
                throw new IllegalArgumentException("Parameter sourceAddress cannot be null!");
            }

            this.sourceAddress = sourceAddress;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Gets the packet destination address.
     *
     * @return the packet destination address.
     */
    @Override
    public final InetAddress getDestinationAddress()
    {
        return this.destinationAddress;
    }

    /**
     * Sets the packet destination address.<br />
     * <br />
     * Subclasses <em>are strongly encouraged</em> to override this method and check that {@code destinationAddress}
     * is-a {@link java.net.Inet4Address} for IPv4 packets and is-a {@link java.net.Inet6Address} for IPv6 packets.
     *
     * @param destinationAddress The packet destination address
     *
     * @throws FinalizedPacketException if this packet was finalized prior to the invocation of this method.
     */
    @Override
    public void setDestinationAddress(final InetAddress destinationAddress) throws FinalizedPacketException
    {
        if(this.isFinalized())
        {
            throw new FinalizedPacketException();
        }
        if(destinationAddress == null)
        {
            throw new IllegalArgumentException("Parameter destinationAddress cannot be null!");
        }

        this.destinationAddress = destinationAddress;
    }
}

/*
 * CustomPacket.java from RawSockets modified Tuesday, June 28, 2011 14:08:57 CDT (-0500).
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

import io.oddsource.java.net.socket.exception.FinalizedPacketException;

import java.util.Arrays;

/**
 * A naïve implementation of {@link Packet} that uses the raw header and payload byte arrays and takes them for granted
 * as accurate. No checks are performed for correctness or size limitations. The source and destination addresses and
 * hop limit/TTL are completely ignored. The user <em>is strongly encouraged</em> to use or subclass one of the other
 * existing packet implementations. Imperfect use of this class could result in very error-prone code.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomPacket extends AbstractPacket
{
    private byte[] headerData = new byte[0];

    private byte[] payloadData = new byte[0];

    public CustomPacket(Packet.Source source)
    {
        super(source);
    }

    /**
     * Gets the content of the packet IP header. A copy of the internal array is made to avoid external modification.
     *
     * @return the content of the packet IP header.
     */
    @Override
    public byte[] getHeaderData()
    {
        return Arrays.copyOf(this.headerData, this.headerData.length);
    }

    /**
     * Sets the content of the packet IP header. A copy of the array argument is made to avoid external modification.
     *
     * @param headerData The content of the packet IP header
     * @throws FinalizedPacketException if this packet was finalized prior to the invocation of this method.
     */
    public void setHeaderData(byte[] headerData) throws FinalizedPacketException
    {
        if(this.isFinalized())
            throw new FinalizedPacketException();

        this.headerData = Arrays.copyOf(headerData, headerData.length);
    }

    /**
     * Gets the content of the packet payload. A copy of the internal array is made to avoid external modification.
     *
     * @return the content of the packet payload.
     */
    @Override
    public byte[] getPayloadData()
    {
        return Arrays.copyOf(this.payloadData, this.payloadData.length);
    }

    /**
     * Sets the content of the packet payload. A copy of the array argument is made to avoid external modification.
     *
     * @param payloadData The content of the packet payload
     * @throws FinalizedPacketException if this packet was finalized prior to the invocation of this method.
     */
    @Override
    public void setPayloadData(byte[] payloadData) throws FinalizedPacketException
    {
        if(this.isFinalized())
            throw new FinalizedPacketException();

        this.payloadData = Arrays.copyOf(payloadData, payloadData.length);
    }

    /**
     * Gets the total header length.
     *
     * @return the IP header length, in bytes.
     */
    @Override
    public int getHeaderLength()
    {
        return this.headerData.length;
    }

    /**
     * Gets the payload length();
     *
     * @return the payload length, in bytes.
     */
    @Override
    public int getPayloadLength()
    {
        return this.payloadData.length;
    }
}

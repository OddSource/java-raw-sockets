/*
 * TestAbstractPacket.java from RawSockets modified Tuesday, June 28, 2011 16:01:33 CDT (-0500).
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
import io.oddsource.java.net.socket.exception.IllegalHopLimitException;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

/**
 * Test class for AbstractPacket.
 */
public class TestAbstractPacket
{
    private AbstractPacket packet;

    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {
        EasyMock.verify(this.packet);
    }

    private void setUpPacket()
    {
        this.setUpPacket(Packet.Source.OUTGOING);
    }

    private void setUpPacket(Packet.Source source)
    {
        // we create a mock instead of a strict mock because all of our invocations are order indifferent,
        // but we don't want unexpected invocations that the nice mock would allow
        this.packet = EasyMock.createMockBuilder(AbstractPacket.class).withConstructor(source).createMock();
    }

    @Test
    public void testConstructor01()
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        assertNotNull("The packet should not be null.", this.packet);
        assertSame("The packet source is not correct.", Packet.Source.INCOMING, this.packet.getSource());
    }

    @Test
    public void testConstructor02()
    {
        this.setUpPacket(Packet.Source.OUTGOING);

        EasyMock.replay(this.packet);

        assertNotNull("The packet should not be null.", this.packet);
        assertSame("The packet source is not correct.", Packet.Source.OUTGOING, this.packet.getSource());
    }

    @Test
    public void testGetPacketData01()
    {
        this.setUpPacket();

        EasyMock.expect(this.packet.getHeaderData()).andReturn(new byte[] {0x01, 0x05, 0x15, 0x01, 0x00, 0x79});
        EasyMock.expect(this.packet.getPayloadData()).andReturn(new byte[] {0x03, 0x04, 0x05, 0x06});

        EasyMock.replay(this.packet);

        byte[] data = this.packet.getPacketData();

        assertNotNull("The data should not be null.", data);
        assertArrayEquals("The data is not correct.", new byte[] {0x01, 0x05, 0x15, 0x01, 0x00, 0x79, 0x03, 0x04, 0x05, 0x06}, data);
    }

    @Test
    public void testGetPacketData02()
    {
        this.setUpPacket();

        EasyMock.expect(this.packet.getPayloadData()).andReturn(new byte[] {0x01, 0x05, 0x15, 0x01, 0x00, 0x79});
        EasyMock.expect(this.packet.getHeaderData()).andReturn(new byte[] {0x03, 0x04, 0x05, 0x06});

        EasyMock.replay(this.packet);

        byte[] data = this.packet.getPacketData();

        assertNotNull("The data should not be null.", data);
        assertArrayEquals("The data is not correct.", new byte[] {0x03, 0x04, 0x05, 0x06, 0x01, 0x05, 0x15, 0x01, 0x00, 0x79}, data);
    }

    @Test
    public void testGetPacketLength01()
    {
        this.setUpPacket();

        EasyMock.expect(this.packet.getHeaderLength()).andReturn(52);
        EasyMock.expect(this.packet.getPayloadLength()).andReturn(13);

        EasyMock.replay(this.packet);

        assertEquals("The length is not correct.", 52 + 13, this.packet.getPacketLength());
    }

    @Test
    public void testGetPacketLength02()
    {
        this.setUpPacket();

        EasyMock.expect(this.packet.getPayloadLength()).andReturn(91);
        EasyMock.expect(this.packet.getHeaderLength()).andReturn(44);

        EasyMock.replay(this.packet);

        assertEquals("The length is not correct.", 91 + 44, this.packet.getPacketLength());
    }

    @Test
    public void testFinalizePacket01()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        assertFalse("The packet should not be finalized yet.", this.packet.isFinalized());

        this.packet.finalizePacket();

        assertTrue("The packet should be finalized now.", this.packet.isFinalized());

        this.packet.finalizePacket();

        assertTrue("The packet should still be finalized.", this.packet.isFinalized());
    }

    @Test(expected=IllegalHopLimitException.class)
    public void testHopLimit01()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        this.packet.setHopLimit((short)(Packet.MIN_HOP_LIMIT - 1));
    }

    @Test(expected=IllegalHopLimitException.class)
    public void testHopLimit02()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        this.packet.setHopLimit((short)(Packet.MAX_HOP_LIMIT + 1));
    }

    @Test(expected=FinalizedPacketException.class)
    public void testHopLimit03()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        this.packet.finalizePacket();
        this.packet.setHopLimit(Packet.MAX_HOP_LIMIT);
    }

    @Test
    public void testHopLimit04()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        this.packet.setHopLimit(Packet.MAX_HOP_LIMIT);

        assertEquals("The hop limit is not correct.", Packet.MAX_HOP_LIMIT, this.packet.getHopLimit());
    }

    @Test
    public void testHopLimit05()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        this.packet.setHopLimit(Packet.MIN_HOP_LIMIT);

        assertEquals("The hop limit is not correct.", Packet.MIN_HOP_LIMIT, this.packet.getHopLimit());
    }

    @Test
    public void testHopLimit06()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        this.packet.setHopLimit(Packet.DEFAULT_HOP_LIMIT);

        assertEquals("The hop limit is not correct.", Packet.DEFAULT_HOP_LIMIT, this.packet.getHopLimit());
    }

    @Test
    public void testHopLimit07()
    {
        this.setUpPacket();

        EasyMock.replay(this.packet);

        this.packet.setHopLimit((short)97);

        assertEquals("The hop limit is not correct.", 97, this.packet.getHopLimit());
    }

    @Test(expected=FinalizedPacketException.class)
    public void testSourceAddress01() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.finalizePacket();
        this.packet.setSourceAddress(InetAddress.getByAddress(new byte[] {0x04, 0x02, 0x02, 0x02})); // 4.2.2.2
    }

    @Test(expected=FinalizedPacketException.class)
    public void testSourceAddress02() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.finalizePacket();
        this.packet.setSourceAddress(InetAddress.getLocalHost()); // 127.0.0.1 or 0::0
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSourceAddress03()
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.setSourceAddress(null);
    }

    @Test
    public void testSourceAddress04() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.OUTGOING);

        EasyMock.replay(this.packet);

        assertFalse("Setting should have failed.",
                    this.packet.setSourceAddress(InetAddress.getByAddress(new byte[] {0x04, 0x02, 0x02, 0x02}))
        ); // 4.2.2.2
    }

    @Test
    public void testSourceAddress05() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.OUTGOING);

        EasyMock.replay(this.packet);

        assertFalse("Setting should have failed.",
                    this.packet.setSourceAddress(InetAddress.getLocalHost())
        ); // 127.0.0.1 or 0::0
    }

    @Test
    public void testSourceAddress06()
    {
        this.setUpPacket(Packet.Source.OUTGOING);

        EasyMock.replay(this.packet);

        assertFalse("Setting should have failed.", this.packet.setSourceAddress(null));
    }

    @Test
    public void testSourceAddress07() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        assertTrue("Setting should have succeeded.",
                    this.packet.setSourceAddress(InetAddress.getByAddress(new byte[] {0x04, 0x02, 0x02, 0x02}))
        ); // 4.2.2.2
        assertEquals("The address is not correct.", InetAddress.getByAddress(new byte[] {0x04, 0x02, 0x02, 0x02}),
                     this.packet.getSourceAddress());
    }

    @Test
    public void testSourceAddress08() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        assertTrue("Setting should have succeeded.",
                    this.packet.setSourceAddress(InetAddress.getLocalHost())
        ); // 127.0.0.1 or 0::0
        assertEquals("The address is not correct.", InetAddress.getLocalHost(), this.packet.getSourceAddress());
    }

    @Test(expected=FinalizedPacketException.class)
    public void testDestinationAddress01() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.finalizePacket();
        this.packet.setDestinationAddress(InetAddress.getByAddress(new byte[] {0x04, 0x02, 0x02, 0x02})); // 4.2.2.2
    }

    @Test(expected=FinalizedPacketException.class)
    public void testDestinationAddress02() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.finalizePacket();
        this.packet.setDestinationAddress(InetAddress.getLocalHost()); // 127.0.0.1 or 0::0
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDestinationAddress03()
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.setDestinationAddress(null);
    }

    @Test
    public void testDestinationAddress04() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.setDestinationAddress(InetAddress.getByAddress(new byte[] {0x04, 0x02, 0x02, 0x02})); // 4.2.2.2
        assertEquals("The address is not correct.", InetAddress.getByAddress(new byte[] {0x04, 0x02, 0x02, 0x02}),
                     this.packet.getDestinationAddress());
    }

    @Test
    public void testDestinationAddress05() throws UnknownHostException
    {
        this.setUpPacket(Packet.Source.INCOMING);

        EasyMock.replay(this.packet);

        this.packet.setDestinationAddress(InetAddress.getLocalHost()); // 127.0.0.1 or 0::0
        assertEquals("The address is not correct.", InetAddress.getLocalHost(), this.packet.getDestinationAddress());
    }
}

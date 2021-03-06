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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.oddsource.java.net.socket.exception.FinalizedPacketException;

/**
 * Test class for CustomPacket.
 */
public class TestCustomPacket
{
    private CustomPacket packet;

    @Before
    public void setUp()
    {
        this.packet = new CustomPacket(Packet.Source.OUTGOING);
    }

    @After
    public void tearDown()
    {

    }

    @Test(expected = FinalizedPacketException.class)
    public void testSetHeaderData01()
    {
        this.packet.finalizePacket();
        this.packet.setHeaderData(new byte[] {});
    }

    @Test
    public void testHeaderDataAndLength01()
    {
        this.packet.setHeaderData(new byte[] {0x01, 0x00, 0x05, 0x15, 0x79, 0x01});
        assertArrayEquals("The header data is not correct.", new byte[] {0x01, 0x00, 0x05, 0x15, 0x79, 0x01},
                          this.packet.getHeaderData()
        );
        assertEquals("The header length is not correct.", 6, this.packet.getHeaderLength());
    }

    @Test
    public void testHeaderDataAndLength02()
    {
        this.packet.setHeaderData(new byte[] {0x05, 0x01, 0x00, 0x79, 0x01});
        assertArrayEquals("The header data is not correct.", new byte[] {0x05, 0x01, 0x00, 0x79, 0x01},
                          this.packet.getHeaderData()
        );
        assertEquals("The header length is not correct.", 5, this.packet.getHeaderLength());
    }

    @Test(expected = FinalizedPacketException.class)
    public void testSetPayloadData()
    {
        this.packet.finalizePacket();
        this.packet.setPayloadData(new byte[] {});
    }

    @Test
    public void testPayloadDataAndLength01()
    {
        this.packet.setPayloadData(new byte[] {0x01, 0x00, 0x05, 0x15, 0x79, 0x01});
        assertArrayEquals("The header data is not correct.", new byte[] {0x01, 0x00, 0x05, 0x15, 0x79, 0x01},
                          this.packet.getPayloadData()
        );
        assertEquals("The header length is not correct.", 6, this.packet.getPayloadLength());
    }

    @Test
    public void testPayloadDataAndLength02()
    {
        this.packet.setPayloadData(new byte[] {0x05, 0x01, 0x00, 0x79, 0x01});
        assertArrayEquals("The header data is not correct.", new byte[] {0x05, 0x01, 0x00, 0x79, 0x01},
                          this.packet.getPayloadData()
        );
        assertEquals("The header length is not correct.", 5, this.packet.getPayloadLength());
    }
}

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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for .
 */
public class TestSocketUtilities
{
    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testConstructionProhibited()
        throws NoSuchMethodException, IllegalAccessException, InstantiationException
    {
        Constructor<SocketUtilities> constructor = SocketUtilities.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        try
        {
            constructor.newInstance();
            fail("Expected exception " + UnsupportedOperationException.class);
        }
        catch(InvocationTargetException e)
        {
            Throwable cause = e.getCause();
            assertNotNull("There should be a call for exception " + e.toString(), cause);
            assertEquals("The cause is not correct.", UnsupportedOperationException.class, cause.getClass());
            assertEquals(
                "The exception message is not correct.",
                "This class is not meant to be instantiated.",
                cause.getMessage()
            );
        }
    }

    @Test
    public void testGetProtocolByName01()
    {
        Protocol protocol = SocketUtilities.getProtocolByName("ip");

        assertNotNull("There should be an IP utility.", protocol);
        assertEquals("The protocol does not match.", "ip", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 0, protocol.getProtocolNumber());

        System.out.println("IP protocol: " + protocol);
    }

    @Test
    public void testGetProtocolByName02()
    {
        Protocol protocol = SocketUtilities.getProtocolByName("tcp");

        assertNotNull("There should be an TCP utility.", protocol);
        assertEquals("The protocol does not match.", "tcp", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 6, protocol.getProtocolNumber());
    }

    @Test
    public void testGetProtocolByName03()
    {
        Protocol protocol = SocketUtilities.getProtocolByName("udp");

        assertNotNull("There should be an UDP utility.", protocol);
        assertEquals("The protocol does not match.", "udp", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 17, protocol.getProtocolNumber());
    }

    @Test
    public void testGetProtocolByName04()
    {
        Protocol protocol = SocketUtilities.getProtocolByName("icmp");

        assertNotNull("There should be an ICMP utility.", protocol);
        assertEquals("The protocol does not match.", "icmp", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 1, protocol.getProtocolNumber());
    }

    @Test
    public void testGetProtocolByName05()
    {
        Protocol protocol = SocketUtilities.getProtocolByName("thisIsABadProtocol");

        assertNull("Protocol should not exist.", protocol);
    }

    @Test
    public void testGetProtocolByNumber01()
    {
        Protocol protocol = SocketUtilities.getProtocolByNumber(0);

        assertNotNull("Looking up the IP protocol should have succeeded.", protocol);
        assertEquals("The protocol does not match.", "ip", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 0, protocol.getProtocolNumber());
    }

    @Test
    public void testGetProtocolByNumber02()
    {
        Protocol protocol = SocketUtilities.getProtocolByNumber(6);

        assertNotNull("Looking up the TCP protocol should have succeeded.", protocol);
        assertEquals("The protocol does not match.", "tcp", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 6, protocol.getProtocolNumber());
    }

    @Test
    public void testGetProtocolByNumber03()
    {
        Protocol protocol = SocketUtilities.getProtocolByNumber(17);

        assertNotNull("Looking up the UDP protocol should have succeeded.", protocol);
        assertEquals("The protocol does not match.", "udp", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 17, protocol.getProtocolNumber());
    }

    @Test
    public void testGetProtocolByNumber04()
    {
        Protocol protocol = SocketUtilities.getProtocolByNumber(1);

        assertNotNull("Looking up the ICMP protocol should have succeeded.", protocol);
        assertEquals("The protocol does not match.", "icmp", protocol.getName().toLowerCase());
        assertNotNull("The list of aliases should not be null.", protocol.getAliases());
        assertEquals("The protocol number is not correct.", 1, protocol.getProtocolNumber());
    }

    @Test
    public void testGetProtocolByNumber05()
    {
        Protocol protocol = SocketUtilities.getProtocolByNumber(1234987234);

        assertNull("This protocol should not exist.", protocol);
    }

    @Test
    public void testGetProtocolList01()
    {
        Protocol[] protocols = SocketUtilities.getProtocolList();

        assertNotNull("The list of protocols should not be null.", protocols);
        assertThat("The list of protocols is not long enough.", protocols.length, is(greaterThan(3)));

        boolean foundIp = false;
        boolean foundTcp = false;
        boolean foundUdp = false;
        boolean foundIcmp = false;

        for(Protocol protocol : protocols)
        {
            if(protocol.getName().equalsIgnoreCase("ip"))
            {
                foundIp = true;
            }
            else if(protocol.getName().equalsIgnoreCase("tcp"))
            {
                foundTcp = true;
            }
            else if(protocol.getName().equalsIgnoreCase("udp"))
            {
                foundUdp = true;
            }
            else if(protocol.getName().equalsIgnoreCase("icmp"))
            {
                foundIcmp = true;
            }
        }

        assertTrue("The IP protocol should be in the list.", foundIp);
        assertTrue("The TCP protocol should be in the list.", foundTcp);
        assertTrue("The UDP protocol should be in the list.", foundUdp);
        assertTrue("The ICMP protocol should be in the list.", foundIcmp);
    }
}

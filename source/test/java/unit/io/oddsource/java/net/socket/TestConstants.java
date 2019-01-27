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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.oddsource.java.net.socket.exception.SocketConstantNotDefinedException;

/**
 * Test class for Constants.
 */
public class TestConstants
{
    @BeforeClass
    public static void setUpClass()
    {
        System.out.println("Property java.library.path is set to \"" + System.getProperty("java.library.path") + "\"");
    }

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
        Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        try
        {
            constructor.newInstance();
            fail("Expected exception " + AssertionError.class);
        }
        catch(InvocationTargetException e)
        {
            Throwable cause = e.getCause();
            assertNotNull("There should be a call for exception " + e.toString(), cause);
            assertEquals("The cause is not correct.", AssertionError.class, cause.getClass());
            assertEquals(
                "The exception message is not correct.",
                "This class is not meant to be instantiated.",
                cause.getMessage()
            );
        }
    }

    @Test
    public void testSocketConstantNotDefinedException()
        throws NoSuchMethodException, IllegalAccessException
    {
        Method method = Constants.class.getDeclaredMethod("registerNumericConstant", String.class);
        method.setAccessible(true);

        try
        {
            method.invoke(null, "BAD_CONSTANT");
            fail("Expected exception " + SocketConstantNotDefinedException.class.getCanonicalName());
        }
        catch(InvocationTargetException e)
        {
            Throwable cause = e.getCause();
            assertNotNull("There should be a call for exception " + e.toString(), cause);
            assertEquals("The cause is not correct.", SocketConstantNotDefinedException.class, cause.getClass());
            assertEquals(
                "The exception message is not correct.",
                "The specified constant, BAD_CONSTANT, is not natively defined.",
                cause.getMessage()
            );
        }
    }

    @Test
    public void test_IP_OPTIONS()
    {
        assertNotEquals("The constant IP_OPTIONS is not correct.", Constants.IP_OPTIONS, 0);
    }

    @Test
    public void test_IP_HDRINCL()
    {
        assertNotEquals("The constant IP_HDRINCL is not correct.", Constants.IP_HDRINCL, 0);
    }
}

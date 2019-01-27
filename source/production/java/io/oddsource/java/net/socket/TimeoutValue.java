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

/**
 * A simple object representing timeouts in a helpful way..
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeoutValue
{
    private static final short MULTIPLIER = 1000;

    private int seconds;

    private int microseconds;

    /**
     * Constructor.
     */
    public TimeoutValue()
    {
        this.seconds = 0;
        this.microseconds = 0;
    }

    /**
     * Set the timeout by milliseconds.
     *
     * @param milliseconds The number of milliseconds
     */
    public void setByMilliseconds(final int milliseconds)
    {
        this.seconds = milliseconds / TimeoutValue.MULTIPLIER;

        int leftover = milliseconds;
        if(this.seconds > 0)
        {
            leftover -= this.seconds * TimeoutValue.MULTIPLIER;
        }

        this.microseconds = leftover * TimeoutValue.MULTIPLIER;
    }

    /**
     * Get the timeout represented in milliseconds.
     *
     * @return the timeout in milliseconds.
     */
    public int getInMilliseconds()
    {
        return (this.seconds * TimeoutValue.MULTIPLIER) + (this.microseconds / TimeoutValue.MULTIPLIER);
    }

    /**
     * Get the seconds component of the timeout.
     *
     * @return the timeout seconds.
     */
    public int getSeconds()
    {
        return this.seconds;
    }

    /**
     * Get the microseconds component of the timeout.
     *
     * @return the timeout microseconds.
     */
    public int getMicroseconds()
    {
        return this.microseconds;
    }

    /**
     * Determine whether the timeout is zero.
     *
     * @return whether the timeout is zero.
     */
    boolean isZero()
    {
        return this.seconds == 0 && this.microseconds == 0;
    }
}

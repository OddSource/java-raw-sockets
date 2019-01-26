/*
 * TimeoutValue.java from RawSockets modified Wednesday, July 6, 2011 17:55:29 CDT (-0500).
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

/**
 * Class description here.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeoutValue
{
	private int seconds;

	private int microseconds;

	public TimeoutValue()
	{
		this.seconds = this.microseconds = 0;
	}

	public void setByMilliseconds(int milliseconds)
	{
		this.seconds = milliseconds / 1000;

		if(this.seconds > 0)
		{
			milliseconds -= this.seconds * 1000;
		}

		this.microseconds = milliseconds * 1000;
	}

	public int getInMilliseconds()
	{
		return (this.seconds * 1000) + (this.microseconds / 1000);
	}

	public int getSeconds()
	{
		return this.seconds;
	}

	public int getMicroseconds()
	{
		return this.microseconds;
	}

	boolean isZero()
	{
		return this.seconds == 0 && this.microseconds == 0;
	}
}

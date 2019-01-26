/*
 * IpVersion.java from RawSockets modified Tuesday, July 5, 2011 12:16:14 CDT (-0500).
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
 * An enumeration to indicate which Internet protocol version is being used.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public enum IpVersion
{
    IPv4(Constants.PF_INET, Constants.AF_INET),
    IPv6(Constants.PF_INET6, Constants.AF_INET6);

    private final int packetFormatFamily;

    private final int addressFormatFamily;

    IpVersion(final int packetFormatFamily, final int addressFormatFamily)
    {
        this.packetFormatFamily = packetFormatFamily;
        this.addressFormatFamily = addressFormatFamily;
    }

    public int getPacketFormatFamily()
    {
        return this.packetFormatFamily;
    }

    public int getAddressFormatFamily()
    {
        return this.addressFormatFamily;
    }
}

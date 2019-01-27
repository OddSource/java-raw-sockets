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
 * An enumeration to indicate which Internet protocol version is being used.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public enum IpVersion
{
    /**
     * IP version 4, formatted 44.51.179.15.
     */
    IPv4(Constants.PF_INET, Constants.AF_INET),

    /**
     * IP version 6, formatted 2001:def2:3fa8:a2e8:eee1:000f:0000:1111.
     */
    IPv6(Constants.PF_INET6, Constants.AF_INET6);

    private final int packetFormatFamily;

    private final int addressFormatFamily;

    IpVersion(final int packetFormatFamily, final int addressFormatFamily)
    {
        this.packetFormatFamily = packetFormatFamily;
        this.addressFormatFamily = addressFormatFamily;
    }

    /**
     * Get the packet format family constant.
     *
     * @return the packet family format constant.
     */
    public int getPacketFormatFamily()
    {
        return this.packetFormatFamily;
    }

    /**
     * Get the address format family constant.
     *
     * @return the address format family constant.
     */
    public int getAddressFormatFamily()
    {
        return this.addressFormatFamily;
    }
}

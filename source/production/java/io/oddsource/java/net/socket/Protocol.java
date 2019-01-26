/*
 * Protocol.java from RawSockets modified Tuesday, July 5, 2011 16:52:40 CDT (-0500).
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

import java.util.Arrays;
import java.util.List;

/**
 * This POJO represents protocol information as retrieved from the underlying operating system.
 *
 * @author Nick Williams
 * @version 1.0.0
 * @since 1.0.0
 * @see SocketUtilities
 */
public final class Protocol
{
    private final String name;

    private final List<String> aliases;

    private final int protocolNumber;

    public Protocol(String name, String[] aliases, int protocolNumber)
    {
        this.name = name;
        this.aliases = Arrays.asList(aliases);
        this.protocolNumber = protocolNumber;
    }

    public String getName()
    {
        return this.name;
    }

    public List<String> getAliases()
    {
        return this.aliases;
    }

    public int getProtocolNumber()
    {
        return this.protocolNumber;
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder("{ name: \"");
        string.append(this.name).
                append( "\", aliases: ").
                append(this.aliases).
                append(", protocolNumber: ").
                append(this.protocolNumber).append(" }");

        return string.toString();
    }
}

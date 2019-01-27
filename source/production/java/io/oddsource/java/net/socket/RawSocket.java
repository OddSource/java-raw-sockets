/*
 * RawSocket.java from RawSockets modified Tuesday, July 5, 2011 07:17:45 CDT (-0500).
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
public interface RawSocket
{
    /**
     * Set the specified socket option at the given level to the given value.
     *
     * @param level The level to which the socket option applies, such as the socket itself, the IP layer, the UDP
     *     layer, etc.
     * @param option The option identifier (an integer, but be sure to always use one of the supported constants)
     * @param value The option value
     */
    public abstract void setSocketOption(SocketLevel level, int option, int value);

    /**
     * Get the specified socket option at the given level.
     *
     * @param level The level to which the socket option applies, such as the socket itself, the IP layer, the UDP
     *     layer, etc.
     * @param option The option identifier (an integer, but be sure to always use one of the supported constants)
     *
     * @return the option value.
     */
    public abstract int getSocketOption(SocketLevel level, int option);

    /**
     * Enable or disable use of the select timeout.
     *
     * @param useSelectTimeout Whether to use the select timeout
     */
    public abstract void setUseSelectTimeout(boolean useSelectTimeout);

    /**
     * Indicates whether this socket will use the select timeout.
     *
     * @return whether select timeouts will be used.
     */
    public abstract boolean getUseSelectTimeout();

    /**
     * Set the send timeout.
     *
     * @param milliseconds The send timeout in milliseconds
     */
    public abstract void setSendTimeout(int milliseconds);

    /**
     * Get the send timeout.
     *
     * @return the send timeout in milliseconds.
     */
    public abstract int getSendTimeout();

    /**
     * Set the receive timeout.
     *
     * @param milliseconds The receive timeout in milliseconds
     */
    public abstract void setReceiveTimeout(int milliseconds);

    /**
     * Get the receive timeout.
     *
     * @return the receive timeout in milliseconds.
     */
    public abstract int getReceiveTimeout();

    /**
     * Set the send buffer size.
     *
     * @param bytes The send buffer size in bytes
     */
    public abstract void setSendBufferSize(int bytes);

    /**
     * Get the send buffer size.
     *
     * @return the send buffer size in bytes.
     */
    public abstract int getSendBufferSize();

    /**
     * Set the receive buffer size.
     *
     * @param bytes The receive buffer size in bytes.
     */
    public abstract void setReceiveBufferSize(int bytes);

    /**
     * Get the receive buffer size.
     *
     * @return the receive buffer size in bytes.
     */
    public abstract int getReceiveBufferSize();

    /**
     * Set whether the IP header include is on.
     *
     * @param on Whether the IP header include is on
     */
    public abstract void setIpHeaderInclude(boolean on);

    /**
     * Determine whether the IP header include is on.
     *
     * @return whether the IP header include is on.
     */
    public abstract boolean getIpHeaderInclude();
}

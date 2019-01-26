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
    public void setSocketOption(SocketLevel level, int option, int value);

    public int getSocketOption(SocketLevel level, int option);

    public void setUseSelectTimeout(boolean useSelectTimeout);

    public boolean getUseSelectTimeout();

    public void setSendTimeout(int milliseconds);

    public int getSendTimeout();

    public void setReceiveTimeout(int milliseconds);

    public int getReceiveTimeout();

    public void setSendBufferSize(int bytes);

    public int getSendBufferSize();

    public void setReceiveBufferSize(int bytes);

    public int getReceiveBufferSize();

    public void setIpHeaderInclude(boolean on);

    public boolean getIpHeaderInclude();
}

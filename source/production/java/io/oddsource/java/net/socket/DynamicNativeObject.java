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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

import io.oddsource.java.net.socket.exception.RawSocketRuntimeException;

class DynamicNativeObject
{
    static final String OS_NAME = System.getProperty("os.name");

    static final boolean OS_IS_WINDOWS = OS_NAME.startsWith("Windows");

    static final boolean OS_IS_MAC = OS_NAME.startsWith("Mac");

    static final boolean OS_IS_LINUX = OS_NAME.toLowerCase(Locale.US).startsWith("Linux");

    // simplistic, might not be true 100% of the time, shouldn't matter for our purposes
    static final boolean OS_IS_UNIX = !OS_IS_WINDOWS;

    private static final String LIBRARY_NAME = "OddSourceRawSockets";

    private static final String LIBRARY_PREFIX_UNIX = "lib";

    private static final String LIBRARY_EXTENSION_NON_MAC_UNIX = ".so";

    private static final String LIBRARY_EXTENSION_MAC = ".dylib";

    private static final String LIBRARY_EXTENSION_WINDOWS = ".dll";

    private static final String LIBRARY_ACTUAL_NAME =
        OS_IS_WINDOWS ? LIBRARY_NAME : LIBRARY_PREFIX_UNIX + LIBRARY_NAME;

    private static final String LIBRARY_ACTUAL_EXTENSION =
        OS_IS_WINDOWS ?
        LIBRARY_EXTENSION_WINDOWS :
        (OS_IS_MAC ? LIBRARY_EXTENSION_MAC : LIBRARY_EXTENSION_NON_MAC_UNIX);

    static
    {
        final String resource = LIBRARY_ACTUAL_NAME + LIBRARY_ACTUAL_EXTENSION;

        try
        {
            final File tempFile = File.createTempFile(LIBRARY_ACTUAL_NAME, LIBRARY_ACTUAL_EXTENSION);
            tempFile.deleteOnExit();
            if(!tempFile.exists())
            {
                throw new RawSocketRuntimeException(
                    "Failed to create temporary file " + tempFile.getAbsolutePath() + "."
                );
            }

            final URL url = DynamicNativeObject.class.getResource(resource);
            if(url == null)
            {
                throw new RawSocketRuntimeException(
                    "Failed to get classpath resource " + resource + " for native library."
                );
            }

            try(final InputStream input = url.openStream())
            {
                Files.copy(input, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            System.load(tempFile.getAbsolutePath());
        }
        catch(final IOException e)
        {
            throw new RawSocketRuntimeException("Failed to load dynamic library " + resource + " due to error.", e);
        }
    }

    /**
     * Constructor.
     */
    protected DynamicNativeObject()
    {

    }
}

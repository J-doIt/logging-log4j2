/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.logging.log4j.core.jackson;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.logging.log4j.util.BiConsumer;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

/**
 * <p>
 * <em>Consider this class private.</em>
 * </p>
 */
public class ContextDataAsEntryListSerializer extends StdSerializer<ReadOnlyStringMap> {

    private static final long serialVersionUID = 1L;

    protected ContextDataAsEntryListSerializer() {
        super(Map.class, false);
    }

    @Override
    public void serialize(final ReadOnlyStringMap contextData, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException, JsonGenerationException {

        final MapEntry[] pairs = new MapEntry[contextData.size()];
        contextData.forEach(new BiConsumer<String, Object>() {
            int i = 0;

            @Override
            public void accept(final String key, final Object value) {
                pairs[i++] = new MapEntry(key, String.valueOf(value));
            }
        });
        jgen.writeObject(pairs);
    }
}

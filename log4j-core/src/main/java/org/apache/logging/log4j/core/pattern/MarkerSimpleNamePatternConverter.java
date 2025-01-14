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
package org.apache.logging.log4j.core.pattern;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.util.PerformanceSensitive;

/**
 * Appends an event's maker name to a StringBuilder.
 */
@Plugin(name = "MarkerNamePatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({ "markerSimpleName" })
@PerformanceSensitive("allocation")
public final class MarkerSimpleNamePatternConverter extends LogEventPatternConverter {

    /**
     * Private constructor.
     * @param options options, may be null.
     */
    private MarkerSimpleNamePatternConverter(final String[] options) {
        super("MarkerSimpleName", "markerSimpleName");
    }

    /**
     * Obtains an instance of pattern converter.
     *
     * @param options options, may be null.
     * @return instance of pattern converter.
     */
    public static MarkerSimpleNamePatternConverter newInstance(final String[] options) {
        return new MarkerSimpleNamePatternConverter(options);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final Marker marker = event.getMarker();
        if (marker != null) {
            toAppendTo.append(marker.getName());
        }
    }
}

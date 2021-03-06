/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.message;

import org.apache.logging.log4j.junit.Mutable;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests ReusableParameterizedMessage.
 */
public class ReusableParameterizedMessageTest {

    @Test
    public void testNoArgs() {
        final String testMsg = "Test message {}";
        ReusableParameterizedMessage msg = new ReusableParameterizedMessage();
        msg.set(testMsg, null);
        String result = msg.getFormattedMessage();
        assertEquals(testMsg, result);

        msg.set(testMsg, null, null);
        result = msg.getFormattedMessage();
        assertEquals("Test message null", result);
    }

    @Test
    public void testNotSafeWithMutableParams() {
        final String testMsg = "Test message {}";
        final Mutable param = new Mutable().set("abc");
        final ReusableParameterizedMessage msg = new ReusableParameterizedMessage();
        msg.set(testMsg, param);

        // modify parameter before calling msg.getFormattedMessage
        param.set("XYZ");
        final String actual = msg.getFormattedMessage();
        assertEquals("Should use current param value", "Test message XYZ", actual);

        // modify parameter after calling msg.getFormattedMessage
        param.set("000");
        final String after = msg.getFormattedMessage();
        assertEquals("Renders again", "Test message 000", after);
    }
}

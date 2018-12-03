/*
 * Copyright (C) 2017-2018 Dremio Corporation
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
package com.dremio.service.namespace;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dremio.service.namespace.dataset.proto.PartitionValue;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Serialization tests
 */
public class TestSerialization {

  private static void assertContains(String expectedContains, String string) {
    assertTrue(string + " should contain " + expectedContains, string.contains(expectedContains));
  }

  private static void assertNotContains(String expectedContains, String string) {
    assertTrue(string + " should not contain " + expectedContains, !string.contains(expectedContains));
  }

  @Test
  public void testNullValueSerialization() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    PartitionValue value = new PartitionValue();
    value.setColumn("test");
    value.setIntValue(10);
    String serialized = mapper.writeValueAsString(value);
    assertContains("intValue", serialized);
    assertNotContains("longValue", serialized);

    value = new PartitionValue();
    value.setColumn("test");
    serialized = mapper.writeValueAsString(value);
    assertNotContains("intValue", serialized);
  }
}

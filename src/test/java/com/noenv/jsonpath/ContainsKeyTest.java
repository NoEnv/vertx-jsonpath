package com.noenv.jsonpath;

import org.junit.Test;

import static com.noenv.jsonpath.JsonPath.containsKey;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsKeyTest extends TestBase {

  @Test
  public void testStringObj() {
    assertTrue(containsKey(obj, "$.name"));
    assertFalse(containsKey(obj, "$.lastName"));
    assertTrue(containsKey(obj, "$.contact.phoneNumbers.home"));
    assertFalse(containsKey(obj, "$.contact.phoneNumbers.holiday"));
    assertFalse(containsKey(obj, "$.foo.bar.eek.nagano"));
    assertTrue(containsKey(obj, "$.contact.officeHours[0]"));
    assertTrue(containsKey(obj, "$.contact.officeHours[1][0]"));
    assertFalse(containsKey(obj, "$.contact.officeHours[2]"));
    assertFalse(containsKey(obj, "$.contact.officeHours[2][3]"));
    assertTrue(containsKey(obj, "$.['nasty.dot.string']"));
  }

  @Test
  public void testStringArr() {
    assertTrue(containsKey(arr, "$[1].firstName"));
    assertTrue(containsKey(arr, "$[2]"));
    assertTrue(containsKey(arr, "$[1].meta.foo"));
    assertFalse(containsKey(arr, "$[17]"));
    assertFalse(containsKey(arr, "$[1].meta.eek"));
  }
}

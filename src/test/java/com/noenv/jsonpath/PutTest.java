package com.noenv.jsonpath;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import static com.noenv.jsonpath.JsonPath.*;
import static org.junit.Assert.assertEquals;

public class PutTest extends TestBase {

  @Test
  public void testStringObj() {
    assertEquals(obj, put(obj, "$.contact.phoneNumbers.home", "623-281-2911"));
    assertEquals("623-281-2911", getString(obj, "$.contact.phoneNumbers.home"));
    assertEquals(obj, put(obj, "$.contact.officeHours[1][1]", "15:00-17:00"));
    assertEquals("15:00-17:00", getString(obj, "$.contact.officeHours[1][1]"));
  }

  @Test
  public void testDotNotation() {
    assertEquals(obj, put(obj, "$.['nasty.dot.string']", "string"));
    assertEquals(obj, put(obj, "$.['nasty.dot.int']", 1234));
    assertEquals(obj, put(obj, "$.['nasty.dot.float']", 12.34));
    assertEquals(obj, put(obj, "$.['nasty.dot.boolean']", true));
    assertEquals(obj, put(obj, "$.['nasty.dot.obj']", new JsonObject().put("some", "thing")));
    assertEquals(obj, put(obj, "$.['nasty.dot.obj']", new JsonArray().add("some").add("thing")));
  }

  @Test
  public void testStringArr() {
    assertEquals(arr, put(arr, "$[0].lastName", "Mustermen"));
    assertEquals("Mustermen", getString(arr, "$[0].lastName"));
    assertEquals(arr, put(arr, "$[2]", "foobar"));
    assertEquals("foobar", arr.getString(2));
  }

  @Test
  public void testIntegerArr() {
    assertEquals(obj, put(obj, "$.data[2]", 93));
    assertEquals(93, (int) getInteger(obj, "$.data[2]"));
  }
}

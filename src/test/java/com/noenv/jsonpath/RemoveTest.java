package com.noenv.jsonpath;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import static com.noenv.jsonpath.JsonPath.*;
import static org.junit.Assert.*;

public class RemoveTest extends TestBase {

  @Test
  public void testString() {
    JsonObject copy = obj.copy();
    String removed = "123-456-7890";
    assertEquals(removed, remove(copy, "$.contact.phoneNumbers.home"));

    getJsonObject(obj, "$.contact.phoneNumbers").remove("home");
    assertEquals(obj, copy);
  }

  @Test
  public void testDotNotation() {
    JsonObject copy = obj.copy();
    assertEquals("string", remove(copy, "$.['nasty.dot.string']"));
    obj.remove("nasty.dot.string");

    assertEquals(1234, remove(copy, "$.['nasty.dot.int']"));
    obj.remove("nasty.dot.int");

    assertEquals(12.34, remove(copy, "$.['nasty.dot.float']"));
    obj.remove("nasty.dot.float");

    assertEquals(true, remove(copy, "$.['nasty.dot.boolean']"));
    obj.remove("nasty.dot.boolean");

    assertEquals(new JsonObject().put("nastier.dot.string", "thing"), remove(copy, "$.['nasty.dot.obj']"));
    obj.remove("nasty.dot.obj");

    assertEquals(new JsonArray().add("some").add("thing"), remove(copy, "$.['nasty.dot.arr']"));
    obj.remove("nasty.dot.arr");

    assertEquals(obj, copy);
  }

  @Test
  public void testArray() {
    JsonObject copy = obj.copy();
    JsonArray removed = new JsonArray()
      .add(new JsonArray().add("10:00-12:00").add("14:00-16:00"))
      .add(new JsonArray().add("11:00-12:00").add("15:00-16:00"));
    assertEquals(removed, remove(copy, "$.contact.officeHours"));

    obj.getJsonObject("contact").remove("officeHours");
    assertEquals(obj, copy);
  }

  @Test
  public void testNothingRemoved() {
    JsonObject copy = obj.copy();
    assertNull(remove(copy, "$.contact.firstName"));
    assertEquals(obj, copy);
  }
}

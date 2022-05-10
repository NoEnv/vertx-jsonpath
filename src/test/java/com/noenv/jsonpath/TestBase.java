package com.noenv.jsonpath;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Before;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class TestBase {

  protected Instant now = Instant.now();
  protected JsonObject obj;
  protected JsonArray arr;

  @Before
  public void setUp() {
    obj = new JsonObject()
      .put("name", "Max Mustermann")
      .put("nasty.dot.string", "string")
      .put("nasty.dot.int", 1234)
      .put("nasty.dot.float", 12.34)
      .put("nasty.dot.boolean", true)
      .put("nasty.dot.obj", new JsonObject().put("nastier.dot.string", "thing"))
      .put("nasty.dot.arr", new JsonArray().add("some").add( "thing"))
      .put("verified", true)
      .put("modified", new JsonObject()
        .put("$date", now))
      .put("contact", new JsonObject()
        .put("phoneNumbers", new JsonObject()
          .put("home", "123-456-7890")
          .put("mobile", "987-654-3210"))
        .put("officeHours", new JsonArray()
          .add(new JsonArray().add("10:00-12:00").add("14:00-16:00"))
          .add(new JsonArray().add("11:00-12:00").add("15:00-16:00")))
        .put("meta", new JsonObject().put("foo", "bar")))
      .put("stats", new JsonObject()
        .put("avgScore", 52)
        .put("avgDistance", 42.42))
      .put("secret", "AQID")
      .put("tags", new JsonArray().add("private").add("friends"))
      .put("data", new JsonArray().add(82).add(92).add(21));

    arr = new JsonArray()
      .add(new JsonObject()
        .put("firstName", "Max")
        .put("lastName", "Mustermann")
        .put("age", 31)
        .put("score", 12.2)
        .put("verified", true))
      .add(new JsonObject()
        .put("firstName", "John")
        .put("lastName", "Doe")
        .put("age", 17)
        .put("score", 13.1)
        .put("verified", false)
        .put("meta", new JsonObject().put("foo", "bar")))
      .add((JsonObject) null)
      .add(obj);
  }

  protected static void assertException(Class<? extends Throwable> expected, Action action) {
    try {
      action.execute();
      fail();
    } catch (Throwable t) {
      assertEquals(expected, t.getClass());
    }
  }
}

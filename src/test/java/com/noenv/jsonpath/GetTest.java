package com.noenv.jsonpath;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static com.noenv.jsonpath.JsonPath.*;
import static org.junit.Assert.*;

public class GetTest extends TestBase {

  @Test
  public void testMisc() {
    assertEquals("123-456-7890", getString(obj, "$.contact.phoneNumbers.home"));
    assertEquals(now, getInstant(obj, "$.modified.$date"));
    assertEquals("friends", getString(obj, "$.tags[1]"));
    assertEquals("15:00-16:00", getString(obj, "$.contact.officeHours[1][1]"));
    assertEquals("John", getString(arr, "$[1].firstName"));
  }

  @Test
  public void testNull() {
    assertNull(JsonPath.getString(new JsonObject(), "$.foo.bar.eek"));
    assertNull(JsonPath.getString(new JsonArray(), "$.foo.bar.eek"));
    assertNull(JsonPath.getString(new JsonObject(), "$[0]"));
  }

  @Test
  public void testInvalidSyntax() {
    Class<JsonPathException> e = JsonPathException.class;
    assertException(e, () -> getString((JsonObject) null, "$.name"));
    assertException(e, () -> getString(obj, null));
    assertException(e, () -> getString(obj, ""));
    assertException(e, () -> getString(obj, ".$"));
    assertException(e, () -> getString(obj, "$.tags[a]"));
    assertException(e, () -> getString(obj, "$.tags[1."));
    assertException(e, () -> getString(obj, "$name"));
    assertException(e, () -> getString(obj, "$.contact..phoneNumbers"));
  }

  @Test
  public void testInvalidType() {
    assertException(DateTimeParseException.class, () -> getInstant(obj, "$.contact.phoneNumbers.home"));
    assertException(ClassCastException.class, () -> getJsonObject(obj, "$.contact.officeHours"));
    assertException(ClassCastException.class, () -> getBoolean(obj, "$.modified"));
    assertException(ClassCastException.class, () -> getInteger(obj, "$.verified"));
    assertException(ClassCastException.class, () -> getInstant(obj, "$.tags"));
  }

  @Test
  public void testStringObj() {
    assertNull(getString(obj, "$.socialSecurityNumber"));
    assertEquals("123-456-7890", getString(obj, "$.contact.phoneNumbers.home"));
    assertEquals("helo", getString(obj, "$.greeting", "helo"));
    assertEquals("string", getString(obj, "$.['nasty.dot.string']"));
    assertEquals("thing", getString(obj, "$.['nasty.dot.obj'].['nastier.dot.string']"));
  }

  @Test
  public void testStringArr() {
    assertNull(getString(arr, "$[2]"));
    assertEquals("Mustermann", getString(arr, "$[0].lastName"));
    assertEquals("", getString(arr, "$[1].middleName", ""));
    assertEquals("foobar", getString(arr, "$[17].firstName", "foobar"));
    assertEquals(new JsonArray().add("some").add("thing"), getJsonArray(obj, "$.['nasty.dot.arr']"));
  }

  @Test
  public void testIntegerObj() {
    assertNull(getInteger(obj, "$.stats.SCORE"));
    assertEquals(52, (int) getInteger(obj, "$.stats.avgScore"));
    assertEquals(123, (int) getInteger(obj, "$.stats.none", 123));
    assertEquals(1234, (int) getInteger(obj, "$.['nasty.dot.int']"));
  }

  @Test
  public void testIntegerArr() {
    assertNull(getInteger(arr, "$[2]"));
    assertEquals(31, (int) getInteger(arr, "$[0].age"));
    assertEquals(42, (int) getInteger(arr, "$[0].AGE", 42));
  }

  @Test
  public void testLongObj() {
    assertNull(getLong(obj, "$.stats.SCORE"));
    assertEquals(52, (long) getLong(obj, "$.stats.avgScore"));
    assertEquals(123, (long) getLong(obj, "$.stats.none", 123L));
  }

  @Test
  public void testLongArr() {
    assertNull(getLong(arr, "$[2]"));
    assertEquals(31, (long) getLong(arr, "$[0].age"));
    assertEquals(42, (long) getLong(arr, "$[0].AGE", 42L));
  }

  @Test
  public void testDoubleObj() {
    assertNull(getDouble(obj, "$.stats.SCORE"));
    assertEquals(42.42, getDouble(obj, "$.stats.avgDistance"), 0.01);
    assertEquals(123.12, getDouble(obj, "$.stats.none", 123.12), 0.01);
    assertEquals(12.34, getDouble(obj, "$.['nasty.dot.float']"), 0.01);
  }

  @Test
  public void testDoubleArr() {
    assertNull(getDouble(arr, "$[2]"));
    assertEquals(13.1, getDouble(arr, "$[1].score"), 0.1);
    assertEquals(42.53, getDouble(arr, "$[0].Score", 42.53), 0.1);
  }

  @Test
  public void testFloatObj() {
    assertNull(getFloat(obj, "$.stats.SCORE"));
    assertEquals(42.42, getFloat(obj, "$.stats.avgDistance"), 0.01);
    assertEquals(123.12, getFloat(obj, "$.stats.none", 123.12f), 0.01);
    assertEquals(12.34, getFloat(obj, "$.['nasty.dot.float']"), 0.01);
  }

  @Test
  public void testFloatArr() {
    assertNull(getFloat(arr, "$[2]"));
    assertEquals(13.1, getFloat(arr, "$[1].score"), 0.1);
    assertEquals(42.53, getFloat(arr, "$[0].Score", 42.53f), 0.1);
  }

  @Test
  public void testBooleanObj() {
    assertNull(getBoolean(obj, "$.none"));
    assertEquals(true, getBoolean(obj, "$.verified"));
    assertEquals(false, getBoolean(obj, "$.vip", false));
    assertEquals(true, getBoolean(obj, "$.['nasty.dot.boolean']"));
  }

  @Test
  public void testBooleanArr() {
    assertNull(getBoolean(arr, "$[2]"));
    assertEquals(true, getBoolean(arr, "$[0].verified"));
    assertEquals(false, getBoolean(arr, "$[0].Verified", false));
  }

  @Test
  public void testJsonObjectObj() {
    assertNull(getJsonObject(obj, "$.none"));
    assertEquals(new JsonObject().put("foo", "bar"), getJsonObject(obj, "$.contact.meta"));
    assertEquals(new JsonObject(), getJsonObject(obj, "$.a.b.c.d", new JsonObject()));
    assertEquals(new JsonObject().put("nastier.dot.string", "thing"), getJsonObject(obj, "$.['nasty.dot.obj']"));
  }

  @Test
  public void testJsonObjectArr() {
    assertNull(getJsonObject(arr, "$[2]"));
    assertEquals(new JsonObject().put("foo", "bar"), getJsonObject(arr, "$[1].meta"));
    assertEquals(new JsonObject(), getJsonObject(arr, "$[0].meta", new JsonObject()));
  }

  @Test
  public void testJsonArrayObj() {
    assertNull(getJsonArray(obj, "$.none"));
    assertEquals(new JsonArray().add("private").add("friends"), getJsonArray(obj, "$.tags"));
    assertEquals(new JsonArray(), getJsonArray(obj, "$.a.b.c.d", new JsonArray()));
  }

  @Test
  public void testJsonArrayArr() {
    assertNull(getJsonArray(arr, "$[2]"));
    assertEquals(new JsonArray().add("private").add("friends"), getJsonArray(arr, "$[3].tags"));
    assertEquals(new JsonArray(), getJsonArray(arr, "$[0].tags", new JsonArray()));
  }

  @Test
  public void testBinaryObj() {
    assertNull(getBinary(obj, "$.none"));
    assertArrayEquals(new byte[]{1, 2, 3}, getBinary(obj, "$.secret"));
    assertArrayEquals(new byte[]{}, getBinary(obj, "$.vip", new byte[]{}));
  }

  @Test
  public void testBinaryArr() {
    assertNull(getBinary(arr, "$[2]"));
    assertArrayEquals(new byte[]{1, 2, 3}, getBinary(arr, "$[3].secret"));
    assertArrayEquals(new byte[]{}, getBinary(arr, "$[1].secret", new byte[]{}));
  }

  @Test
  public void testInstantObj() {
    assertNull(getInstant(obj, "$.none"));
    assertEquals(now, getInstant(obj, "$.modified.$date"));
    assertEquals(now.minusSeconds(43), getInstant(obj, "$.created.$date", now.minusSeconds(43)));
  }

  @Test
  public void testInstantArr() {
    assertNull(getInstant(arr, "$[2]"));
    assertEquals(now, getInstant(arr, "$[3].modified.$date"));
    assertEquals(now.minusSeconds(17), getInstant(arr, "$[1].created", now.minusSeconds(17)));
  }

  @Test
  public void testValueObj() {
    assertNull(getValue(obj, "$.none"));
    assertEquals("AQID", getValue(obj, "$.secret"));
    assertEquals(72, getValue(obj, "$.none.invalid", 72));
  }

  @Test
  public void testValueArr() {
    assertNull(getValue(arr, "$[2]"));
    assertEquals("bar", getValue(arr, "$[3].contact.meta.foo"));
    assertEquals("foo", getValue(arr, "$[1].contact.meta.foo", "foo"));
  }

  @Test
  public void testUnsupportedDotSyntax_WrongLeadingSyntax() {
    // missing ' in the beginning
    assertThrows("unsupported syntax [nasty.dot.string']", JsonPathException.class, () -> getString(obj, "$.[nasty.dot.string']"));
  }

  @Test
  public void testUnsupportedDotSyntax_WrongTrailingSyntax() {
    // missing ' in the end
    assertThrows("unsupported syntax ['nasty.dot.string]", JsonPathException.class, () -> getString(obj, "$.['nasty.dot.string]"));
  }
}

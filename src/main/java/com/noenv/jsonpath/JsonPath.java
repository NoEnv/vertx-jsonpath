package com.noenv.jsonpath;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class JsonPath {

  private JsonPath() {
  }

  public static String getString(JsonObject o, String expr) {
    return getString(o, expr, null);
  }

  public static String getString(JsonObject o, String expr, String defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getString, JsonArray::getString);
  }

  public static String getString(JsonArray o, String expr) {
    return getString(o, expr, null);
  }

  public static String getString(JsonArray o, String expr, String defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getString, JsonArray::getString);
  }

  public static Integer getInteger(JsonObject o, String expr) {
    return getInteger(o, expr, null);
  }

  public static Integer getInteger(JsonObject o, String expr, Integer defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getInteger, JsonArray::getInteger);
  }

  public static Integer getInteger(JsonArray o, String expr) {
    return getInteger(o, expr, null);
  }

  public static Integer getInteger(JsonArray o, String expr, Integer defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getInteger, JsonArray::getInteger);
  }

  public static Long getLong(JsonObject o, String expr) {
    return getLong(o, expr, null);
  }

  public static Long getLong(JsonObject o, String expr, Long defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getLong, JsonArray::getLong);
  }

  public static Long getLong(JsonArray o, String expr) {
    return getLong(o, expr, null);
  }

  public static Long getLong(JsonArray o, String expr, Long defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getLong, JsonArray::getLong);
  }

  public static Double getDouble(JsonObject o, String expr) {
    return getDouble(o, expr, null);
  }

  public static Double getDouble(JsonObject o, String expr, Double defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getDouble, JsonArray::getDouble);
  }

  public static Double getDouble(JsonArray o, String expr) {
    return getDouble(o, expr, null);
  }

  public static Double getDouble(JsonArray o, String expr, Double defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getDouble, JsonArray::getDouble);
  }

  public static Float getFloat(JsonObject o, String expr) {
    return getFloat(o, expr, null);
  }

  public static Float getFloat(JsonObject o, String expr, Float defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getFloat, JsonArray::getFloat);
  }

  public static Float getFloat(JsonArray o, String expr) {
    return getFloat(o, expr, null);
  }

  public static Float getFloat(JsonArray o, String expr, Float defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getFloat, JsonArray::getFloat);
  }

  public static Boolean getBoolean(JsonObject o, String expr) {
    return getBoolean(o, expr, null);
  }

  public static Boolean getBoolean(JsonObject o, String expr, Boolean defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getBoolean, JsonArray::getBoolean);
  }

  public static Boolean getBoolean(JsonArray o, String expr) {
    return getBoolean(o, expr, null);
  }

  public static Boolean getBoolean(JsonArray o, String expr, Boolean defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getBoolean, JsonArray::getBoolean);
  }

  public static JsonObject getJsonObject(JsonObject o, String expr) {
    return getJsonObject(o, expr, null);
  }

  public static JsonObject getJsonObject(JsonObject o, String expr, JsonObject defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getJsonObject, JsonArray::getJsonObject);
  }

  public static JsonObject getJsonObject(JsonArray o, String expr) {
    return getJsonObject(o, expr, null);
  }

  public static JsonObject getJsonObject(JsonArray o, String expr, JsonObject defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getJsonObject, JsonArray::getJsonObject);
  }

  public static JsonArray getJsonArray(JsonObject o, String expr) {
    return getJsonArray(o, expr, null);
  }

  public static JsonArray getJsonArray(JsonObject o, String expr, JsonArray defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getJsonArray, JsonArray::getJsonArray);
  }

  public static JsonArray getJsonArray(JsonArray o, String expr) {
    return getJsonArray(o, expr, null);
  }

  public static JsonArray getJsonArray(JsonArray o, String expr, JsonArray defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getJsonArray, JsonArray::getJsonArray);
  }

  public static byte[] getBinary(JsonObject o, String expr) {
    return getBinary(o, expr, null);
  }

  public static byte[] getBinary(JsonObject o, String expr, byte[] defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getBinary, JsonArray::getBinary);
  }

  public static byte[] getBinary(JsonArray o, String expr) {
    return getBinary(o, expr, null);
  }

  public static byte[] getBinary(JsonArray o, String expr, byte[] defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getBinary, JsonArray::getBinary);
  }

  public static Instant getInstant(JsonObject o, String expr) {
    return getInstant(o, expr, null);
  }

  public static Instant getInstant(JsonObject o, String expr, Instant defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getInstant, JsonArray::getInstant);
  }

  public static Instant getInstant(JsonArray o, String expr) {
    return getInstant(o, expr, null);
  }

  public static Instant getInstant(JsonArray o, String expr, Instant defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getInstant, JsonArray::getInstant);
  }

  public static Object getValue(JsonObject o, String expr) {
    return getValue(o, expr, null);
  }

  public static Object getValue(JsonObject o, String expr, Object defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getValue, JsonArray::getValue);
  }

  public static Object getValue(JsonArray o, String expr) {
    return getValue(o, expr, null);
  }

  public static Object getValue(JsonArray o, String expr, Object defaultValue) {
    return get(o, expr, defaultValue, JsonObject::getValue, JsonArray::getValue);
  }

  public static <T> JsonObject put(JsonObject o, String expr, T value) {
    return (JsonObject) putInternal(o, expr, value);
  }

  public static <T> JsonArray put(JsonArray o, String expr, T value) {
    return (JsonArray) putInternal(o, expr, value);
  }

  public static boolean containsKey(JsonObject o, String expr) {
    return containsKeyInternal(o, expr);
  }

  public static boolean containsKey(JsonArray o, String expr) {
    return containsKeyInternal(o, expr);
  }

  public static Object remove(JsonObject o, String expr) {
    return removeInternal(o, expr);
  }

  private static boolean containsKeyInternal(Object o, String expr) {
    return beginEval(o, expr, false, JsonObject::containsKey, (a, i) -> i >= 0 && i < a.size());
  }

  private static <T> T get(Object o, String expr, T defaultValue,
                           BiFunction<JsonObject, String, T> objGet,
                           BiFunction<JsonArray, Integer, T> arrGet) {
    return beginEval(o, expr, defaultValue, objGet, arrGet);
  }

  private static <T> Object putInternal(Object o, String expr, T value) {
    return beginEval(o, expr, null, (c, k) -> {
      c.put(k, value);
      return o;
    }, (a, i) -> {
      a.set(i, value);
      return o;
    });
  }

  private static <T> T beginEval(Object o, String expr, T defaultValue,
                                 BiFunction<JsonObject, String, T> objGet,
                                 BiFunction<JsonArray, Integer, T> arrGet) {
    if (o == null || expr == null || !expr.startsWith("$")) {
      throw new JsonPathException("json is required and expr has to start with '$'");
    }
    return eval(o, expr.substring(1), defaultValue, objGet, arrGet);
  }

  private static <T> T eval(Object o, String expr, T defaultValue,
                            BiFunction<JsonObject, String, T> objGet,
                            BiFunction<JsonArray, Integer, T> arrGet) {
    switch (expr.charAt(0)) {
      case '.':
        return o instanceof JsonObject ? child((JsonObject) o, expr.substring(1), defaultValue, objGet, arrGet) : defaultValue;
      case '[':
        return o instanceof JsonArray ? array((JsonArray) o, expr.substring(1), defaultValue, objGet, arrGet) : defaultValue;
      default:
        throw new JsonPathException("unknown operator " + expr.charAt(0));
    }
  }

  private static <T> T child(JsonObject o, String expr, T defaultValue,
                             BiFunction<JsonObject, String, T> objGet,
                             BiFunction<JsonArray, Integer, T> arrGet) {
    List<Character> endChars = new ArrayList<>();
    endChars.add('.');
    endChars.add( '[');
    int endIdx = 0;
    int beginIdx = 0;

    // dotted key
    if (expr.charAt(beginIdx) == '[') {
      if (expr.charAt(beginIdx + 1) != '\'') {
        throw new JsonPathException("unsupported syntax " + expr.substring(0, beginIdx));
      }
      beginIdx = 2;
      endIdx += 2;
    }

    boolean isDottedKey = beginIdx != 0;

    while (endIdx < expr.length() &&
      ((!isDottedKey && !endChars.contains(expr.charAt(endIdx))) || (isDottedKey && !expr.substring(beginIdx, endIdx).endsWith("']")))) {
      endIdx++;
    }

    if (endIdx == 0) {
      throw new JsonPathException("missing name of child after '.'");
    }

    String name = expr.substring(beginIdx, endIdx);

    if (isDottedKey) {
      if (!expr.substring(beginIdx, endIdx).endsWith("']")) {
        throw new JsonPathException("unsupported syntax " + expr.substring(beginIdx));
      }
      name = expr.substring(beginIdx, endIdx - 2);
    }
    if (endIdx == expr.length()) {
      T r = objGet.apply(o, name);
      return r == null ? defaultValue : r;
    }
    if (o.containsKey(name)) {
      return eval(o.getValue(name), expr.substring(endIdx), defaultValue, objGet, arrGet);
    }
    return defaultValue;
  }

  private static <T> T array(JsonArray o, String expr, T defaultValue,
                             BiFunction<JsonObject, String, T> objGet,
                             BiFunction<JsonArray, Integer, T> arrGet) {
    int endIdx = expr.indexOf(']');
    if (endIdx == -1) {
      throw new JsonPathException("missing ']' in expression");
    }
    int idx;
    try {
      idx = Integer.parseInt(expr.substring(0, endIdx));
    } catch (NumberFormatException ex) {
      throw new JsonPathException("invalid array index " + expr.substring(0, endIdx));
    }
    if (endIdx + 1 == expr.length()) {
      T r = arrGet.apply(o, idx);
      return r == null ? defaultValue : r;
    }
    if (idx >= 0 && idx < o.size()) {
      return eval(o.getValue(idx), expr.substring(endIdx + 1), defaultValue, objGet, arrGet);
    }
    return defaultValue;
  }

  private static Object removeInternal(JsonObject o, String expr) {
    return beginEval(o, expr, null, JsonObject::remove, (a, i) -> {
      throw new IllegalArgumentException("");
    });
  }
}

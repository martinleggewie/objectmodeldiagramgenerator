package org.codemaker.objectmodeldiagramgenerator.util;

import org.junit.jupiter.api.Test;

import static org.codemaker.objectmodeldiagramgenerator.util.PumlUtil.lineWrap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PumlUtilTest {

  @Test
  void lineWrap_10() {
    // Arrange
    String text1Raw = "";
    String text1Expected = "";
    String text2Raw = "123456789 1";
    String text2Expected = "123456789\\n1";
    String text3Raw = "1234567890";
    String text3Expected = "1234567890";
    String text4Raw = "1234567890 1";
    String text4Expected = "1234567890\\n1";
    String text5Raw = "12345678901";
    String text5Expected = "12345678901";
    String text6Raw = "12345678901 2";
    String text6Expected = "12345678901\\n2";
    String text7Raw = "12345678901234567890";
    String text7Expected = "12345678901234567890";
    String text8Raw = "12345678901234567890 1";
    String text8Expected = "12345678901234567890\\n1";
    String text9Raw = "12345678901234567890123456789 1";
    String text9Expected = "12345678901234567890123456789\\n1";

    // Act
    String result1 = lineWrap(text1Raw, 10);
    String result2 = lineWrap(text2Raw, 10);
    String result3 = lineWrap(text3Raw, 10);
    String result4 = lineWrap(text4Raw, 10);
    String result5 = lineWrap(text5Raw, 10);
    String result6 = lineWrap(text6Raw, 10);
    String result7 = lineWrap(text7Raw, 10);
    String result8 = lineWrap(text8Raw, 10);
    String result9 = lineWrap(text9Raw, 10);

    // Assert
    assertEquals(text1Expected, result1);
    assertEquals(text2Expected, result2);
    assertEquals(text3Expected, result3);
    assertEquals(text4Expected, result4);
    assertEquals(text5Expected, result5);
    assertEquals(text6Expected, result6);
    assertEquals(text7Expected, result7);
    assertEquals(text8Expected, result8);
    assertEquals(text9Expected, result9);
  }
}
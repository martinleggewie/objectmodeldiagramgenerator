package org.codemaker.objectmodeldiagramgenerator.util;

public class PumlUtil {

  public static String lineWrap(String rawLine, int maxLineLength) {
    StringBuilder result = new StringBuilder();

    String[] words = rawLine.trim().split(" ");
    int lineLength = 0;
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      if (lineLength + word.length() >= maxLineLength) {
        if (i > 0) {
          result.append("\\n");
        }
        lineLength = 0;
      } else {
        if (i > 0) {
          result.append(" ");
          lineLength++;
        }
      }
      result.append(word);
      lineLength += word.length();
    }

    return result.toString();
  }
}

package org.codemaker.objectmodeldiagramgenerator.domain.valueobjects;

public class PumlDiagram {
  private final String name;
  private final String content;

  public PumlDiagram(String name, String content) {
    this.name = name;
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public String getContent() {
    return content;
  }
}

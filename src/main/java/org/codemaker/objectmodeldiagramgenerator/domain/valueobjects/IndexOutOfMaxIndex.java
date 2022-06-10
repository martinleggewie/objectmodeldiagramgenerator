package org.codemaker.objectmodeldiagramgenerator.domain.valueobjects;

import java.util.Objects;

/**
 * Represents the index of an element out of a total amount of elements. Example: You can indicate something like "This is diagram 7
 * out of a total amount of 13 diagrams.
 */
public class IndexOutOfMaxIndex {
  private final int index;
  private final int maxIndex;

  public IndexOutOfMaxIndex(int index, int maxIndex) {
    this.index = index;
    this.maxIndex = maxIndex;
  }

  public int getIndex() {
    return index;
  }

  public int getMaxIndex() {
    return maxIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof IndexOutOfMaxIndex))
      return false;
    IndexOutOfMaxIndex that = (IndexOutOfMaxIndex) o;
    return getIndex() == that.getIndex() && getMaxIndex() == that.getMaxIndex();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIndex(), getMaxIndex());
  }

  @Override
  public String toString() {
    return "IndexOutOfMaxIndex{" + "index=" + index + ", maxIndex=" + maxIndex + '}';
  }
}

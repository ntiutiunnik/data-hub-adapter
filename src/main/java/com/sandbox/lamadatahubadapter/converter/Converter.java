package com.sandbox.lamadatahubadapter.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface Converter<F, T> {

  T convert(F dto);

  default List<T> convert(List<F> dtos) {
    return dtos.stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }
}

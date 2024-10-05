package org.arta.libraryintec.mapper;

public interface Mapper<F, T> {
    T map(F object);
}

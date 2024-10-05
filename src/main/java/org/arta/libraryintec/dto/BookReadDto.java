package org.arta.libraryintec.dto;

public record BookReadDto(Integer id,
                          String title,
                          String author,
                          Boolean available) {
}

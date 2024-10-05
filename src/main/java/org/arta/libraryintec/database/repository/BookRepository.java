package org.arta.libraryintec.database.repository;

import org.arta.libraryintec.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    List<Book> findByTitleLike(String title);
    List<Book> findByAuthorLike(String title);
}

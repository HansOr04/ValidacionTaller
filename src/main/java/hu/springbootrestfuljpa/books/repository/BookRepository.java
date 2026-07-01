package hu.springbootrestfuljpa.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.springbootrestfuljpa.books.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
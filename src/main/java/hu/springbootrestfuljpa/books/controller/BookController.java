package hu.springbootrestfuljpa.books.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.springbootrestfuljpa.books.model.Book;
import hu.springbootrestfuljpa.books.repository.BookRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping(path = "/books")
	public List<Book> retrieveAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping(path = "/books/{id}")
	public ResponseEntity<Book> retrieveBook(@PathVariable int id) {
		Optional<Book> book = bookRepository.findById(id);

		if (book.isPresent()) {
			return ResponseEntity.ok(book.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/books")
	public ResponseEntity<Object> createBook(@RequestBody Book book) {
		if (book.getId() != null && bookRepository.existsById(book.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		return ResponseEntity.ok(bookRepository.save(book));
	}

	@DeleteMapping(path = "/books/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable int id) {
		Optional<Book> book = bookRepository.findById(id);

		if (book.isPresent()) {
			bookRepository.delete(book.get());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
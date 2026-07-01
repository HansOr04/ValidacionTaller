package hu.springbootrestfuljpa.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.springbootrestfuljpa.books.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
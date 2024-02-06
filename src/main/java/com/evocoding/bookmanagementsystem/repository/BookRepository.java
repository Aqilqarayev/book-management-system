package com.evocoding.bookmanagementsystem.repository;

import com.evocoding.bookmanagementsystem.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}

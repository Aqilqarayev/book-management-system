package com.evocoding.bookmanagementsystem.repository;

import com.evocoding.bookmanagementsystem.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByIsDeleted(boolean isDeleted);

    Optional<BookEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
}

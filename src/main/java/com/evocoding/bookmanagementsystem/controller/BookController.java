package com.evocoding.bookmanagementsystem.controller;

import com.evocoding.bookmanagementsystem.service.BookService;
import com.evocoding.bookmanagementsystem.service.dto.BookDTO;
import com.evocoding.bookmanagementsystem.service.dto.CreateBookDTO;
import com.evocoding.bookmanagementsystem.service.dto.UpdateBookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody CreateBookDTO createBookDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(createBookDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateBookDTO updateBookDTO){
        bookService.update(id,updateBookDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

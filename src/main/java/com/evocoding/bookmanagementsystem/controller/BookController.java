package com.evocoding.bookmanagementsystem.controller;

import com.evocoding.bookmanagementsystem.service.BookService;
import com.evocoding.bookmanagementsystem.service.dto.BookDTO;
import com.evocoding.bookmanagementsystem.service.dto.CreateBookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody CreateBookDTO createBookDTO){
        bookService.create(createBookDTO);
    }

}

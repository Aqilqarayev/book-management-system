package com.evocoding.bookmanagementsystem.service;

import com.evocoding.bookmanagementsystem.exception.BookNotFoundException;
import com.evocoding.bookmanagementsystem.repository.BookRepository;
import com.evocoding.bookmanagementsystem.repository.entity.BookEntity;
import com.evocoding.bookmanagementsystem.service.dto.BookDTO;
import com.evocoding.bookmanagementsystem.service.dto.CreateBookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDTO> findAll() {
        var books = bookRepository.findAll();
        var bookDTOs = new ArrayList<BookDTO>();

        for (BookEntity book : books) {
            bookDTOs.add(mapToBookDTO(book));
        }

        return bookDTOs;
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id);
        return mapToBookDTO(book.orElseThrow(() -> new BookNotFoundException("Book not found with id:" + id)));
    }

    public void create(CreateBookDTO createBookDTO){
       var book = mapToBookEntity(createBookDTO);
       bookRepository.save(book);
    }

    private static BookEntity mapToBookEntity(CreateBookDTO createBookDTO) {
        var book = new BookEntity();
        book.setTitle(createBookDTO.getTitle());
        book.setAuthor(createBookDTO.getAuthor());
        book.setIsbn(createBookDTO.getIsbn());
        book.setGenre(createBookDTO.getGenre());
        book.setPublisher(createBookDTO.getPublisher());
        return book;
    }

    private static BookDTO mapToBookDTO(BookEntity book) {
        var bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setPublisher(book.getPublisher());

        return bookDTO;
    }



}

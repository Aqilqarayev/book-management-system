package com.evocoding.bookmanagementsystem.service;

import com.evocoding.bookmanagementsystem.exception.BookNotFoundException;
import com.evocoding.bookmanagementsystem.repository.BookRepository;
import com.evocoding.bookmanagementsystem.repository.entity.BookEntity;
import com.evocoding.bookmanagementsystem.service.dto.BookDTO;
import com.evocoding.bookmanagementsystem.service.dto.CreateBookDTO;
import com.evocoding.bookmanagementsystem.service.dto.UpdateBookDTO;
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
        var books = bookRepository.findAllByIsDeleted(false);
        var bookDTOs = new ArrayList<BookDTO>();

        for (BookEntity book : books) {
            bookDTOs.add(mapToBookDTO(book));
        }

        return bookDTOs;
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findByIdAndIsDeleted(id, false);
        return mapToBookDTO(book.orElseThrow(() -> new BookNotFoundException("Book not found with id:" + id)));
    }

    public void create(CreateBookDTO createBookDTO) {
        var book = mapToBookEntity(createBookDTO);
        bookRepository.save(book);
    }

    public void update(Long id, UpdateBookDTO updateBookDTO) {
        BookEntity book = getBookEntity(id);
        book.setAuthor(updateBookDTO.getAuthor());
        book.setIsbn(updateBookDTO.getIsbn());
        book.setTitle(updateBookDTO.getTitle());
        book.setGenre(updateBookDTO.getGenre());
        book.setPublisher(updateBookDTO.getPublisher());

        bookRepository.save(book);
    }

    public void delete(Long id) {
        BookEntity book = getBookEntity(id);
        book.setDeleted(true);

        bookRepository.save(book);
    }

    private BookEntity getBookEntity(Long id) {
        return bookRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id:" + id));
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

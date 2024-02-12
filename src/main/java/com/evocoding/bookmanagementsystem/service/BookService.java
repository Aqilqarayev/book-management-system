package com.evocoding.bookmanagementsystem.service;

import com.evocoding.bookmanagementsystem.exception.BookNotFoundException;
import com.evocoding.bookmanagementsystem.mapper.BookMapper;
import com.evocoding.bookmanagementsystem.repository.BookRepository;
import com.evocoding.bookmanagementsystem.repository.entity.BookEntity;
import com.evocoding.bookmanagementsystem.service.dto.BookDTO;
import com.evocoding.bookmanagementsystem.service.dto.CreateBookDTO;
import com.evocoding.bookmanagementsystem.service.dto.UpdateBookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> findAll() {
        var books = bookRepository.findAllByIsDeleted(false);
        return bookMapper.toBookDTOList(books);
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id:" + id));
        return bookMapper.toBookDTO(book);
    }

    public BookDTO create(CreateBookDTO createBookDTO) {
        BookEntity book = bookMapper.toBookEntity(createBookDTO);
        return bookMapper.toBookDTO(bookRepository.save(book));
    }

    public void update(Long id, UpdateBookDTO updateBookDTO) {
        BookEntity book = getBookEntity(id);
        bookMapper.toBookEntity(updateBookDTO, book);
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

}

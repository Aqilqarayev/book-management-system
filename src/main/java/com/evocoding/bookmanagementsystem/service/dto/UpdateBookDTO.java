package com.evocoding.bookmanagementsystem.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookDTO {
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private String publisher;
}

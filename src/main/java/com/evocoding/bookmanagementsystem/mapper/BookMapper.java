package com.evocoding.bookmanagementsystem.mapper;

import com.evocoding.bookmanagementsystem.repository.entity.BookEntity;
import com.evocoding.bookmanagementsystem.service.dto.BookDTO;
import com.evocoding.bookmanagementsystem.service.dto.CreateBookDTO;
import com.evocoding.bookmanagementsystem.service.dto.UpdateBookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toBookDTO(BookEntity bookEntity);
    BookEntity toBookEntity(CreateBookDTO createBookDTO);
    void toBookEntity(UpdateBookDTO updateBookDTO, @MappingTarget BookEntity book);

    List<BookDTO> toBookDTOList(List<BookEntity> bookEntities);

}

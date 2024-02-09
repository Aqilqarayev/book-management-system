package com.evocoding.bookmanagementsystem.mapper;

import com.evocoding.bookmanagementsystem.repository.entity.BookEntity;
import com.evocoding.bookmanagementsystem.service.dto.BookDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toBookDTO(BookEntity bookEntity);

    List<BookDTO> toBookDTOList(List<BookEntity> bookEntities);

}

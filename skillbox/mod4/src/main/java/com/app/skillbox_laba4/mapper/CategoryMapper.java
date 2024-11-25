package com.app.skillbox_laba4.mapper;

import com.app.skillbox_laba4.domain.Category;
import com.app.skillbox_laba4.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

}

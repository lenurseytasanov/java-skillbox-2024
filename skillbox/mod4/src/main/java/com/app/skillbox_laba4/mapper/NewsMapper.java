package com.app.skillbox_laba4.mapper;

import com.app.skillbox_laba4.domain.News;
import com.app.skillbox_laba4.dto.NewsDetailedDto;
import com.app.skillbox_laba4.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "categories", expression = "java(newsDto.getCategories())")
    News toEntity(NewsDto newsDto);

    @Mapping(target = "commentsNumber", expression = "java((long) news.getComments().size())")
    NewsDto toDto(News news);

    @Mapping(target = "commentsNumber", expression = "java((long) news.getComments().size())")
    NewsDetailedDto toDetailedDto(News news);

}

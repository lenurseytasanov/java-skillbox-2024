package com.app.skillbox_laba4.mapper;

import com.app.skillbox_laba4.domain.Comment;
import com.app.skillbox_laba4.dto.CommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentDto commentDto);

    CommentDto toDto(Comment comment);

}

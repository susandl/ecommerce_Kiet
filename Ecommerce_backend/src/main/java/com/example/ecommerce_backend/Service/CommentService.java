package com.example.ecommerce_backend.Service;

import com.example.ecommerce_backend.Data.Entity.Comment;
import com.example.ecommerce_backend.Data.Entity.Rating;
import com.example.ecommerce_backend.Dto.Request.CommentRequestDto;
import com.example.ecommerce_backend.Dto.Request.RatingRequestDto;
import com.example.ecommerce_backend.Dto.Response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getCommentsByProductId(Long id);

    List<CommentResponseDto> getCommentsByProductName(String name);

    void createComment(CommentRequestDto commentRequestDto,Long id);
}

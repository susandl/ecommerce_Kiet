package com.example.ecommerce_backend.Controller.Customer;

import com.example.ecommerce_backend.Dto.Request.CommentRequestDto;
import com.example.ecommerce_backend.Dto.Response.CommentResponseDto;
import com.example.ecommerce_backend.Service.CategoryService;
import com.example.ecommerce_backend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public List<CommentResponseDto> getCommentsByProductId(@PathVariable Long id) {
        return this.commentService.getCommentsByProductId(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@Valid @RequestBody CommentRequestDto commentRequestDto) {
        this.commentService.createComment(commentRequestDto);
    }
}

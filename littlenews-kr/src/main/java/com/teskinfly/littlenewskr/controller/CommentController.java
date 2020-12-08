package com.teskinfly.littlenewskr.controller;

import com.teskinfly.littlenewskr.domain.Comment;
import com.teskinfly.littlenewskr.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService service;
    @GetMapping("/{tid}/{page}/{size}")
    public List<Comment> get(@PathVariable String tid, @PathVariable int page, @PathVariable int size) throws IOException {
        return service.get(tid,page,size);
    }
    @PostMapping("/")
    public boolean add(@RequestBody Comment comment) throws IOException {
        return service.add(comment);
    }
}

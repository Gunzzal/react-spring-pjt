package com.spring.react_spring_pjt.bbs.domain;

import java.util.ArrayList;

import com.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;

import lombok.Data;

@Data
public class BbsResponseDTO {
    private Integer id;
    private String title;
    private String content;

    private ArrayList<CommentResponseDTO> comments;
}

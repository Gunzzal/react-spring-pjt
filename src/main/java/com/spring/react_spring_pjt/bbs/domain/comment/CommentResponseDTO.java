package com.spring.react_spring_pjt.bbs.domain.comment;

import lombok.Data;

@Data
public class CommentResponseDTO {
    private Integer id;
    private String comment;
    private Integer bbsid;
}

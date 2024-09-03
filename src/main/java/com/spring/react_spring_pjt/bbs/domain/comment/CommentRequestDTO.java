package com.spring.react_spring_pjt.bbs.domain.comment;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private Integer id;
    private String comment;
    private Integer bbsid;
}

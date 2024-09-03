package com.spring.react_spring_pjt.bbs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.spring.react_spring_pjt.bbs.domain.BbsRequestDTO;
import com.spring.react_spring_pjt.bbs.domain.BbsResponseDTO;
import com.spring.react_spring_pjt.bbs.domain.comment.CommentRequestDTO;
import com.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;

@Mapper
public interface BbsMapper {

    public List<BbsResponseDTO> findAllRow();

    public void saveRow(BbsRequestDTO parmas);

    public Optional<BbsResponseDTO> findRow(Map<String, Integer> map);

    public ArrayList<CommentResponseDTO> commentFindRow(Map<String, Integer> map);

    public void commentSaveRow(CommentRequestDTO parmas);

    public void deleteRow(Map<String, Integer> map);
    
    public void commentDeleteRow(Map<String, Integer> map);

    public void updateRow(BbsRequestDTO params);
}

package com.spring.react_spring_pjt.bbs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.react_spring_pjt.bbs.dao.BbsMapper;
import com.spring.react_spring_pjt.bbs.domain.BbsRequestDTO;
import com.spring.react_spring_pjt.bbs.domain.BbsResponseDTO;
import com.spring.react_spring_pjt.bbs.domain.comment.CommentRequestDTO;
import com.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;

@Service
public class BbsService {
    
    
    @Autowired
    private BbsMapper bbsMapper;

    public List<BbsResponseDTO> findAll(){
        System.out.println("debug >>> service findAll "+bbsMapper);
        return bbsMapper.findAllRow();
    }

    public void save(BbsRequestDTO params){
        System.out.println("debug >>> service save "+bbsMapper);
        bbsMapper.saveRow(params);
    }

    public Optional<BbsResponseDTO> find(Map<String, Integer> map){
        System.out.println("debug >>> service view "+bbsMapper);
        Optional<BbsResponseDTO> result = bbsMapper.findRow(map);
        ArrayList<CommentResponseDTO> lst = bbsMapper.commentFindRow(map);
        if(result.isPresent()){
            result.get().setComments(lst);
        }
        return result;
    }

    public void commentSave(CommentRequestDTO params){
        System.out.println("debug >>> service commentSave "+bbsMapper);
        bbsMapper.commentSaveRow(params);
    }

    public ArrayList<CommentResponseDTO> getComment(Map<String, Integer> map){
        return bbsMapper.commentFindRow(map);
    }

    public void delete(Map<String, Integer> map){
        bbsMapper.deleteRow(map);
        // bbsMapper.commentAllDeleteRow(map);
    }

    public void commentDelete(Map<String, Integer> map){
        bbsMapper.commentDeleteRow(map);
    }

    public void update(BbsRequestDTO params){
        bbsMapper.updateRow(params);
    }
}

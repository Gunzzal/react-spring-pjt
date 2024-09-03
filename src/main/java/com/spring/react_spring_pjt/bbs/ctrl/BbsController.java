package com.spring.react_spring_pjt.bbs.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.react_spring_pjt.bbs.domain.BbsRequestDTO;
import com.spring.react_spring_pjt.bbs.domain.BbsResponseDTO;
import com.spring.react_spring_pjt.bbs.domain.comment.CommentRequestDTO;
import com.spring.react_spring_pjt.bbs.domain.comment.CommentResponseDTO;
import com.spring.react_spring_pjt.bbs.service.BbsService;






@RestController
@RequestMapping("/bbs")
public class BbsController {

    @Autowired
    private BbsService bbsService;

    @GetMapping("/index")
    public ResponseEntity<Object> landing() {
        System.out.println("client endpoint /bbs/index"+bbsService);
        List<BbsResponseDTO> list = bbsService.findAll();
        System.out.println("result size = "+list.size());
        if(list.size()==0){
            Map<String, String> map = new HashMap<>();
            map.put("info", "저장된 데이터가 존재하지 않습니다.");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody BbsRequestDTO params) {
        System.out.println("client endpoint /bbs/save"+bbsService);
        System.out.println("params = "+params);
        bbsService.save(params);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Object> view(@PathVariable Integer id) {
        System.out.println("client endpoint /bbs/view"+bbsService);
        Map<String, Integer>map = new HashMap<>();
        map.put("id", id);
        Optional<BbsResponseDTO> response = bbsService.find(map);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/comment/save")
    public ResponseEntity<Void> commentSave(@RequestBody CommentRequestDTO params) {
        System.out.println("client endpoint /comment/save"+bbsService);
        System.out.println("params = "+params);
        bbsService.commentSave(params);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/view/getComment/{id}")
    public ResponseEntity<Object> getComment(@PathVariable Integer id) {
        System.out.println("client endpoint /view/getComment/{id}"+bbsService);
        System.out.println("params = "+id);
        Map<String, Integer>map = new HashMap<>();
        map.put("id", id);
        ArrayList<CommentResponseDTO> lst = bbsService.getComment(map);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBbs(@PathVariable Integer id) {
        System.out.println("client endpoint /delete/{id}"+bbsService);
        System.out.println("params = "+id);
        Map<String, Integer>map = new HashMap<>();
        map.put("id", id);
        bbsService.delete(map);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        System.out.println("client endpoint /comment/delete/{id}"+bbsService);
        System.out.println("params = "+id);
        Map<String, Integer>map = new HashMap<>();
        map.put("id", id);
        bbsService.commentDelete(map);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // @PatchMapping("/update/{id}")
    // public ResponseEntity<Void> putMethodName(@PathVariable Integer id, @RequestBody BbsRequestDTO params ) {
    //     System.out.println("client endpoint /update/{id}"+bbsService);
    //     System.out.println("params = "+params);
    //     System.out.println("id = "+id);
    //     params.setId(id);
    //     bbsService.update(params);
        
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;

    @PatchMapping("/update")
    public ResponseEntity<Void> putMethodName( @RequestBody BbsRequestDTO params ) {
        System.out.println("client endpoint /update/{id}"+bbsService);
        System.out.println("params = "+params);

        bbsService.update(params);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }
}

package com.spring.react_spring_pjt.openapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.react_spring_pjt.openapi.domian.ForecastItemDTO;
import com.spring.react_spring_pjt.openapi.domian.ForecastItems;
import com.spring.react_spring_pjt.openapi.util.CategoryCode;

@Service
public class ForecastService {
    /*
     ObjectMapper 객체를 이용해서 json 문자열을 Java 객체로 변경 = 역 직렬화(json -> java)
     직렬화(java -> json)
     */
    public List<ForecastItemDTO> parsiongJson(String str){
        ObjectMapper mapper = new ObjectMapper();
        List<ForecastItemDTO> list = null;
        try{
            ForecastItems items =  mapper.readValue(str, ForecastItems.class);
            list = items.getItems();
            
            System.out.println("service parsion json size = "+list.size());
            for(ForecastItemDTO item : list){
                decodeCategory(item);
                // System.out.println(item);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private void decodeCategory(ForecastItemDTO item){
        String name = CategoryCode.valueOf(item.getCategory()).getName();
        String value = CategoryCode.getCodeValue(item.getCategory(), item.getFcstValue());
        String unit = CategoryCode.valueOf(item.getCategory()).getUnit();
        // System.out.println("name = "+name);
        // System.out.println("value = "+value);
        // System.out.println("unit = "+unit);
        // System.out.println("==========================");
        item.setCategoryName(name);
        try {
            float floatValue = Float.parseFloat(value);
            item.setFcstValue(floatValue +" "+ unit);
           
        } catch (Exception e) {
            item.setFcstValue(value); 
        } 
    }
}

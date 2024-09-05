package com.spring.react_spring_pjt.openapi.ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.react_spring_pjt.openapi.domian.ForecastItemDTO;
import com.spring.react_spring_pjt.openapi.domian.ForecastRequestDTO;
import com.spring.react_spring_pjt.openapi.service.ForecastService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ForecastController {

    final String numOfRows="12";

    @Autowired
    private ForecastService forecastService;

    @Value("${openApi.serviceKey}")
    private String serviceKey;

    @Value("${openApi.callBackUrl}")
    private String callBackUrl;

    @Value("${openApi.dataType}")
    private String dataType;
    
    @PostMapping("/validate/forecast")
    public ResponseEntity<Object> validateForecastApi(@Valid @RequestBody ForecastRequestDTO params, 
                                                        BindingResult bindingResult) {

        System.out.println("client end point : /api/forecast");
        System.out.println("serviceKey = "+serviceKey);
        System.out.println("callBackUrl = "+callBackUrl);
        System.out.println("dataType = "+dataType);
        // System.out.println("params = "+baseTime+"\t"+baseDate+"\t"+beachNum);

        // step 01) 스프링에서 제공되는 콜백URL에게 요청 파라미터를 보내는 것

        if( bindingResult.hasErrors() ) {
            System.out.println("debug >>> validate error");
            List<ObjectError> list = bindingResult.getAllErrors();
            Map<String, String> map = new HashMap<>();

            for(int idx=0; idx<list.size(); idx++){
                FieldError field = (FieldError)list.get(idx);
                String msg = list.get(idx).getDefaultMessage();
                System.out.println("debug >>> "+field.getField() + "\t" + msg);
                map.put(field.getField(), msg);
            }

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } else {

        String requestURL = callBackUrl+
                            "?serviceKey="+serviceKey+
                            "&dataType="+dataType+
                            "&base_date="+params.getBase_date()+
                            "&base_time="+params.getBase_time()+
                            "&beach_num="+params.getBeach_num()+
                            "&numOfRows="+numOfRows;

        System.out.println("url check = " + requestURL);

        HttpURLConnection http = null;
        InputStream stream = null;
        String result = null;
        List<ForecastItemDTO> list = null;

        try {
            URL url = new URL(requestURL);
            http = (HttpURLConnection)url.openConnection();
            System.out.println("http connection = " + http );
            int code = http.getResponseCode();
            System.out.println("http response code = " + code );
            if( code == 200){
                stream = http.getInputStream();
                result = readString(stream);
                System.out.println("result = "+result);

                list = forecastService.parsiongJson(result);
            } else{

            }
        } catch (Exception e) {
            
        }finally{

        }

        return new ResponseEntity<>(list,HttpStatus.OK);

        } // else end
    }

    public String readString(InputStream stream) throws IOException {
        BufferedReader br =  new BufferedReader( new InputStreamReader(stream, "UTF-8"));
        String input = null;
        StringBuilder result = new StringBuilder();
        while((input = br.readLine()) != null){
            result.append(input+"\n");
        } 
        br.close();
        return result.toString();
    }
    
}

package com.frequency.Modules.Json;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Title : Json관련 모듈
 * Author : 염형준
 * Date : 2017-02-13
 */

public class JsonHandler {

    // 문자열을 JSON 객체로 전환
    public static JSONObject stringToJson(String jsonString){
        try {
            JSONParser jsonParser = new JSONParser();
            return (JSONObject) jsonParser.parse( jsonString );
        }
        catch ( ParseException e ) {System.out.println( "Json parsing Error : "+e ); }
        return null;
    }

    // Request에서 문자열을 추출해
    public static JSONObject getJSONfromBody(HttpServletRequest request){

        JSONObject jsonObject;
        StringBuffer json = new StringBuffer();
        String line;

        try(
                BufferedReader reader = request.getReader()
        ){

            while ( (line=reader.readLine())!=null )
                json.append(line);

            jsonObject = stringToJson(json.toString());

            return jsonObject;

        }
        catch ( IOException e) { System.out.println("readLine Error : "+e); }

        return null;

    }

}

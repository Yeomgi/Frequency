package com.frequency.Modules;


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

    public static JSONObject getJSONfromBody(HttpServletRequest request){

        JSONParser jsonParser;
        JSONObject jsonObject;
        StringBuffer json = new StringBuffer();
        String line;

        try(
                BufferedReader reader = request.getReader()
        ){

            while ( (line=reader.readLine())!=null ){
                json.append(line);
            }

            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(json.toString());

            return jsonObject;

        } catch ( IOException e) { System.out.println("readLine Error : "+e);
        } catch ( ParseException e) { System.out.println("Json parsing Error : "+e);
        }

        return null;

    }

}

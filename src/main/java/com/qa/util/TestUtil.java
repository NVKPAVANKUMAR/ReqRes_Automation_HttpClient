package com.qa.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.data.Users;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import com.jayway.jsonpath.JsonPath;
public class TestUtil {

    public static String getValueByJPath(String responseString, String jpath){
        return JsonPath.read(responseString, jpath).toString();
    }

    public static String objectToJson(String filename, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String entryString;
        //Object to json file
        mapper.writeValue(
                new File(System.getProperty("user.dir")+"/src/main/java/com/qa/data/"+filename+".json")
                , object);

        //Object to json string
        entryString = mapper.writeValueAsString(object);
        return entryString;

    }

}

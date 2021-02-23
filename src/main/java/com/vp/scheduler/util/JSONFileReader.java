package com.vp.scheduler.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFileReader {
	
	String filePath;
	FileReader reader;
	JSONObject jsonObj;
	
	public JSONFileReader(String filePath){
		this.filePath = filePath;
		try {
			this.reader = getReader();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private FileReader getReader() throws FileNotFoundException {
		return  new FileReader(filePath);
		 	 	 
	}
	
	public String getJSONString() {
		
		
		return jsonObj.toString();
	}
	
	public JSONObject getJSONObject() {
		JSONParser jsonParser = new JSONParser();
		
        try 
        {
        	
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            jsonObj = (JSONObject) obj;
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return jsonObj;
        
	}
	
    
    
}


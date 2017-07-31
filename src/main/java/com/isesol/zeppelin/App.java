package com.isesol.zeppelin;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

public class App {
	public static void main(String[] args) throws MalformedURLException, IOException {		
		String result=null;
		String base = "http://10.10.192.88:8080/api/notebook";
		HttpURLConnection conn = (HttpURLConnection) new URL(base).openConnection();
		conn.setRequestProperty("Accept", "application/json");
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		int code = conn.getResponseCode();

		if (code == 200) {
			InputStream inStream1 = conn.getInputStream();
			result = IOUtils.toString(inStream1, "UTF-8");
			System.out.println(result);
		}
		
		 JsonParser parse =new JsonParser();  
		 try {
			 JsonObject json=(JsonObject) parse.parse(result);	
			 JsonArray array = json.get("body").getAsJsonArray();
			 for (int i=0; i< array.size(); i++){			 
				 JsonObject subObject = array.get(i).getAsJsonObject();
				 System.out.println("id: " + subObject.get("id"));
				 System.out.println("name: " + subObject.get("name"));
			 }
			 
		 }catch(JsonIOException  e){
			 e.printStackTrace();
			 
		 }
		 
	}
}

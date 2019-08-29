package com.cyient.test;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class lits_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> gfg = new ArrayList<String>() { 
            { 
       
            } 
        }; 
        
        JSONArray arraya = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("001", arraya);
        
        System.out.println(obj);

	}

}

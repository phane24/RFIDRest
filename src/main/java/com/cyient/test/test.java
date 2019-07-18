package com.cyient.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class test {

    public static void main(String[] args)
    {
        //JSON parser object to parse read file
    /*    JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("C:\\Users\\kv46112\\Desktop\\Ticket.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
             

 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/
    	
    	
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i <= 10; i++) {
                list.add(new Integer(i));
            }
            Collections.shuffle(list);
            for (int i = 0; i < 502; i++) {
                System.out.println(list.get(i)+"nd");
            }
           
      

}
}
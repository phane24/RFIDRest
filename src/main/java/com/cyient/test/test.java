package com.cyient.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cyient.dao.RFIDDAO;
import com.google.gson.Gson;

public class test {

	public static void main(String[] args) throws java.text.ParseException {
        String utcDateStr = "Mar 9, 2019";
        try {
            convert(utcDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
 
    public static void convert(String dateString) throws ParseException, java.text.ParseException {
        System.out.println("Given date is " + dateString);
 
        DateFormat sdf = new SimpleDateFormat("MMM dd,yyyy");
        Date date = sdf.parse(dateString);
 
       // System.out.println("MM/dd/yyyy formatted date : " + new SimpleDateFormat("MM/dd/yyyy").format(date));
        System.out.println("yyyy-MM-dd formatted date : " + new SimpleDateFormat("yyyy-MM-dd").format(date));
 
    }
}
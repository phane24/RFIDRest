package com.cyient.test;

import java.util.Scanner; 
  
public class hex { 
  
    // function to convert ASCII to HEX 
    public static String ASCIItoHEX(String ascii) 
    { 
        // Initialize final String 
        String hex = ""; 
  
        // Make a loop to iterate through 
        // every character of ascii string 
        for (int i = 0; i < ascii.length(); i++) { 
  
            // take a char from 
            // position i of string 
            char ch = ascii.charAt(i); 
  
            // cast char to integer and 
            // find its ascii value 
            int in = (int)ch; 
  
            // change this ascii value 
            // integer to hexadecimal value 
            String part = Integer.toHexString(in); 
  
            // add this hexadecimal value 
            // to final string. 
            hex += part; 
        } 
        // return the final string hex 
        return hex; 
    } 
  
    // Driver Function 
    public static void main(String arg[]) 
    { 
        // print the Hex String 
        System.out.println(ASCIItoHEX("RF-100_133")); 
    } 
} 
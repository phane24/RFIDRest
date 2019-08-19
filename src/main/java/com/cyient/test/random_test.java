package com.cyient.test;

import java.util.Random;

public class random_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random(); 
		String[]  status=new String[10];
		status[0]="reserve";
		status[1]="unreserve";
		status[2]="connected";
		
		String[]  ownershiop=new String[10];
		ownershiop[0]="3rd party";
		ownershiop[1]="Telstra";
		ownershiop[2]="NBN Cable";
				
		String[]  shelf=new String[10];
		ownershiop[0]="Sprliter";
		ownershiop[1]="FDIT";
		ownershiop[2]="SC";
		ownershiop[3]="FDT";

		String[]  equipment=new String[10];
		equipment[0]="Cable-SMOF FNPEHJ STD";
		equipment[1]="Cable-SMOF HENFHU STD";
		equipment[2]="Cable-SMOF EQUINE STD";
		equipment[3]="Cable-SMOF AUSTEH STD"; 
		
		
		String[]  sitename=new String[10];
		sitename[0]="BMBA 3306";
		sitename[1]="BMBA 3307";
		sitename[2]="BMBA 3308";
		sitename[3]="BMBA 3309"; 
		
		
		
		
		String[]  Alpha=new String[15];
		Alpha[0]="AB";
		Alpha[1]="CD";
		Alpha[2]="EF";
		Alpha[3]="GH"; 
		Alpha[4]="IJ";
		Alpha[5]="KL";
		Alpha[6]="MN";
		Alpha[7]="OP"; 		
		Alpha[8]="QR";
		Alpha[9]="ST";
		Alpha[10]="UV";
		Alpha[11]="WX"; 		
		Alpha[12]="YZ";

		
		String[]  Alpha_re=new String[15];
		Alpha_re[12]="AB";
		Alpha_re[11]="CD";
		Alpha_re[10]="EF";
		Alpha_re[9]="GH"; 
		Alpha_re[8]="IJ";
		Alpha_re[7]="KL";
		Alpha_re[6]="MN";
		Alpha_re[5]="OP"; 		
		Alpha_re[4]="QR";
		Alpha_re[3]="ST";
		Alpha_re[2]="UV";
		Alpha_re[1]="WX"; 		
		Alpha_re[0]="YZ";		
		
		String[]  fllor=new String[10];
		fllor[0]="1st";
		fllor[1]="2nd";
		fllor[2]="3rd";
		fllor[3]="4th"; 
		fllor[4]="5th";
		fllor[5]="6th";
		fllor[6]="7th";
		fllor[7]="8th";		
		
		String[]  racks=new String[10];
		racks[0]="R1";
		racks[1]="R2";
		racks[2]="R3";
		racks[3]="R4"; 
			
		
        for(int i=0;i<50;i++)
        {
            int rand_int2 = rand.nextInt(7); 
            
        	System.out.println(racks[getRandomNumberInRange(0,3)]);
        }
        
        
        
	}
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}

package com.isesol.zeppelin;

public class getfilename {
	public static void main(String[] args) {

		String filename = "nothing";

		filename = args[0];
		
		if(filename.equals("nothing")){
			System.out.println("no input filename");
		}else{
			System.out.println(filename);
		}

		

	}
}

package dam.m9.f1;

import java.io.File;
import java.io.FileInputStream;

public class SecurityManager {

	public static void main(String[] args) throws Exception
	{ 
		File f = new File ("/home/sbalaguer/Test.txt"); 
	//	try 
	 	(FileInputStream in = new FileInputStream(f)) 
	/*	{ */
			in.read(); 
	/*	}
		catch (Exception e)
		{
			System.out.println("No va: "+e.getMessage());
		}*/
	}
	
}

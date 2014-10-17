import java.io.File;
import java.io.FileInputStream;

public class SecurityManager {

	public static void main(String[] args) 
	{ 
		File f = new File ("/home/sbalaguer/Test.txt"); 
		try (FileInputStream in = new FileInputStream(f)) 
		{ 
			in.read(); 
		}
		catch (Exception e)
		{
			System.out.println("No va: "+e.getMessage());
		}
	}
	
}

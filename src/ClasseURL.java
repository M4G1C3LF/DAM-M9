
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



public class ClasseURL {

	public static void main(String[] args) throws IOException
	{
		URL myUrl = new URL("http://ioc.xtec.cat/materials/FP/Materials/2252_DAM/DAM_2252_M09/web/html/WebContent/u2/a1/continguts.html");
		
		System.out.println("Protocol: "+myUrl.getProtocol());
		System.out.println("Entitat Certificadora: "+myUrl.getAuthority());
		System.out.println("Path: "+myUrl.getPath());
		System.out.println("Query: "+myUrl.getQuery());
		System.out.println("Arxiu: "+myUrl.getFile());
		System.out.println("Port: "+myUrl.getPort());
		System.out.println("ToString: "+myUrl.toString());

		System.out.println("\n\n");
		
		URL myUrl2 = new URL("http","elpuig.xeill.net","/Members/malcocer/noticies_curs_2013-14/curs_2014-15/gravacio-de-la-conferencia-de-rafael-poch-de-feliu-el-conflicte-ducraina");
		System.out.println("Protocol: "+myUrl2.getProtocol());
		System.out.println("Entitat Certificadora: "+myUrl2.getAuthority());
		System.out.println("Path: "+myUrl2.getPath());
		System.out.println("Query: "+myUrl2.getQuery());
		System.out.println("Arxiu: "+myUrl2.getFile());
		System.out.println("Port: "+myUrl2.getPort());
		System.out.println("ToString: "+myUrl2.toString());
		
		InputStream is = myUrl2.openStream();
				
		while (is.available() > 0)
		{
			char c = (char) is.read();
			
			System.out.print(c);
		}
	}
}

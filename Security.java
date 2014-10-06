package dam.m9.f1;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class Security {
	
	public static final byte[] IV_PARAM = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
	
	static private SecretKey clau = null;
	static private int longClau;
	static private byte[] dadesXifrades;
	static private byte[] dadesDesxifrades;
	static private byte[][] dadesXifradesPublicKey;
	static private byte[] dadesDesxifradesPrivateKey;
	static private byte[]  hash;
	static private KeyPair keys;
	static private byte[] signatura;
	//static private KeyStore myKeyStore=null;
	
	static public SecretKey generateKey(int keySize) //Generate a SecretKey
	{ 
		SecretKey sKey = null; 
		if ((keySize == 128)||(keySize == 192)||(keySize == 256)) 
		{ 
			try 
			{ 
				KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
				kgen.init(keySize); sKey = kgen.generateKey(); 
			} 
			catch (NoSuchAlgorithmException ex) 
			{ 
				System.err.println("Generator not available"); 
			} 
		} 
		return sKey; 
	}
	
	static public byte[] cipherDataECB(SecretKey key, byte[] data, int mode) //Encript/Decript Data using ECB mode with SecretKey
	{ 
		byte[] encryptedData = null; 
		try 
		{ 
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
			cipher.init(mode, key);  
			encryptedData = cipher.doFinal(data); 
		} 
		catch (Exception ex) 
		{ 
			System.err.println("Error ciphering data: " + ex); 
		} 
		return encryptedData; 
	}
	
	public static byte[] cipherDataECB(PublicKey key, byte[] data, int mode) //Encript/Decript Data using ECB mode with PublicKey
	{ 
		byte[] encryptedData = null; 
		try 
		{ 
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE"); 
			cipher.init(mode, key); 
			encryptedData = cipher.doFinal(data); 
		} 
		catch (Exception ex) 
		{ 
			System.err.println("Error ciphering data:" + ex); 
		} 
		
		return encryptedData; 
	}
	public static byte[] cipherDataECB(PrivateKey key, byte[] data, int mode) //Encript/Decript Data using ECB mode with PrivateKey
	{ 
		byte[] encryptedData = null; 
		try 
		{ 
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE"); 
			cipher.init(mode, key); 
			encryptedData = cipher.doFinal(data); 
		} 
		catch (Exception ex) 
		{ 
			System.err.println("Error ciphering data:" + ex); 
		} 
		
		return encryptedData; 
	} 
	
	static public byte[] cipherDataCBC(SecretKey key, byte[] data, int mode) //Encript/Decript Data using CBC mode with SecretKey
	{ 
		byte[] encryptedData = null; 
		try 
		{ 
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
			IvParameterSpec iv = new IvParameterSpec(IV_PARAM); 
			cipher.init(mode, key, iv); 
			encryptedData = cipher.doFinal(data); 
		} 
		catch (Exception ex) 
		{ 
			System.err.println("Error ciphering data: " + ex); 
		} 
		return encryptedData; 
	}
	
	public static byte[][] encryptWrappedData(byte[] data, PublicKey key ) //Encrypt data with WRAPMode ECB and PublicKey
	{ 
		byte[][] encWrappedData = new byte[2][]; 
		try 
		{ 
			KeyGenerator kgen = KeyGenerator.getInstance("AES"); 
			kgen.init(128); SecretKey sKey = kgen.generateKey(); 
			Cipher cipher = Cipher.getInstance("AES"); 
			cipher.init(Cipher.ENCRYPT_MODE, sKey); 
			byte[] encMsg = cipher.doFinal(data); //ENCRIPTEM EL MISSATGE
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); 
			cipher.init(Cipher.WRAP_MODE, key); 
			byte[] encKey = cipher.wrap(sKey); //ENCRIPTEM CLAU
			encWrappedData[0] = encMsg; 
			encWrappedData[1] = encKey; 
		} 
		catch (Exception ex) 
		{ 
			System.err.println("Error encrypting data:" + ex); 
		} 
		
		return encWrappedData; 
	} 
	
	public static byte[] decryptWrappedData(byte[][] data, PrivateKey key)  //Decrypt data with WRAPMode ECB and PrivateKey
	{ 
		byte[] msg = null;
		try 
		{

			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); 
			cipher.init(Cipher.UNWRAP_MODE, key);
			SecretKey encKey = (SecretKey) cipher.unwrap(data[1],"AES", Cipher.SECRET_KEY); //DESNCRIPTEM CLAU
			
			cipher = Cipher.getInstance("AES"); 
			cipher.init(Cipher.DECRYPT_MODE, encKey); 
			msg = cipher.doFinal(data[0]); //DESENCRIPTEM EL MISSATGE
			

		} 
		catch (Exception ex) 
		{ 
			System.err.println("Error decripting data:" + ex); 
		} 
		
		return msg; 
	}
	
  public static byte[] generateHash(String text, int keySize) //Generate Hash from text
  {    
	  if ((keySize == 128)||(keySize == 192)||(keySize == 256)) 
	  {    	
	      try 
	      {
	        byte[] data = text.getBytes("UTF-8");
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(data);
	        return hash;
	      } 
	      catch (Exception ex) 
	      { 
	    	  System.err.println("Error generating key: " + ex); 
	      } 
	  } 
	  return null;
	   
  }  
	      
  public static KeyPair generateKeyPair(int len) //Generate a pair of keys (Private and Public)
  { 
	  KeyPair keys = null; 
	  try 
	  { 
		  KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
		  keyGen.initialize(len); 
		  keys = keyGen.genKeyPair();
		  return keys;
	  } 
	  catch (Exception ex) 
	  { 
		  System.err.println("Generator not available"); 
	  } 
	  
	  return keys;  
  }
  
  public static byte[] createSignature(byte[] data, PrivateKey priv) //Creating Signature
  { 
	  byte[] signature = null; 
	  try 
	  { 
		  Signature signer = Signature.getInstance("SHA1withRSA"); 
		  signer.initSign(priv); signer.update(data); 
		  signature = signer.sign(); 
	  } 
	  catch (Exception ex) 
	  { 
		  System.err.println("Error signing data: " + ex); 
	  } 
	  return signature; 
  }
  
  public static boolean validateSignature(byte[] data, byte[] signature, PublicKey pub) 
  { 
	  boolean isValid = false; 
	  try 
	  { 
		  Signature signer = Signature.getInstance("SHA1withRSA"); 
		  signer.initVerify(pub); 
		  signer.update(data); 
		  isValid = signer.verify(signature); 
	  } 
	  catch (Exception ex) 
	  { 
		  System.err.println("Error validant les dades: " + ex); 
	  } 
	  return isValid; 
  }
  
  public static KeyStore loadKeyStore(String ksFile, String ksPwd) throws Exception 
  { 
	  KeyStore ks = KeyStore.getInstance("JCEKS"); 
	  File f = new File (ksFile); 
	  if (f.isFile()) 
	  { 
		  FileInputStream in = new FileInputStream (f); 
		  ks.load(in, ksPwd.toCharArray()); 
	  } 
	  return ks; 
  }
  
  public static void main (String[] args) 
  {
	  KeyStore myKeyStore=null;
	  	try
		{
			if (args.length == 0)
			{
				System.err.println("I need a parameter");
				return;
			}
			else
			{
				longClau=Integer.parseInt(args[0]);
			}
			
			clau = generateKey(longClau);
			
			System.out.println("Clau: "+clau.getEncoded());
			System.out.println("Clau en UFT8: "+new String(clau.getEncoded(),"UTF-8"));
			
			
			dadesXifrades = cipherDataECB(clau, new String("Hola Benvinguts!").getBytes(),Cipher.ENCRYPT_MODE);
			System.out.println("Dades XifradesEBC: "+dadesXifrades);
			
			dadesDesxifrades = cipherDataECB(clau, dadesXifrades,Cipher.DECRYPT_MODE);
			System.out.println("Dades DesxifradesEBC: "+new String(dadesDesxifrades,"UTF-8"));
			
			hash = generateHash("CONTRASEÑA",256);
			System.out.println("Mi hash generado: "+hash);
			
			dadesXifrades = cipherDataCBC(clau, new String("Hola Benvinguts!").getBytes(),Cipher.ENCRYPT_MODE);
			System.out.println("Dades XifradesCBC: "+dadesXifrades);
			
			dadesDesxifrades = cipherDataCBC(clau, dadesXifrades,Cipher.DECRYPT_MODE);
			System.out.println("Dades DesxifradesCBC: "+new String(dadesDesxifrades,"UTF-8"));
			
			keys = generateKeyPair(1024); //MÍNIM 1024 bits
			
			dadesXifrades = cipherDataECB(keys.getPrivate(), new String("Dades Privades").getBytes(),Cipher.ENCRYPT_MODE);
			System.out.println("Dades XifradesEBC amb clau privada: "+dadesXifrades);
			
			dadesDesxifrades = cipherDataECB(keys.getPublic(), dadesXifrades,Cipher.DECRYPT_MODE);
			System.out.println("Dades DesxifradesEBC amb clau publica: "+new String(dadesDesxifrades,"UTF-8"));
			
			dadesXifrades = cipherDataECB(keys.getPublic(), new String("Dades Públiques").getBytes(),Cipher.ENCRYPT_MODE);
			System.out.println("Dades XifradesEBC amb clau pública: "+dadesXifrades);
			
			dadesDesxifrades = cipherDataECB(keys.getPrivate(), dadesXifrades,Cipher.DECRYPT_MODE);
			System.out.println("Dades DesxifradesEBC amb clau privada: "+new String(dadesDesxifrades,"UTF-8"));
			
			dadesXifradesPublicKey= encryptWrappedData(dadesDesxifrades,keys.getPublic());
			System.out.println("Dades Xifrades amb clau pública: "+ dadesXifradesPublicKey.toString());
			
			dadesDesxifradesPrivateKey= decryptWrappedData(dadesXifradesPublicKey,keys.getPrivate());
			System.out.println("Dades Desxifrades amb clau privada: "+new String(dadesDesxifradesPrivateKey));
			
			signatura = createSignature(dadesDesxifradesPrivateKey,keys.getPrivate());
			System.out.println("Signatura creada - ToString() "+signatura.toString());
			
			System.out.println("És la signatura correcta? "+validateSignature(dadesDesxifradesPrivateKey,signatura,keys.getPublic()));
			
			try {
				myKeyStore = loadKeyStore("/home/sbalaguer/.keystore", "tyghbn67");
				System.out.println("Valor de myKeyStore: "+myKeyStore);
				System.out.println("Nº d'entrades: "+myKeyStore.size());
				
				int i = 0;
				for (Enumeration<String> alias = myKeyStore.aliases(); alias.hasMoreElements();)
				{
					System.out.println("Clau nº "+i+": "+alias.nextElement());
					i++;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		catch(NumberFormatException e)
		{
			System.err.println("El parámetro debe ser un numero entero");
		}
		catch(UnsupportedEncodingException e)
		{
			System.err.println("No se puede codificar la clave");
		}
	
		
	}	
}
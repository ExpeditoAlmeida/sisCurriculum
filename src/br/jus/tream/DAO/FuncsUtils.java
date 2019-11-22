package br.jus.tream.DAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FuncsUtils {
	
	private static FuncsUtils functUtils;
	
	public static FuncsUtils getInstance(){
	 	if (functUtils==null) {
	 		functUtils = new FuncsUtils();
	 	}
	 	return functUtils;
	 }
	
	public String encriptar(String senha) throws NoSuchAlgorithmException {
      if (senha.length()==0)
        {
            return null;
        }
        else
        {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(senha.getBytes());
            byte[] hash = md.digest();
            StringBuffer senhaEncrip = new StringBuffer();
            for (int i=0;i<hash.length;i++)
            {
                senhaEncrip.append(Integer.toHexString(hash[i]&0xff));
            }
            return senhaEncrip.toString();
        }
    }
	
	
	public byte[] readBytes(File file) throws IOException {
		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			byte[] buffer = new byte[4096];
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			int read = 0;
			while ((read = ios.read(buffer)) != -1) {
				ous.write(buffer, 0, read);
			}
		} finally {
			try {
				if (ous != null)
					ous.close();
			} catch (IOException e) {
			}

			try {
				if (ios != null)
					ios.close();
			} catch (IOException e) {
			}
		}
		return ous.toByteArray();
	}
	
	
	
	/*
	public String encrypt(String pText) {
		BasicPasswordEncryptor passwordEncript = new BasicPasswordEncryptor();
		return passwordEncript.encryptPassword(pText);
	}
	
	public boolean checkEncrypted(String pText, String encryptedText) {
		BasicPasswordEncryptor passwordEncript = new BasicPasswordEncryptor();
		return passwordEncript.checkPassword(pText, encryptedText);
	}
	*/		

	public static void main(String[] args) {
		FuncsUtils gerador = FuncsUtils.getInstance();
		String senha = "mmv123@";
		try {
			String senhaEncriptada = gerador.encriptar(senha);
			System.out.println("Senha : " + senha + " / Encriptada = " + senhaEncriptada);
			// c2e3c9865495ed86e648c40673d2972d2842759
			if (gerador.encriptar(senha).equals("c2e3c9865495ed86e648c40673d2972d2842759")) {
				System.out.println("SEnha ok");
			}else {System.out.println("SEnha não confere");}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}

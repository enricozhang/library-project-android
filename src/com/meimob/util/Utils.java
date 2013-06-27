package com.meimob.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class Utils {

    private static char[] hexDigits = "0123456789abcdef".toCharArray();

    /**
     * When downloading a file over internet, the uploader usually provides an
     * MD5 checksum for the file to ensure the integrity of the file. MD5
     * checksum (message digest) is something like a fingerprint or digital
     * signature for a file. The checksum is unique for a file and there is very
     * small possibility of getting two identical checksums for two different
     * files. In Android, using java security package we can compute the md5
     * message digest for a file. Here is a sample class to get MD5 checksum
     * from an input stream
     * 
     * @param is
     *            file of inputstream
     * @return
     * @throws IOException
     */
    public static String md5(InputStream is) throws IOException {
	String md5 = "";
	try {
	    byte[] bytes = new byte[4096];
	    int read = 0;
	    MessageDigest digest = MessageDigest.getInstance("MD5");
	    while ((read = is.read(bytes)) != -1) {
		digest.update(bytes, 0, read);
	    }
	    byte[] messageDigest = digest.digest();
	    StringBuilder sb = new StringBuilder(32);
	    for (byte b : messageDigest) {
		sb.append(hexDigits[(b >> 4) & 0x0f]);
		sb.append(hexDigits[b & 0x0f]);
	    }
	    md5 = sb.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return md5;
    }

    /**
     * To get md5 checksum from a file and compare it with original md5
     * 
     * @param md5Origin original file's md5 checksum
     * @param file fill with the real file path name
     * @return
     */
    public boolean checkmd5Sum(final String md5Origin, String file) {
	try {
	    FileInputStream fis = new FileInputStream(file);
	    String md5Checksum = md5(fis);
	    if (md5Checksum.equals(md5Origin)) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return false;
    }
}

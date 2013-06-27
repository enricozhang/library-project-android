package com.meimob.util.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 
 * Utility class commonly useful in read/write stream programming.
 * 
 * @author: Enrico Zhang
 * @rewrite: Enrico Zhang
 * 2011-8-1 下午01:04:16
 * 
 * @version 1.0.0
 * 
 */
public class InputStreamUtils {
	
	/**
	 * convert inputstream to String method
	 * @param the inputstream 
	 * @return the result of the inputstream convert to String
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	*/
	public static String InputStreamToString(InputStream paramInputStream)
			throws IOException {
		byte[] arrayOfByte = inputStreamToByteArray(paramInputStream);
		paramInputStream.close();
		return new String(arrayOfByte);
	}

	/**
	 * convert inpustream to bytes array
	 * @param paramInputStream
	 * @return
	 * @throws IOException 
	 * @exception 
	 * @since  1.0.0
	*/
	public static byte[] inputStreamToByteArray(InputStream paramInputStream)
			throws IOException {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		inputStreamToOutputStream(paramInputStream, localByteArrayOutputStream);
		return localByteArrayOutputStream.toByteArray();
	}

	public static void inputStreamToOutputStream(InputStream paramInputStream,
			OutputStream paramOutputStream) throws IOException {
		byte[] arrayOfByte = new byte[1024];
		while (true) {
			int i = paramInputStream.read(arrayOfByte, 0, 1024);
			if (i == -1) {
				paramOutputStream.flush();
				return;
			}
			paramOutputStream.write(arrayOfByte, 0, i);
		}
	}

	/**
	 * convert String object to bytes array
	 * @param the source String
	 * @return  the bytes array
	 * @exception 
	 * @since  1.0.0
	*/
	public static InputStream stringToInputStream(String paramString) {
		byte[] arrayOfByte = paramString.getBytes();
		return new ByteArrayInputStream(arrayOfByte);
	}
}

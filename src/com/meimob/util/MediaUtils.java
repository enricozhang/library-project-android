package com.meimob.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class MediaUtils {
    
    /**
     * The drawable objects into bitmap and then saved to Gallery, If you need to immediately send a broadcast display at the Gallery
     * @param context
     * @param drawable
     * @param picName
     * @return
     */
    public static String saveImageToGallery(Context context, Drawable drawable, String picName){
	Bitmap bitmap = BitmapUtils.drawableToBitmap(drawable);
	
	String result = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, picName, ""); 
	if(result == null){
	    return null;
	}
	context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory()))); 
	return "";
	
    }
    
    /**
     * The drawable objects into bitmap and then saved to SDcard after refresh gallery
     * @param context
     * @param drawable
     * @param picName
     * @return
     */
    public static String saveImageToSDcard(Context context, Drawable drawable, String picName){
	Bitmap bitmap = BitmapUtils.drawableToBitmap(drawable);
	File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/com.google/" + File.separator + picName);
	try {
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		if(!file.exists())
			file.createNewFile();
		
		FileOutputStream out = new FileOutputStream(file);
		bitmap.compress(CompressFormat.JPEG, 100, out);
		try {
			out.flush();
		} catch (IOException e) {
        e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
        e.printStackTrace();
		}
		return file.getAbsolutePath();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// notification gallery to refresh
	MediaScannerConnection.scanFile(context,
	            new String[] { file.toString() }, null,
	            new MediaScannerConnection.OnScanCompletedListener() {
	        public void onScanCompleted(String path, Uri uri) {
	            Log.i("ExternalStorage", "Scanned " + path + ":");
	            Log.i("ExternalStorage", "-> uri=" + uri);
	        }
	    });
	return null;
    }

}

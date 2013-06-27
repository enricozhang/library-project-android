package com.meimob.util;

import java.lang.ref.WeakReference;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapUtils {
    
    static class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
	    private final WeakReference<ImageView> imageViewReference;
	    private int data = 0;

	    public BitmapWorkerTask(ImageView imageView) {
	        // Use a WeakReference to ensure the ImageView can be garbage collected
	        imageViewReference = new WeakReference<ImageView>(imageView);
	    }

	    // Decode image in background.
	    @Override
	    protected Bitmap doInBackground(Integer... params) {
	        data = params[0];
	     //   return decodeSampledBitmapFromResource(getResources(), data, 100, 100));
	        return null;
	    }

	    // Once complete, see if ImageView is still around and set bitmap.
	    @Override
	    protected void onPostExecute(Bitmap bitmap) {
	        if (imageViewReference != null && bitmap != null) {
	            final ImageView imageView = imageViewReference.get();
	            if (imageView != null) {
	                imageView.setImageBitmap(bitmap);
	            }
	        }
	    }
	}
    
    

    /**
     * To use this method, first decode with inJustDecodeBounds set to true,
     * pass the options through and then decode again using the new inSampleSize
     * value and inJustDecodeBounds set to false
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res,
	    int resId, int reqWidth, int reqHeight) {

	// First decode with inJustDecodeBounds=true to check dimensions
	final BitmapFactory.Options options = new BitmapFactory.Options();
	options.inJustDecodeBounds = true;
	BitmapFactory.decodeResource(res, resId, options);

	// Calculate inSampleSize
	options.inSampleSize = calculateInSampleSize(options, reqWidth,
		reqHeight);

	// Decode bitmap with inSampleSize set
	options.inJustDecodeBounds = false;
	return BitmapFactory.decodeResource(res, resId, options);
    }
    
    /**
     * To use this method, first decode with inJustDecodeBounds set to true,
     * pass the options through and then decode again using the new inSampleSize
     * value and inJustDecodeBounds set to false
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromByteArray(byte[] data,
	    int reqWidth, int reqHeight) {

	// First decode with inJustDecodeBounds=true to check dimensions
	final BitmapFactory.Options options = new BitmapFactory.Options();
	options.inJustDecodeBounds = true;
	BitmapFactory.decodeByteArray(data, 0, data.length, options);

	// Calculate inSampleSize
	options.inSampleSize = calculateInSampleSize(options, reqWidth,
		reqHeight);

	// Decode bitmap with inSampleSize set
	options.inJustDecodeBounds = false;
	return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }
    
    /**
     * To use this method, first decode with inJustDecodeBounds set to true,
     * pass the options through and then decode again using the new inSampleSize
     * value and inJustDecodeBounds set to false
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName,
	    int reqWidth, int reqHeight) {

	// First decode with inJustDecodeBounds=true to check dimensions
	final BitmapFactory.Options options = new BitmapFactory.Options();
	options.inJustDecodeBounds = true;
	BitmapFactory.decodeFile(pathName, options);

	// Calculate inSampleSize
	options.inSampleSize = calculateInSampleSize(options, reqWidth,
		reqHeight);

	// Decode bitmap with inSampleSize set
	options.inJustDecodeBounds = false;
	return BitmapFactory.decodeFile(pathName, options);
    }

    /**
     * To tell the decoder to subsample the image, loading a smaller version
     * into memory, set inSampleSize to true in your BitmapFactory.Options
     * object. For example, an image with resolution 2048x1536 that is decoded
     * with an inSampleSize of 4 produces a bitmap of approximately 512x384.
     * Loading this into memory uses 0.75MB rather than 12MB for the full image
     * (assuming a bitmap configuration of ARGB_8888). Here’s a method to
     * calculate a the sample size value based on a target width and height:
     * 
     * @param options
     * @param reqWidth
     *            target width of imageview
     * @param reqHeight
     *            target height and height
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
	    int reqWidth, int reqHeight) {
	// Raw height and width of image
	final int height = options.outHeight;
	final int width = options.outWidth;
	int inSampleSize = 1;

	if (height > reqHeight || width > reqWidth) {

	    // Calculate ratios of height and width to requested height and
	    // width
	    final int heightRatio = Math.round((float) height
		    / (float) reqHeight);
	    final int widthRatio = Math.round((float) width / (float) reqWidth);

	    // Choose the smallest ratio as inSampleSize value, this will
	    // guarantee
	    // a final image with both dimensions larger than or equal to the
	    // requested height and width.
	    inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	}

	return inSampleSize;
    }

    /**
     * 该方法是将｛@link Drawable} 对象转换成｛@link Bitmap} 对象
     * 
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
	if (drawable == null) {
	    return null;
	}
	Bitmap bitmap = Bitmap
		.createBitmap(
			drawable.getIntrinsicWidth(),
			drawable.getIntrinsicHeight(),
			drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565);
	if (bitmap == null) {
	    return null;
	}
	Canvas canvas = new Canvas(bitmap);
	drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
		drawable.getIntrinsicHeight());
	drawable.draw(canvas);
	return bitmap;
    }

}

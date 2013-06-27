package com.meimob.util;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DrawableFactory {
	
	private static ConcurrentHashMap<Integer, WeakReference<Bitmap>> map = new ConcurrentHashMap<Integer, WeakReference<Bitmap>>();

	public static Drawable getDrawable(Context paramContext, int paramInt) {
		Drawable localObject1;
		if (paramInt == 0) {
			localObject1 = null;
			return localObject1;
		}
		
		
		ConcurrentHashMap<Integer, WeakReference<Bitmap>> localConcurrentHashMap1 = map;
		Integer localInteger1 = Integer.valueOf(paramInt);
		WeakReference<Bitmap> localWeakReference1 = (WeakReference<Bitmap>) localConcurrentHashMap1.get(localInteger1);
		Bitmap localBitmap1;
		if (localWeakReference1 == null) {
			localBitmap1 = null;
		}
		for (Drawable localObject2 = null;; localObject2 = new BitmapDrawable(localBitmap1)) {
			if (localObject2 == null) {
				localObject2 = paramContext.getResources()
						.getDrawable(paramInt);
				if ((localObject2 instanceof BitmapDrawable)) {
					Bitmap localBitmap2 = ((BitmapDrawable) localObject2)
							.getBitmap();
					ConcurrentHashMap<Integer, WeakReference<Bitmap>> localConcurrentHashMap2 = map;
					Integer localInteger2 = Integer.valueOf(paramInt);
					WeakReference<Bitmap> localWeakReference2 = new WeakReference<Bitmap>(
							localBitmap2);
					localConcurrentHashMap2.put(localInteger2,
							localWeakReference2);
				}
			}
			localObject1 = localObject2;
			localBitmap1 = (Bitmap) localWeakReference1.get();
		}
	}
}

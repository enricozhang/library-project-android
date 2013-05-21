package com.meimob.app.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager.LayoutParams;

public class FullScreenDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	Dialog dialog = new Dialog(this, R.style.mydialog);  
	dialog.setContentView(R.layout.layout);  
	LayoutParams lay = dialog.getWindow().getAttributes();  
	setParams(lay);  
	dialog.show();  
	
    }
    
    private void setParams(LayoutParams lay) {
	  DisplayMetrics dm = new DisplayMetrics();
	  getWindowManager().getDefaultDisplay().getMetrics(dm);
	  Rect rect = new Rect();
	  View view = getWindow().getDecorView();
	  view.getWindowVisibleDisplayFrame(rect);
	  lay.height = dm.heightPixels - rect.top;
	  lay.width = dm.widthPixels;
	 }
    

}

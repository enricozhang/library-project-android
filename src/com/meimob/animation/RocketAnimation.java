package com.meimob.animation;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.meimob.app.R;

public class RocketAnimation {
    public static void addImage(Context context){
//	ImageView rocketImage = (ImageView) findViewById(R.id.animation);
	ImageView rocketImage = new ImageView(context);
	rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
	final AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
	rocketImage.setOnClickListener(new OnClickListener(){

	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		rocketAnimation.start();
	    }
	    
	});
    }

}

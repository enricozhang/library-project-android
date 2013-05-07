package com.meimob.app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.meimob.app.R;

public class CustomProgressDialog extends Dialog {
	   private Context context = null;
	 
	   private TextView tv_msg;
	 
	   public CustomProgressDialog(Context context) {
	      super(context);
	      this.context = context;
	 
	   }
	 
	   public CustomProgressDialog(Context context, boolean cancelable,
	         OnCancelListener cancelListener) {
	 
	      super(context, cancelable, cancelListener);
	      this.context = context;
	      // TODO Auto-generated constructor stub
	   }
	 
	   public CustomProgressDialog(Context context, int theme) {
	 
	      super(context, theme);
	      this.context = context;
	      // 加载自己定义的布局
	      View view = LayoutInflater.from(context)
	            .inflate(R.layout.loading, null);
	 
	      ImageView img_loading = (ImageView) view.findViewById(R.id.img_loading);
	      ImageView img_close = (ImageView) view.findViewById(R.id.img_close);
	      tv_msg = (TextView) view.findViewById(R.id.tv_msg);
	      // 加载XML文件中定义的动画
	      RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils
	            .loadAnimation(context, R.anim.rotate_refresh_drawable_default);
	      // 开始动画
	      img_loading.setAnimation(rotateAnimation);
	      //为Dialoge设置自己定义的布局
	      setContentView(view);
	      //为close的那个文件添加事件
	      img_close.setOnClickListener(new View.OnClickListener() {
	 
	         public void onClick(View v) {
	            dismiss();
	 
	         }
	      });
	 
	   }
	 
	   public void setMsg(String msg) {
	 
	      if (null != tv_msg) {
	         tv_msg.setText(msg);
	      }
	   }
	 
	   public void setMsg(int resId) {
	 
	      if (null != tv_msg) {
	         tv_msg.setText(context.getString(resId));
	      }
	   }
	 
	}

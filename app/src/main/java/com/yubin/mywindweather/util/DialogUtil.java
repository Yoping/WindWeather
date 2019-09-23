package com.yubin.mywindweather.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.yubin.mywindweather.R;

/**
 * Created by YUBIN at 17-11-2 下午4:51
 * Last modified at 17-7-28 下午4:26
 */

/**
 * Created by YUBIN at 17-7-28 下午4:20
 * Last modified at 17-3-27 上午10:54
 */

public class DialogUtil {
	
	
	
	// 左边Button方法
		public interface OnClickLeftListener {
			abstract void onClick();
		}

		public interface OnClickCententListener {
			abstract void onClick();
		}

		// 右边Button方法
		public interface OnClickRightListener {
			abstract void onClick();
		}
		
		public interface OnClickButtonListener {
			abstract void onClick();
		}
		
		
//		public static void showTwoButtonDialog(Context context, String content, String leftName,
//											   String rightName, boolean cancelable, final OnClickLeftListener listenerLeft, final OnClickRightListener listenerRight) {
//
//			final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//			alertDialog.show();
//			Window window = alertDialog.getWindow();
//			window.setContentView(R.layout.custom_dialog);
//			TextView tvText=(TextView)window.findViewById(R.id.tv_dialog_text);
//			TextView tvLeftName=(TextView)window.findViewById(R.id.tv_dialog_yes);
//			TextView tvRightName=(TextView)window.findViewById(R.id.tv_dialog_no);
//			tvText.setText(content);
//			tvLeftName.setText(leftName);
//			tvRightName.setText(rightName);
//
//			tvLeftName.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View arg0) {
//					if (listenerLeft != null) {
//						listenerLeft.onClick();
//						alertDialog.dismiss();
//					}
//
//				}
//			});
//			tvRightName.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View arg0) {
//					if (listenerRight != null) {
//						listenerRight.onClick();
//						alertDialog.dismiss();
//					}
//
//				}
//			});
//			alertDialog.setCancelable(cancelable);
//
//		}
	public static void showTwoButtonDialog(Context context, String title,String leftName,
										   String rightName, boolean cancelable, final OnClickLeftListener listenerLeft, final OnClickRightListener listenerRight) {

		final AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.custom_dailog).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setContentView(R.layout.custom_dialog);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_text);
		TextView tvLeftName = (TextView) window.findViewById(R.id.tv_dialog_yes);
		TextView tvRightName = (TextView) window.findViewById(R.id.tv_dialog_no);
		tvTitle.setText(title);
		tvLeftName.setText(leftName);
		tvRightName.setText(rightName);

		tvLeftName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (listenerLeft != null) {
					listenerLeft.onClick();
					alertDialog.dismiss();
				}

			}
		});
		tvRightName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (listenerRight != null) {
					listenerRight.onClick();
					alertDialog.dismiss();
				}

			}
		});
		alertDialog.setCancelable(cancelable);

	}

		
		public static void showOneButtonDialog(Context context, String content, String buttonName,
											   boolean cancelable, final OnClickButtonListener buttonListener) {
			
			final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
			alertDialog.show();
			Window window = alertDialog.getWindow();
			window.setContentView(R.layout.custom_dialog);
			TextView tvText=(TextView)window.findViewById(R.id.tv_dialog_text);
			TextView tvLeftName=(TextView)window.findViewById(R.id.tv_dialog_yes);
			TextView tvRightName=(TextView)window.findViewById(R.id.tv_dialog_no);
			TextView tvLine=(TextView)window.findViewById(R.id.tv_dialog_line);
			tvText.setText(content);
			tvLeftName.setText(buttonName);
			
			tvLine.setVisibility(View.GONE);
			tvRightName.setVisibility(View.GONE);
			
			tvLeftName.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					if (buttonListener != null) {
						buttonListener.onClick();
						alertDialog.dismiss();
					}
					
				}
			});
		
			alertDialog.setCancelable(cancelable);
			
		}
		
		
}

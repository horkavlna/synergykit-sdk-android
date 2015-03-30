package com.synergykit.sampleapp.widgets;

import android.content.Context;
import android.view.View;

import com.synergykit.sampleapp.R;


public class ErrorAlertDialog {
	
	public CustomAlertDialog alertDialogError;

	public ErrorAlertDialog(Context context, String error) {
		
		alertDialogError = new CustomAlertDialog(context) {

			@Override
			public void onButtonPositive(View view) {

			}

			@Override
			public void onButtonNegative(View view) {

			}
		};
		
		alertDialogError.setTitleImageTint(context.getResources().getColor(
				R.color.alertError));
		alertDialogError.setTitleTextColor(context.getResources().getColor(
				R.color.alertError));
		alertDialogError.setTitleBackground(R.drawable.alert_error);
		alertDialogError.setTitleImage(R.drawable.ic_launcher);
		alertDialogError.setTitleText(context.getString(R.string.app_name)); 
		alertDialogError.setMessageText(error);
		alertDialogError.setButtonPositive("ok");
		alertDialogError.setCancelable(true);
	}

	public void show() {
		alertDialogError.show();
	}
}

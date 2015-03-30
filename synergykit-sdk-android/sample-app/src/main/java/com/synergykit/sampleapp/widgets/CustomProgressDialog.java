package com.synergykit.sampleapp.widgets;

import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgressDialog {

	private Context context;
	private ProgressDialog pd;
	private String text;
	
	public CustomProgressDialog (Context context, String text){
		this.text = text;
		
		pd = new ProgressDialog(context);
		pd.setCancelable(false);
		pd.setMessage(text);
		show();
	}
	
	public void show(){
		pd.show();
	}
	
	public void dismiss(){
		pd.dismiss();
	}
	

}

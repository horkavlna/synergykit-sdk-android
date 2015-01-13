package com.synergykit.sampleapp.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.synergykit.sampleapp.R;
import com.synergykit.sampleapp.helper.CustomAlertDialogHelper;


/**
 * KMF alert dialogs. Class was created for customizing title, message (append
 * message with image) and buttons background. Customized alert dialog use
 * layout "R.layout.alert_dialog_with_title_and_messsage", where all widgets is
 * hidden (visibility = gone). When any of widget is set by methods in this
 * class, coresponding layout and widget is set to visible. Created by goldj on
 * 7.2.14.
 */
public abstract class CustomAlertDialog {
	public static String TAG = CustomAlertDialog.class.getName();

	private static final int AD_WITH_TITLE_MESSAGE_BUTTONS = R.layout.alert_dialog_with_title_messsage_buttons;

	/**
	 * Resouorce ids
	 */
	private static final int alertDialogTitle = R.id.alertDialogTitle;
	private static final int alertDialogTitleImage = R.id.alertDialogTitleImage;
	private static final int alertDialogTitleText = R.id.alertDialogTitleText;
	private static final int alertDialogMessage = R.id.alertDialogMessage;
	private static final int alertDialogMessageImage = R.id.alertDialogMessageImage;
	private static final int alertDialogMessageText = R.id.alertDialogMessageText;
	private static final int alertDialogContainer = R.id.alertDialogContainer;
	private static final int alertDialogButtons = R.id.alertDialogButtons;
	private static final int alertDialogButtonPositive = R.id.alertDialogButtonPositive;
	private static final int alertDialogButtonNegative = R.id.alertDialogButtonNegative;
	private static final int alertDialogButtonSeparator = R.id.alertDialogButtonSeparator;

	private Context mContext;
	private AlertDialog mAlertDialog;
	private AlertDialog.Builder mBuilder;
	private View mView;

	/**
	 * Create instance of AlertDialog.Builder and set cancelable = true as
	 * default.
	 * 
	 * @param context
	 */
	public CustomAlertDialog(Context context) {
		mContext = context;
		mBuilder = new AlertDialog.Builder(context);
		mBuilder.setCancelable(false);
		mView = LayoutInflater.from(mContext).inflate(
				AD_WITH_TITLE_MESSAGE_BUTTONS, null);
	}

	/**
	 * Set title background resource.
	 * 
	 * @param resources
	 */
	public void setTitleBackground(int resources) {
		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogTitle));
		mView.findViewById(alertDialogTitle).setBackgroundResource(resources);
	}

	/**
	 * Set title image.
	 * 
	 * @param image
	 */
	public void setTitleImage(int image) {
		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogTitle));
		CustomAlertDialogHelper.setVisibilityVisible(CustomAlertDialogHelper
                .getImageView(mView, alertDialogTitleImage));
		CustomAlertDialogHelper.getImageView(mView, alertDialogTitleImage)
				.setImageResource(image);
	}

	public LinearLayout setLayout(int layout, Context context) {

		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogContainer));

		LinearLayout customLayout = (LinearLayout) LayoutInflater.from(context)
				.inflate(layout, null);

		LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		customLayout.setLayoutParams(layoutParams);

		CustomAlertDialogHelper.getLayout(mView, alertDialogContainer).addView(
				customLayout);

		return customLayout;

	}

	/**
	 * Set title image tint(color).
	 * 
	 * @param color
	 */
	public void setTitleImageTint(int color) {
		CustomAlertDialogHelper.getImageView(mView, alertDialogTitleImage)
				.setColorFilter(color);
	}

	/**
	 * Set title text.
	 * 
	 * @param text
	 */
	public void setTitleText(int text) {
		setTitleText(mContext.getString(text));
	}

	/**
	 * Set title text color.
	 * 
	 * @param color
	 */
	public void setTitleTextColor(int color) {
		CustomAlertDialogHelper.getTextView(mView, alertDialogTitleText)
				.setTextColor(color);
	}

	/**
	 * Set title text.
	 * 
	 * @param text
	 */
	public void setTitleText(CharSequence text) {
		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogTitle));
		CustomAlertDialogHelper.setVisibilityVisible(CustomAlertDialogHelper
                .getTextView(mView, alertDialogTitleText));
		CustomAlertDialogHelper.getTextView(mView, alertDialogTitleText)
				.setText(text);
	}

	/**
	 * Set message image.
	 * 
	 * @param image
	 */
	public void setMessageImage(int image) {
		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogMessage));
		CustomAlertDialogHelper.setVisibilityVisible(CustomAlertDialogHelper
                .getImageView(mView, alertDialogMessageImage));
		CustomAlertDialogHelper.getImageView(mView, alertDialogMessageImage)
				.setImageResource(image);
	}

	/**
	 * Set message text.
	 * 
	 * @param text
	 */
	public void setMessageText(int text) {
		setMessageText(mContext.getString(text));
	}

	/**
	 * Set message text.
	 * 
	 * @param text
	 */
	public void setMessageText(CharSequence text) {
		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogMessage));
		CustomAlertDialogHelper.setVisibilityVisible(CustomAlertDialogHelper
                .getTextView(mView, alertDialogMessageText));
		CustomAlertDialogHelper.getTextView(mView, alertDialogMessageText)
				.setText(text);
	}

	/**
	 * Set button text.
	 * 
	 * @param text
	 */
	public void setButtonPositive(int text) {
		setButtonPositive(mContext.getString(text));
	}

	/**
	 * Set button positive text.
	 * 
	 * @param text
	 */
	public void setButtonPositive(CharSequence text) {
		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogButtons));
		Button buttonPositive = CustomAlertDialogHelper.getButton(mView,
                alertDialogButtonPositive);
		CustomAlertDialogHelper.setVisibilityVisible(buttonPositive);
		buttonPositive.setText(text);
		buttonPositive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
				onButtonPositive(view);
			}
		});
	}

	/**
	 * Set button negative text.
	 * 
	 * @param text
	 */
	public void setButtonNegative(int text) {
		setButtonNegative(mContext.getString(text));
	}

	/**
	 * Set button negative text.
	 * 
	 * @param text
	 */
	public void setButtonNegative(CharSequence text) {
		CustomAlertDialogHelper.setVisibilityVisible(mView
                .findViewById(alertDialogButtons));
		Button buttonNegative = CustomAlertDialogHelper.getButton(mView,
                alertDialogButtonNegative);
		View separatorView = CustomAlertDialogHelper.getView(mView,
                alertDialogButtonSeparator);

		CustomAlertDialogHelper.setVisibilityVisible(buttonNegative);
		CustomAlertDialogHelper.setVisibilityVisible(separatorView);
		buttonNegative.setText(text);
		buttonNegative.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
				onButtonNegative(view);
			}
		});
	}

	/**
	 * Set cancelable.
	 * 
	 * @param cancelable
	 */
	public void setCancelable(boolean cancelable) {
		if (mBuilder != null)
			mBuilder.setCancelable(cancelable);
	}

	/**
	 * Show alert dialog;
	 */
	public void show() {
		if (mBuilder != null) {
			mBuilder.setView(mView);
			mAlertDialog = mBuilder.create();
			mAlertDialog.show();
		}
	}

	/**
	 * Dismiss alert dialog.
	 */
	public void dismiss() {
		if (mAlertDialog != null)
			mAlertDialog.dismiss();
	}

	/**
	 * Check if alert dialog is showing/displayed.
	 * 
	 * @return
	 */
	public boolean isShowing() {
		if (mAlertDialog != null && mAlertDialog.isShowing())
			return true;
		else
			return false;
	}

	/**
	 * On positive button click. Write your own implementation.
	 * 
	 * @param view
	 */
	public abstract void onButtonPositive(View view);

	/**
	 * On negative button click. Write your own implementation.
	 * 
	 * @param view
	 */
	public abstract void onButtonNegative(View view);
}

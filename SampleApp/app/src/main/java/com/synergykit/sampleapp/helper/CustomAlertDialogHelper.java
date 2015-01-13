package com.synergykit.sampleapp.helper;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Static methods working with view and classes extends view. Created by goldj
 * on 14.2.14.
 */
public class CustomAlertDialogHelper {
	/**
	 * Set view visibility visible.
	 * 
	 * @param view
	 */
	public static void setVisibilityVisible(View view) {
		view.setVisibility(View.VISIBLE);
	}

	/**
	 * Set view visibility gone.
	 * 
	 * @param view
	 */
	public static void setVisibilityGone(View view) {
		view.setVisibility(View.GONE);
	}

	/**
	 * Get image view from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static ImageView getImageView(View view, int resourceId) {
		return ((ImageView) view.findViewById(resourceId));
	}

	/**
	 * Get layout view from defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static RelativeLayout getLayout(View view, int resourceId) {
		return ((RelativeLayout) view.findViewById(resourceId));
	}

	/**
	 * Get text view from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static View getView(View view, int resourceId) {
		return ((View) view.findViewById(resourceId));
	}

	/**
	 * Get text view from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static TextView getTextView(View view, int resourceId) {
		return ((TextView) view.findViewById(resourceId));
	}

	/**
	 * Get button from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static Button getButton(View view, int resourceId) {
		return ((Button) view.findViewById(resourceId));
	}

	/**
	 * Get relative layout from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static RelativeLayout getRelativeLayout(View view, int resourceId) {
		return ((RelativeLayout) view.findViewById(resourceId));
	}

	/**
	 * Get progress bar from view for defined resource id.
	 * 
	 * @param resourceId
	 * @return
	 */
	public static ProgressBar getProgressBar(View view, int resourceId) {
		return ((ProgressBar) view.findViewById(resourceId));
	}
}

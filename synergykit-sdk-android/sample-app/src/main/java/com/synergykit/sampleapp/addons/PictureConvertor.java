package com.synergykit.sampleapp.addons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import java.io.ByteArrayOutputStream;

public class PictureConvertor {


    public static byte[] convertBitmapToByteArray(Context context, Bitmap bitmap) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(CompressFormat.JPEG, 70, buffer);
        return buffer.toByteArray();
    }
}

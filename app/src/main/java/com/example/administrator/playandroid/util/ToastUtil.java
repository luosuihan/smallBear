package com.example.administrator.playandroid.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	private static Toast toast;

	public static void showShortToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();
	}

	public static void showLongToast(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		}
		toast.setText(text);
		toast.show();
	}
}

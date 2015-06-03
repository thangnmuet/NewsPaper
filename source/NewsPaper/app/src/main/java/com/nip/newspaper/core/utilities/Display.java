package com.nip.newspaper.core.utilities;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by nguyenminhthang on 6/2/15.
 */
public class Display {

    public static Point getSizeDevice(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        android.view.Display display = wm.getDefaultDisplay();
        Point size = new Point();

        display.getSize(size);
        Log.i("Point", size.x + "");

        return size;

    }
}

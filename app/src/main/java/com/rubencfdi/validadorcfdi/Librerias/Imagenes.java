package com.rubencfdi.validadorcfdi.Librerias;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Ruben on 30/07/2017
 */

public class Imagenes {

    public static Bitmap viewToBitmap(View view) {
            Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(returnedBitmap);
            Drawable drawable =view.getBackground();

            if (drawable != null)
                drawable.draw(canvas);
            else
                canvas.drawColor(Color.WHITE);

            view.draw(canvas);

            return returnedBitmap;
    }
}

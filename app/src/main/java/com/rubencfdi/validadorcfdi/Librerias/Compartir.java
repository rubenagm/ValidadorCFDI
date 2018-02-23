package com.rubencfdi.validadorcfdi.Librerias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

/**
 * Created by Ruben on 30/07/2017
 */

public class Compartir {

    public static void compartirView (Context context, View view) {
        Bitmap bitmap = Imagenes.viewToBitmap(view);

        final Intent intent = new Intent(Intent.ACTION_SEND);
        String URI = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Image", null);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(URI));
        intent.setType("image/png");
        context.startActivity(Intent.createChooser(intent , "Share"));
    }
}

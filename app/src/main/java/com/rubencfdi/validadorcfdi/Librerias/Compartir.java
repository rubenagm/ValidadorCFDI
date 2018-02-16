package com.rubencfdi.validadorcfdi.Librerias;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.View;

import com.rubencfdi.validadorcfdi.BuildConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ruben on 30/07/2017
 */

public class Compartir {

    public static void compartirView (Context context, View view) {
        Bitmap bitmap = Imagenes.viewToBitmap(view);
        File file = new File(context.getCacheDir(), "temporal.png");
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

        try {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        final Intent intent = new Intent(Intent.ACTION_SEND);

        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, file));
            intent.setType("image/png");
            PackageManager packageManager = context.getPackageManager();
            if (intent.resolveActivity(packageManager) != null) {
                context.startActivity(intent);
            }
        }
        else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            context.startActivity(intent);
        }


    }
}

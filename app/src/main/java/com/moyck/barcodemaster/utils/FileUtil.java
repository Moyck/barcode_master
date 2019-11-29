package com.moyck.barcodemaster.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * @author Moyuk
 * @package:com.moyck.barcodemaster.utils
 * @date on 2019/11/28 13:37
 */
public class FileUtil {


    public static void save2Album(Bitmap bitmap) throws IOException {
        File appDir = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES), System.currentTimeMillis() + ".png");
        FileOutputStream fos = new FileOutputStream(appDir);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
    }

}

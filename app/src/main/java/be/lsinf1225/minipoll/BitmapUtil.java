package be.lsinf1225.minipoll;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/*
 * code found on https://stackoverflow.com/questions/11790104/how-to-storebitmap-image-and-retrieve-image-from-sqlite-database-in-android
*/

public class BitmapUtil {

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        } else {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }
    }

    // convert from byte array to bitmap
    public static Bitmap getBitmap(byte[] image) {
        if (image == null) {
            return null;
        } else {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
    }
}

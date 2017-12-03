package wilderpereira.com.dreambox.extension

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.util.Base64


/**
 * Created by Wilder on 03/12/17.
 */
fun decodeBase64(input: String): Bitmap {
    val decodedByte = Base64.decode(input, 0)
    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
}
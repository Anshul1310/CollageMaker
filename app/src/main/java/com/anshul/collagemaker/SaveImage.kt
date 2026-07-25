package com.anshul.collagemaker

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import java.util.UUID




//fun saveBitmapToGallery(context: Context, bitmap: Bitmap): Uri {
//    val filename = "Collage_${System.currentTimeMillis()}.png"
//    var fos: OutputStream? = null
//
//    try {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            // Android 10 and above
//            context.contentResolver?.also { resolver ->
//                val contentValues = ContentValues().apply {
//                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
//                    put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
//                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
//                }
//                val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//                fos = imageUri?.let { resolver.openOutputStream(it) }
//            }
//        } else {
//            // Android 9 and below
//            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//            val image = File(imagesDir, filename)
//            fos = FileOutputStream(image)
//        }
//
//        fos?.use {
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
//            Toast.makeText(context, "Saved to Gallery!", Toast.LENGTH_SHORT).show()
//        }
//    } catch (e: Exception) {
//        e.printStackTrace()
//        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
//    }
//}
suspend fun saveBitmapToGallery(
        context: Context,
        bitmap: Bitmap
    ): Uri? {

        val filename =
            "Collage_${System.currentTimeMillis()}.jpg"

        val values = ContentValues().apply {

            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                filename
            )

            put(
                MediaStore.Images.Media.MIME_TYPE,
                "image/jpeg"
            )

            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES
            )
        }

        val uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        uri?.let {

            val outputStream =
                context.contentResolver.openOutputStream(it)

            outputStream?.use { stream ->

                bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    100,
                    stream
                )
            }
        }
    withContext(Dispatchers.Main) {

        Toast.makeText(
            context,
            "Image Saved",
            Toast.LENGTH_SHORT
        ).show()
    }
        return uri
    }

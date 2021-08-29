package com.swayam.restaurant.assignment.utils.common

import android.content.Context
import java.io.IOException
import java.io.InputStream

object Utils {
    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        val jsonString: String = try {
            val `is`: InputStream = context.getAssets().open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}
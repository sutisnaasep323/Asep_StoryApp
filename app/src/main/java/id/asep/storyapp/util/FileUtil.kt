package id.asep.storyapp.util

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

@Throws(IOException::class)
fun getFile(context: Context, uri: Uri?): File? {
    val destinationFilename =
        File(context.filesDir.path + File.separatorChar + getFileName(context, uri!!))
    try {
        context.contentResolver.openInputStream(uri).use { ins ->
            createFileFromStream(
                ins!!,
                destinationFilename
            )
        }
    } catch (ex: Exception) {
        Log.e("Save File", ex.message!!)
        ex.printStackTrace()
        return null
    }
    return destinationFilename
}

fun createFileFromStream(ins: InputStream, destination: File?) {
    try {
        FileOutputStream(destination).use { os ->
            val buffer = ByteArray(4096)
            var length: Int
            while (ins.read(buffer).also { length = it } > 0) {
                os.write(buffer, 0, length)
            }
            os.flush()
        }
    } catch (ex: Exception) {
        Log.e("Save File", ex.message!!)
        ex.printStackTrace()
    }
}

fun getFileName(context: Context, uri: Uri): String? {
    val returnCursor = context.contentResolver.query(uri, null, null, null, null)

    if (returnCursor != null && returnCursor.moveToFirst()) {
        returnCursor.moveToFirst()
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        return returnCursor.getString(nameIndex)
    }
    returnCursor?.close()
    return null
}


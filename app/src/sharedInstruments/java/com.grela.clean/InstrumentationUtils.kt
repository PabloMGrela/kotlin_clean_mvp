package com.grela.clean

import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import java.io.FileOutputStream

object InstrumentationUtils {
    fun givenAMockFile(fileName: String): String {
        val testContext = InstrumentationRegistry.getInstrumentation().context
        val testInput = testContext.assets.open(fileName)

        val cacheFile = File("${InstrumentationRegistry.getInstrumentation().targetContext.externalCacheDir?.toString()}/MockLocalFile.html")
        val outputStream = FileOutputStream(cacheFile)
        testInput.use { inputFile ->
            outputStream.use { output ->
                inputFile.copyTo(output)
            }
        }
        return "file://${cacheFile.absolutePath}"
    }
}
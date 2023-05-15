package com.example.gitaarstemmer

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.EOFException
import java.io.FileNotFoundException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileRepository(val context: Context) {

    private val defaultList = listOf(Stemming("Standaard", false, arrayOf(Noot.E, Noot.A, Noot.D, Noot.G, Noot.B, Noot.E)))


    fun load(): List<Stemming> {
        try {
            val openFileInput = context.openFileInput(context.getString(R.string.bestandsnaam)) ?: return defaultList
            ObjectInputStream(openFileInput).use {
                return Json.decodeFromString<List<Stemming>>(it.readObject() as String)
            }
        } catch (fileNotFound: FileNotFoundException) {
            // no file yet, revert to defaults.
        } catch (prematureEndOfFile: EOFException) {
            // also ignore this: file incomplete/corrupt, revert to defaults.
        }
        return defaultList
    }

    fun save(items: List<Stemming>) {
        val openFileOutput = context.openFileOutput(context.getString(R.string.bestandsnaam), Context.MODE_PRIVATE) ?: return
        ObjectOutputStream(openFileOutput).use {
            it.writeObject(Json.encodeToString(items))
        }
    }
}
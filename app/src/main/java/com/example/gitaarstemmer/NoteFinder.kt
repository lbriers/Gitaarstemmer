package com.example.gitaarstemmer

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import kotlin.math.absoluteValue
import kotlin.math.pow

class NoteFinder {
    val sampleRate = 22050
    val channelConfig = AudioFormat.CHANNEL_IN_MONO
    val audioFormat = AudioFormat.ENCODING_PCM_8BIT
    val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
    val threshold = 0.03
    var buffer = ByteArray(bufferSize)
    val noten = arrayOf(110,117,123,131,139,147,156,165,175,185,196,208)
    //A, A# , B, C , C#, D , D#, E , F , F#, G , G#

    @SuppressLint("MissingPermission")
    val audioRecord = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        sampleRate,
        channelConfig,
        audioFormat,
        bufferSize
    )

    val recordingThread = Thread {
        while (true) {
            // Code to run continuously in the background thread
            audioRecord.read(buffer, 0, bufferSize)
            // Add a delay if needed
        }
    }

    init{
        audioRecord.startRecording()
        recordingThread.start()
    }

    fun getNote() : IntArray {
        val frequency = getFreq()
        var lowestValue = 1000000
        var bestMatch = 0
        for(note in noten){
            var temp = (note - frequency).absoluteValue
            if(temp < lowestValue){
                lowestValue = temp
                bestMatch = note
            }
        }
        val tempArray = intArrayOf(noten.indexOf(bestMatch), frequency - bestMatch)
        return tempArray
    }


    fun getFreq(): Int {
        //bereken de noot
        var i = 105
        var lowestValue = 1.0
        var bestMatch = -1
        while(i < 210){
            var note = checkfreq(buffer, i)
            if(note < lowestValue){
                lowestValue = note
                bestMatch = i
            }
            i++
        }
        return bestMatch
    }

    fun checkfreq(buffer : ByteArray, frequency : Int) : Double{
        val note = notePower(buffer, frequency).toDouble()
        val audio = audioPower(buffer, frequency).toDouble()
        return note / audio
    }

    fun audioPower(buffer: ByteArray, frequency: Int): Int {
        val period = 1/frequency.toFloat()
        val samplePeriod = 1/sampleRate.toFloat()
        val periodSamples= (period/ samplePeriod).toInt()
        var sum = 0.0
        var i = 0
        while(i < buffer.size - periodSamples){
            sum += buffer[i].toFloat().pow(2) + buffer[i+periodSamples].toFloat().pow(2)
            i++
        }
        val result = (sum / (2*(buffer.size - periodSamples))).toInt()
        return  result//MISHCIEN AANPASSEN
    }

    fun notePower(buffer : ByteArray, frequency: Int) : Int {
        val period = 1/frequency.toFloat()
        val samplePeriod = 1/sampleRate.toFloat()
        val periodSamples= (period/ samplePeriod).toInt()
        var sum = 0.0
        var i = 0
        while(i < buffer.size - periodSamples){
            sum += (buffer[i].toFloat() - buffer[i+periodSamples].toFloat()).pow(2) //x² x2²
            i++
        }
        val result = (sum / (4*(buffer.size - periodSamples))).toInt()
        return  result//MISHCIEN AANPASSEN
    }

}


package com.example.texttospeechapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var ttsfield: EditText
    lateinit var ttsbutton: Button
    lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ttsfield=findViewById(R.id.editText)
        ttsbutton=findViewById(R.id.button)
       // ttsbutton.isEnabled=false;
        textToSpeech= TextToSpeech(this,this)
        ttsbutton.setOnClickListener{
            converttexttospeech()
        }

    }
    private fun   converttexttospeech(){
        var text=ttsfield.text.toString()
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")


    }
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val res=textToSpeech.setLanguage(Locale.ENGLISH)
            if(res==TextToSpeech.LANG_NOT_SUPPORTED||res==TextToSpeech.LANG_MISSING_DATA)
            {
                Log.e("TTS","language not supported!")
            }
            else{
                ttsbutton.isEnabled=true;
            }
        }
            else{
                Log.e("TTS","TTS Failed")
            }
    }

    override fun onDestroy() {
        if(textToSpeech!=null)
        {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
        }


package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var number:String=""
        setContentView(R.layout.activity_main)
        if(intent.action== Intent.ACTION_PROCESS_TEXT)
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        if(number.isDigitsOnly()){
startWhatsapp(number)
        }
        else{
            Toast.makeText(this,"Please Check Your NUmber",Toast.LENGTH_LONG).show();
        }

    }

    private fun startWhatsapp( s:String) {
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
var data:String="0"
        when {
            s[0]=='+' -> {
                data=s.substring(1)
            }
            s.length==10 -> data= "91$s"
            else -> {
               data= s
            }
        }
intent.data= Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(intent,0)!=null)
            startActivity(intent)
        else
            Toast.makeText(this,"please Install Whatsapp",Toast.LENGTH_LONG).show()
finish()
    }

}
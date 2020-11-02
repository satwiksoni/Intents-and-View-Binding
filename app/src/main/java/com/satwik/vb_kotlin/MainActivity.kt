package com.satwik.vb_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast.makeText as makeText1
const val KEY="Text"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title ="Sample"

        et1.apply {
            hint ="Enter Your Name"
            addTextChangedListener {
                Log.i("TAG", it.toString())
                bt1.isVisible = it.toString().length>5

            }
        }
        // For buttons
        bt1.setOnClickListener(object :View.OnClickListener
        {

            override fun onClick(v: View) {
                Toast.makeText(v.context,"Button has been clicked",Toast.LENGTH_LONG).show()
                tv1.isVisible=true
                tv1.setText(et1.text.toString())
            }

        })

        // for switch
        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            val message=if(isChecked)"Clicked ON" else "Clicked OFF"
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        }


        // for radio
        RadioGroup1.setOnCheckedChangeListener { group, checkedId ->
            val text:String=et1.text.toString()
            when(checkedId)
            {
                R.id.radioButton->{
                    Toast.makeText(this,"1st option has been choosed",Toast.LENGTH_LONG).show()
                    val intent=Intent(this,MainActivity2:: class.java)
                    intent.putExtra("KEY",text)
                    startActivity(intent)
                }
                R.id.radioButton2->{
                    Toast.makeText(this,"2nd option has been choosed",Toast.LENGTH_LONG).show()
                    val intent=Intent()
                    intent.action=Intent.ACTION_SENDTO
                    intent.data=Uri.parse("mailto:$text")
                    startActivity(intent)
                }
                R.id.radioButton3->{
                    val intent=Intent()
                    intent.action=Intent.ACTION_VIEW
                    intent.data= Uri.parse(text)
                    startActivity(intent)
                }
                R.id.radioButton4 -> {
                    val intent=Intent()
                    intent.action=Intent.ACTION_DIAL
                    intent.data= Uri.parse("tel:$text")
                    startActivity(intent)
                }
                R.id.radioButton5->{
                    val downloadIntent = Intent(this, DOWNLOAD_SERVICE::class.java).apply {
                        data = Uri.parse("http://142.44.214.148/series/gameof/Game.of.Thrones.S08.720p.HBO.WEB-DL.DD2.0.H.264-PiA/Game.of.Thrones.S08E01.720p.HBO.WEB-DL.DD2.0.H.264-PiA.mkv")
                    }
                    startService(downloadIntent)

                }
            }
        }
        ///Using intent




        }
    }
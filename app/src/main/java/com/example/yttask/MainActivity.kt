package com.example.yttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.ImageView
import androidx.core.view.get
import org.w3c.dom.Text
import java.text.FieldPosition
import java.util.ArrayList
import java.util.*
import kotlin.collections.HashMap
import android.widget.AdapterView.OnItemSelectedListener as OnItemSelectedListener1


class MainActivity : AppCompatActivity() {
    private var txt: TextView? = null
    private var spin: Spinner? = null
    private var image: ImageView? = null
    private var pButton:Button?=null
    private var mButton:Button?=null
    private var pricetext:TextView?=null
    private var instruments = HashMap<String, Int>()

    var a = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instruments.put("Guitar", 1000)
        instruments.put("Drum", 500)
        instruments.put("Piano", 1500)
        image=findViewById(R.id.goodsImage)
        pricetext=findViewById(R.id.priceValue)
        var price: Int
        var goodsName: String
        goodsName = spin?.getSelectedItem().toString()
        val mutab = arrayOf("Guitar", "Drum","Piano")
        mButton=findViewById(R.id.minusButton)
        pButton=findViewById(R.id.plusButton)
        spin = findViewById(R.id.spinner)
        var p=0
        val massiv = ArrayAdapter(this, android.R.layout.simple_spinner_item, mutab)
        massiv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin?.setAdapter(massiv)
        spin?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
//
//                price=instruments.get(goodsName)!!
               // pricetext?.setText(""+a*price)
                goodsName = spin?.getSelectedItem().toString()
                if(goodsName.equals("Drum")){
                    a=0
                    p=0
                    image?.setImageResource(R.drawable.drum)
                    pricetext?.setText((0).toString())

                    txt?.setText((0).toString())
                    pButton?.setOnClickListener(){
                        p+=500
                        pricetext?.setText((p).toString())
                        a+=1
                        txt = findViewById(R.id.quantityValue)
                        txt?.setText((a).toString())
                    }
                    mButton?.setOnClickListener(){

                        if (a <= 0) a==0
                        else {
                            a -=1
                            p -= 500
                            txt?.setText((a).toString())
                            pricetext?.setText((p).toString())
                        }
                    }
                }else if (goodsName.equals("Guitar")){
                    a=0
                    p=0
                    image?.setImageResource(R.drawable.guitar)
                    pricetext?.setText((0).toString())

                    txt?.setText((0).toString())
                    pButton?.setOnClickListener(){
                        p+=1000
                        pricetext?.setText((p).toString())
                        a+=1
                        txt = findViewById(R.id.quantityValue)
                        txt?.setText((a).toString())
                    }
                    mButton?.setOnClickListener(){
                        if (a <= 0) a==0
                        else {
                            a -=1
                            p -= 1000
                            txt?.setText((a).toString())
                            pricetext?.setText((p).toString())
                        }
                    }
                }else{
                    image?.setImageResource(R.drawable.piano)
                    a=0
                    p=0
                    pricetext?.setText((0).toString())
                    txt?.setText((0).toString())

                    pButton?.setOnClickListener(){
                        p+=1500
                        pricetext?.setText((p).toString())
                        a+=1
                        txt = findViewById(R.id.quantityValue)
                        txt?.setText((a).toString())
                    }
                    mButton?.setOnClickListener(){

                        if (a <= 0) a==0
                        else {
                            a -=1
                            p -= 1500
                            txt?.setText((a).toString())
                            pricetext?.setText((p).toString())
                        }
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Nothing implemented yet")
            }
        }

        }
    }





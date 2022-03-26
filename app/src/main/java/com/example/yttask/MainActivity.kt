package com.example.yttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private var pButton: Button? = null
    private var mButton: Button? = null
    private var pricetext: TextView? = null
    private var nametext: EditText? = null
    private var buttonAdd: Button? = null
    private var buttonOrder: Button? = null
    private var posterImage: ImageView? = null
    private var instruments = HashMap<String, Int>()
    private val goodsList = arrayListOf<Goods>()
    private var clearButton : Button?=null

    var goodsName: String = ""
    var a = 0
    var order = Order()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOrder = findViewById(R.id.button5)
        posterImage = findViewById(R.id.imageView)
        buttonAdd = findViewById(R.id.button2)
        mButton = findViewById(R.id.minusButton)
        pButton = findViewById(R.id.plusButton)
        spin = findViewById(R.id.spinner)
        image = findViewById(R.id.goodsImage)
        pricetext = findViewById(R.id.priceValue)
        nametext = findViewById(R.id.nametxt)
        txt = findViewById(R.id.quantityValue)
        clearButton=findViewById(R.id.clear)

        instruments["Guitar"] = 1000
        instruments["Drum"] = 500
        instruments["Piano"] = 1500

        val mutable1 = arrayOf("Guitar", "Drum", "Piano")
        var p = 0
        val array1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, mutable1)

        goodsName = spin?.selectedItem.toString()

        array1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin?.adapter = array1

        spin?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                goodsName = spin?.getSelectedItem().toString()
                when (goodsName) {
                    "Drum" -> {
                        a = 0
                        p = 0

                        image?.setImageResource(R.drawable.drum)
                        pricetext?.text = (0).toString()

                        txt?.text = (0).toString()
                        pButton?.setOnClickListener() {
                            p += 500
                            pricetext?.text = (p).toString()
                            a += 1
                            txt?.text = (a).toString()
                        }
                        mButton?.setOnClickListener() {

                            if (a <= 0) a == 0
                            else {
                                a -= 1
                                p -= 500
                                txt?.text = (a).toString()
                                pricetext?.text = (p).toString()
                            }
                        }
                    }
                    "Guitar" -> {
                        a = 0
                        p = 0
                        image?.setImageResource(R.drawable.guitar)
                        pricetext?.text = (0).toString()

                        txt?.text = (0).toString()
                        pButton?.setOnClickListener() {
                            p += 1000
                            pricetext?.text = (p).toString()
                            a += 1
                            txt?.text = (a).toString()
                        }
                        mButton?.setOnClickListener() {
                            if (a <= 0) a == 0
                            else {
                                a -= 1
                                p -= 1000
                                txt?.text = (a).toString()
                                pricetext?.text = (p).toString()
                            }
                        }
                    }
                    else -> {
                        image?.setImageResource(R.drawable.piano)
                        a = 0
                        p = 0
                        pricetext?.text = (0).toString()
                        txt?.text = (0).toString()

                        pButton?.setOnClickListener() {
                            p += 1500
                            pricetext?.text = (p).toString()
                            a += 1
                            txt?.text = (a).toString()
                        }
                        mButton?.setOnClickListener() {

                            if (a <= 0) a == 0
                            else {
                                a -= 1
                                p -= 1500
                                txt?.text = (a).toString()
                                pricetext?.text = (p).toString()
                            }
                        }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Nothing implemented yet")
            }
        }

        clearButton?.setOnClickListener(){
            goodsList[0].count=0
            goodsList[1].count=0
            goodsList[2].count=0
        }

        buttonOrder?.setOnClickListener() {
            order.userName = (nametext?.getText()).toString()
            order.goods = goodsName
            order.quantity = a

            var intent = Intent(this, OrderActivity::class.java).also {
                it.putExtra("userName", order.userName)
                it.putExtra("GuitarCount", goodsList[0].count)
                it.putExtra("DrumCount", goodsList[1].count)
                it.putExtra("PianoCount", goodsList[2].count)
                startActivity(it)
            }
        }

        setGoods()
        setOnClick()
    }

    private fun setGoods() {
        val guitar = Goods("Guitar", 0, 1000)
        val drum = Goods("Drum", 0, 500)
        val piano = Goods("Piano", 0, 1500)
        goodsList.add(guitar) //0
        goodsList.add(drum)  //1
        goodsList.add(piano)  //2
    }

    private fun setOnClick() {
        buttonAdd?.setOnClickListener() {
            when (goodsName) {
                "Drum" -> {
                    goodsList[1].count = txt?.text.toString().toIntOrNull() ?: 0
                }
                "Guitar" -> {
                    goodsList[0].count = txt?.text.toString().toIntOrNull() ?: 0
                }
                "Piano" -> {
                    goodsList[2].count = txt?.text.toString().toIntOrNull() ?: 0
                }
            }
        }

    }
}

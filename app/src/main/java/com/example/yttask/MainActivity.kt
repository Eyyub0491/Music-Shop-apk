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
    private var buttonAdd:Button?=null
    private var instruments = HashMap<String, Int>()

    var a = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonAdd=findViewById(R.id.button5)
        instruments["Guitar"] = 1000
        instruments["Drum"] = 500
        instruments["Piano"] = 1500
        image = findViewById(R.id.goodsImage)
        pricetext = findViewById(R.id.priceValue)
        var price: Int
        var goodsName: String
        goodsName = spin?.selectedItem.toString()
        val mutab = arrayOf("Guitar", "Drum", "Piano")
        mButton = findViewById(R.id.minusButton)
        pButton = findViewById(R.id.plusButton)
        spin = findViewById(R.id.spinner)
        var p = 0
        val massiv = ArrayAdapter(this, android.R.layout.simple_spinner_item, mutab)
        massiv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin?.adapter = massiv
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
                            txt = findViewById(R.id.quantityValue)
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
                            txt = findViewById(R.id.quantityValue)
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
                            txt = findViewById(R.id.quantityValue)
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
        buttonAdd?.setOnClickListener(){
            var order = Order()
            nametext = findViewById(R.id.nametxt)
            price = instruments.get(goodsName)!!
            order.userName = (nametext?.getText()).toString()
            order.goods = goodsName
            order.quantity = a
            order.priceValue = price * a
            var intent=Intent(this,OrderActivity::class.java).also {
                it.putExtra("userName", order.userName)
                it.putExtra("goodsName", order.goods)
                it.putExtra("quantity", order.quantity)
                it.putExtra("price", order.priceValue)
                it.putExtra("itemPrice",price)
                startActivity(it)
            }
        }


    }
}
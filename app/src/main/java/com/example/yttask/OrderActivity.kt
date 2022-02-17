package com.example.yttask

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text


class OrderActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var userName=intent.getStringExtra("userName")
        var goods=intent.getStringExtra("goodsName")
        var quantity=intent.getIntExtra("quantity",0)
        var price=intent.getIntExtra("price",0)
        var orderUserName= findViewById<TextView>(R.id.textViewUser) as TextView
        var itemPrice=intent.getIntExtra("itemPrice",0)
        var emailText=("Customer name: $userName \nGoods name: $goods \nQuantity: $quantity \nPrice: $itemPrice $ \nOrder price: $price $").toString()
        orderUserName?.setText(emailText)

        title = "Your Order"
        var subButton=findViewById<Button>(R.id.submit) as Button
        subButton.setOnClickListener() {

                var emailAdress="oruczade0491@gmail.com"
                var emailSubject="Music Shop Order List"
                val emIntent = Intent(Intent.ACTION_SEND)
                emIntent.data = Uri.parse("mailto:")
                emIntent.type="text/plain"
                emIntent.putExtra(Intent.EXTRA_EMAIL, emailAdress)
                emIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                emIntent.putExtra(Intent.EXTRA_TEXT,emailText)
                startActivity(Intent.createChooser(emIntent,"Сhoose the sending method"))
            }
        }


    }


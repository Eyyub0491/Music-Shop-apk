package com.example.yttask

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text


class OrderActivity : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        var subButton = findViewById<Button>(R.id.submit) as Button
        var orderUserName = findViewById<TextView>(R.id.textViewUser) as TextView
        var userName = intent.getStringExtra("userName")
        var guitarCount = intent.getIntExtra("GuitarCount", 0)
        var drumCount = intent.getIntExtra("DrumCount", 0)
        var pianoCount = intent.getIntExtra("PianoCount", 0)

        val pianoName = "Piano"
        val pianoPrice = 1500
        val pianoTotal = pianoPrice * pianoCount

        val guitarName = "Guitar"
        val guitarPrice = 1000
        val guitarTotal = guitarPrice * guitarCount

        val drumName = "Drum"
        val drumPrice = 500
        val drumTotal = drumPrice * drumCount

        var price = pianoTotal + guitarTotal + drumTotal

        var emailText= ("Customer name: $userName " +
                "\n\nGoods name: $pianoName " +
                "\nQuantity: $pianoCount " +
                "\nPrice: $pianoPrice $ " +
                "\nTotal Price: $pianoTotal " +
                "\n" +
                "\nGoods name: $guitarName " +
                "\nQuantity: $guitarCount " +
                "\nPrice: $guitarPrice $ " +
                "\nTotal Price: $guitarTotal" +
                "\n" +
                "\nGoods name: $drumName " +
                "\nQuantity: $drumCount " +
                "\nPrice: $drumPrice $ " +
                "\nTotal Price: $drumTotal" +
                "\n\n" +
                "Total Order Price: $price")
        orderUserName?.text = emailText
        title = "Your Order"


            subButton.setOnClickListener() {

                var emailAdress = "oruczade0491@gmail.com"
                var emailSubject = "Music Shop Order List"
                val emIntent = Intent(Intent.ACTION_SEND)
                emIntent.data = Uri.parse("mailto:")
                emIntent.type = "text/plain"
                emIntent.putExtra(Intent.EXTRA_EMAIL, emailAdress)
                emIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
                emIntent.putExtra(Intent.EXTRA_TEXT, emailText)
                startActivity(Intent.createChooser(emIntent, "Choose the sending method"))
            }
    }
}
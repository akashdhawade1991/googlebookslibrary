package com.example.googlebookslibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

class BookDetailsActivity : AppCompatActivity() {

    // creating variables for strings,text view,
    // image views and button.
    lateinit var titleTV: TextView
    lateinit var subtitleTV: TextView
    lateinit var publisherTV: TextView
    lateinit var descTV: TextView
    lateinit var pageTV: TextView
    lateinit var publisherDateTV: TextView
    lateinit var backBtn: Button
    lateinit var buyBtn: Button
    lateinit var bookIV: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // initializing our variables.
        titleTV = findViewById(R.id.idTVTitle)
        subtitleTV = findViewById(R.id.idTVSubTitle)
        publisherTV = findViewById(R.id.idTVpublisher)
        descTV = findViewById(R.id.idTVDescription)
        pageTV = findViewById(R.id.idTVNoOfPages)
        publisherDateTV = findViewById(R.id.idTVPublishDate)
        backBtn = findViewById(R.id.idBtnBack)
        buyBtn = findViewById(R.id.idBtnBuy)
        bookIV = findViewById(R.id.idIVbook)

        // getting the data which we have passed from our adapter class.
        val title = getIntent().getStringExtra("title")
        val subtitle = getIntent().getStringExtra("subtitle")
        val publisher = getIntent().getStringExtra("publisher")
        val publishedDate = getIntent().getStringExtra("publishedDate")
        val description = getIntent().getStringExtra("description")
        val pageCount = getIntent().getIntExtra("pageCount", 0)
        val thumbnail = getIntent().getStringExtra("thumbnail")
        val buyLink = getIntent().getStringExtra("buyLink")

        // after getting the data we are setting
        // that data to our text views and image view.
        titleTV.setText(title)
        subtitleTV.setText(subtitle)
        publisherTV.setText(publisher)
        publisherDateTV.setText("Published On : " + publishedDate)
        descTV.setText(description)
        pageTV.setText("No Of Pages : " + pageCount)
        val secureThumbnail = thumbnail?.replace("http://", "https://")
        Picasso.get().load(secureThumbnail).into(bookIV)

        // adding on click listener for our preview button.
        backBtn.setOnClickListener {
            finish()
        }

        // adding click listener for buy button
        buyBtn.setOnClickListener {
            if (buyLink.isNullOrEmpty()) {
                // below toast message is displaying
                // when buy link is empty.
                Toast.makeText(
                    this@BookDetailsActivity,
                    "No buy page present for this book",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // if the link is present we are opening
            // the link via an intent.
            val uri = Uri.parse(buyLink)
            val i = Intent(Intent.ACTION_VIEW, uri)
            startActivity(i)
        }

    }
}
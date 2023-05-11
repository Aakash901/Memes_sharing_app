package com.example.memessharingapp

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var imageUrl: String? = null
    private var imageUrlTwo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shareMeme = findViewById<Button>(R.id.button)
        val nextMeme = findViewById<Button>(R.id.button2)

        val shareMemeTwo = findViewById<Button>(R.id.button_two)
        val nextMemeTwo = findViewById<Button>(R.id.button2_two)

        loadMeme()
        loadMemeTwo()
    }

    private fun loadMemeTwo() {

        val imageview_two = findViewById<ImageView>(R.id.imageView_two)
        val progressBar_two = findViewById<ProgressBar>(R.id.progressbar_two)
        progressBar_two.visibility = View.VISIBLE

        val queue = Volley.newRequestQueue(this)

        imageUrlTwo = "https://meme-api.com/gimme"

        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, imageUrlTwo, null, { response ->
                imageUrlTwo = response.getString("url")
                Glide.with(this).load(imageUrlTwo).listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar_two.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar_two.visibility = View.GONE
                        return false

                    }

                }).into(imageview_two)
            }, {

            })

        queue.add(jsonObjectRequest)
    }

    private fun loadMeme() {


        val imageview = findViewById<ImageView>(R.id.imageView)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        progressBar.visibility = View.VISIBLE

        val queue = Volley.newRequestQueue(this)

        imageUrl = "https://meme-api.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, imageUrl, null, { response ->
            imageUrl = response.getString("url")
            Glide.with(this).load(imageUrl).listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false

                }

            }).into(imageview)
        }, {

        })

        queue.add(jsonObjectRequest)
    }

    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "hey ,Enjoy this amazing memes  $imageUrl")
        val chooser = Intent.createChooser(intent, "Share....")
        startActivity(chooser)


    }

    fun shareMemeTwo(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "hey ,Enjoy this amazing memes  $imageUrlTwo")
        val chooser = Intent.createChooser(intent, "Share with your friends....")
        startActivity(chooser)


    }

    fun nextMeme(view: View) {
        loadMeme()
    }

    fun nextMemeTwo(view: View) {
        loadMemeTwo()
    }
}
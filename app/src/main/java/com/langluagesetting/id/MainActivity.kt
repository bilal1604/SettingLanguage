package com.langluagesetting.id
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {

    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        // Inisialisasi AdView
        adView = findViewById(R.id.adView)

        // Buat permintaan iklan
        val adRequest = AdRequest.Builder().build()

        // Muat iklan ke dalam AdView
        adView.loadAd(adRequest)

        val button_start = findViewById<Button>(R.id.startbutton)
        button_start.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }

        val button_wa = findViewById<ImageView>(R.id.whatshap)
        button_wa.setOnClickListener {
            openWhatsApp()
        }

        val button_ig = findViewById<ImageView>(R.id.ig)
        button_ig.setOnClickListener {
            openInstagram()
        }

        val button_tw = findViewById<ImageView>(R.id.twiter)
        button_tw.setOnClickListener {
            openTwitter()
        }

        val button_yt = findViewById<ImageView>(R.id.yt)
        button_yt.setOnClickListener {
            openYouTube()
        }
    }

    override fun onDestroy() {
        // Hentikan pemuatan iklan saat activity dihancurkan
        adView.destroy()
        super.onDestroy()
    }

    private fun openWhatsApp() {
        val packageName = "com.whatsapp"
        val packageManager = packageManager

        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(intent)
        } else {
            val errorMessage = "Whatshapp app is not installed on your device."
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()

            val playStoreLink = "https://play.google.com/store/apps/details?id=com.whatsapp"
            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse(playStoreLink))
            startActivity(playStoreIntent)
        }
    }

    private fun openYouTube() {
        val packageName = "com.google.android.youtube"
        val packageManager = packageManager

        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(intent)
        } else {
            // Aplikasi YouTube tidak terpasang, buka tautan Play Store untuk mengunduh.
            val errorMessage = "Youtube app is not installed on your device."
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()

            val playStoreLink = "https://play.google.com/store/apps/details?id=com.google.android.youtube"
            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse(playStoreLink))
            startActivity(playStoreIntent)
        }
    }


    private fun openInstagram() {
        val packageName = "com.instagram.android"
        val packageManager = packageManager

        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(intent)
        } else {
            // Instagram tidak terpasang, Anda bisa memberikan pilihan lain atau pesan error.
            val errorMessage = "Instagram app is not installed on your device."
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()

            val playStoreLink = "https://play.google.com/store/apps/details?id=com.instagram.android"
            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse(playStoreLink))
            startActivity(playStoreIntent)
        }
    }

    private fun openTwitter() {
        val packageName = "com.twitter.android" // Package name untuk aplikasi Twitter
        val packageManager = packageManager

        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(intent)
        } else {
            // Aplikasi Twitter tidak terpasang, Anda bisa memberikan pilihan lain atau pesan error.
            val errorMessage = "Twitter app is not installed on your device."
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()

            val playStoreLink = "https://play.google.com/store/apps/details?id=com.twitter.android"
            val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse(playStoreLink))
            startActivity(playStoreIntent)
        }
    }
}

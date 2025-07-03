package com.langluagesetting.id

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.net.toUri
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private lateinit var adView: AdView
    private lateinit var adView1: AdView
    private lateinit var adView2: AdView
    private var interstitialAd: InterstitialAd? = null
    private lateinit var adRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        // Inisialisasi iklan
        MobileAds.initialize(this)

        // Siapkan permintaan iklan
        adRequest = AdRequest.Builder().build()

        // Load iklan interstitial
        loadInterstitialAd()

        // Inisialisasi dan load banner
        adView = findViewById(R.id.adView)
        adView1 = findViewById(R.id.adView1)
        adView2 = findViewById(R.id.adView2)

        adView.loadAd(adRequest)
        adView1.loadAd(adRequest)
        adView2.loadAd(adRequest)

        // Tombol start
        val buttonStart = findViewById<Button>(R.id.startbutton)
        buttonStart.setOnClickListener {
            if (interstitialAd != null) {
                interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                        interstitialAd = null
                        loadInterstitialAd() // reload setelah ditutup
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                        interstitialAd = null
                        loadInterstitialAd() // reload walau gagal
                    }
                }
                interstitialAd?.show(this)
            } else {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                loadInterstitialAd() // reload jika belum tersedia
            }
        }

            }

    private fun loadInterstitialAd() {
        InterstitialAd.load(
            this,
            "ca-app-pub-8604728728100888/1036434163", // ganti dengan ID asli jika di produksi
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                }
            }
        )
    }

    override fun onDestroy() {
        adView.destroy()
        adView1.destroy()
        adView2.destroy()
        super.onDestroy()
    }

    private fun openAppOrStore(packageName: String, storeUrl: String) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Aplikasi tidak ditemukan.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Intent.ACTION_VIEW, storeUrl.toUri()))
        }
    }
}

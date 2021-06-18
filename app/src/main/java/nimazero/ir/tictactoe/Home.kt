package nimazero.ir.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import ir.tapsell.plus.AdRequestCallback
import ir.tapsell.plus.TapsellPlus
import ir.tapsell.plus.TapsellPlusBannerType
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        analytics =FirebaseAnalytics.getInstance(this)

        TapsellPlus.initialize(
            this,
            "obdogdgjhtncmpcasghoemtrktnnlthkqeolieckfqcanlhelchtemastsntnafhbkhjmr"
        );

        TapsellPlus.showBannerAd(
            this,
            standardBanner,
            "6061f37b260b920001cc781b",
            TapsellPlusBannerType.BANNER_320x50,
            object : AdRequestCallback() {
                override fun response() {}
                override fun error(message: String?) {}
            })


        supportActionBar?.hide()

        bTak.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btn2p.setOnClickListener {
           startActivity(Intent(this, TwoPlayers::class.java))
        }



        btnEX.setOnClickListener {
            finishAffinity()
            finish()
        }

    }
}
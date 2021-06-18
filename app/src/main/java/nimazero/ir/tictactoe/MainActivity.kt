package nimazero.ir.tictactoe

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.YELLOW
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import ir.tapsell.plus.AdRequestCallback
import ir.tapsell.plus.TapsellPlus
import ir.tapsell.plus.TapsellPlusBannerType
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer
import kotlin.math.log
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()


        TapsellPlus.showBannerAd(
            this,
            standardBanner1,
            "6061f37b260b920001cc781b",
            TapsellPlusBannerType.BANNER_320x50,
            object : AdRequestCallback() {
                override fun response() {}
                override fun error(message: String?) {}
            })

        reset.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }


    fun buClick(view: View) {
        val buSelected = view as Button
        var cellId = 0
        when (buSelected.id) {
            R.id.b1 -> cellId = 1
            R.id.b2 -> cellId = 2
            R.id.b3 -> cellId = 3
            R.id.b4 -> cellId = 4
            R.id.b5 -> cellId = 5
            R.id.b6 -> cellId = 6
            R.id.b7 -> cellId = 7
            R.id.b8 -> cellId = 8
            R.id.b9 -> cellId = 9
        }
        //  Log.d("buClick",buSelected.id.toString())
        // Log.d("buClick: cellId", cellId.toString())
        playgame(cellId, buSelected )
    }


    var activeplayer = 1
    var player1 = arrayListOf<Int>()
    var player2 = arrayListOf<Int>()
    val a = 5
    @SuppressLint("ResourceAsColor")
    fun playgame(cellId: Int, buSelected: Button) {
        if (activeplayer == 1) {
            winner.text = "نوبت بازیکن ۲"
            buSelected.text = "X"
            player1.add(cellId)
            activeplayer = 2
            buSelected.setBackgroundColor(Color.RED)
            if (player1.size < a){
                autoplay()
            }
        } else {
            winner.text = "نوبت بازیکن ۱"
            buSelected.text = "O"
            player2.add(cellId)
            activeplayer = 1
            buSelected.setBackgroundColor(Color.BLUE)
        }
        buSelected.isEnabled = false
        win()

    }

    fun win(){
        // by Winer i mean win not the winner!
        var Winer = 0

        if (b1.text == "X" && b2.text == "X" && b3.text == "X"){Winer = 1}
        if (b4.text == "X" && b5.text == "X" && b6.text == "X"){Winer = 1}
        if (b7.text == "X" && b8.text == "X" && b9.text == "X"){Winer = 1}

        if (b1.text == "X" && b4.text == "X" && b7.text == "X"){Winer = 1}
        if (b2.text == "X" && b5.text == "X" && b8.text == "X"){Winer = 1}
        if (b3.text == "X" && b6.text == "X" && b9.text == "X"){Winer = 1}

        if (b1.text == "X" && b5.text == "X" && b9.text == "X"){Winer = 1}
        if (b3.text == "X" && b5.text == "X" && b7.text == "X"){Winer = 1}



        if (b1.text == "O" && b2.text == "O" && b3.text == "O"){Winer = 2}
        if (b4.text == "O" && b5.text == "O" && b6.text == "O"){Winer = 2}
        if (b7.text == "O" && b8.text == "O" && b9.text == "O"){Winer = 2}

        if (b1.text == "O" && b4.text == "O" && b7.text == "O"){Winer = 2}
        if (b2.text == "O" && b5.text == "O" && b8.text == "O"){Winer = 2}
        if (b3.text == "O" && b6.text == "O" && b9.text == "O"){Winer = 2}

        if (b1.text == "O" && b5.text == "O" && b9.text == "O"){Winer = 2}
        if (b3.text == "O" && b5.text == "O" && b7.text == "O"){Winer = 2}

        if (Winer == 0 && player1.size + player2.size == 9) {
            winner.text = "مساوی"
        }
            if (Winer == 1){
                winner.text = "بازیکن ۱ برنده شد"
                winner.setTextColor(Color.YELLOW)
            }
        if (Winer == 2){
            winner.text = "بازیکن ۲ برنده شد"
            winner.setTextColor(Color.YELLOW)
        }

        }

    fun autoplay(){
        var emptyCells = ArrayList<Int>()
        for(cellId in 1..9){
          if (!(player1.contains(cellId) || player2.contains(cellId))){
              emptyCells.add(cellId)
          }
        }

        val r = Random
        val randIndex = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randIndex]
        var buSelected:Button?
        buSelected = when(cellId){
            1 -> b1
            2 -> b2
            3 -> b3
            4 -> b4
            5 -> b5
            6 -> b6
            7 -> b7
            8 -> b8
            9 -> b9
            else -> {
                b1
            }
        }
        playgame(cellId, buSelected)

    }

    }


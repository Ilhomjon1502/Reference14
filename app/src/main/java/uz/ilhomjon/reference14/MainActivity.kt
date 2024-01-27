package uz.ilhomjon.reference14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.ilhomjon.reference14.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnSotuvchi.setOnClickListener {
                startActivity(Intent(this@MainActivity, SotuvchiActivity::class.java))
            }
            btnXaridor.setOnClickListener {
                startActivity(Intent(this@MainActivity, XaridorActivity::class.java))
            }

            btnBuyurtma.setOnClickListener {
                startActivity(Intent(this@MainActivity, BuyurtmaActivity::class.java))
            }
        }
    }
}
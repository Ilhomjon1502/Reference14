package uz.ilhomjon.reference14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import uz.ilhomjon.reference14.adapter.BuyurtmaAdapter
import uz.ilhomjon.reference14.databinding.ActivityBuyurtmaBinding
import uz.ilhomjon.reference14.db.MyDbHelper
import uz.ilhomjon.reference14.models.Buyurtma

class BuyurtmaActivity : AppCompatActivity() {
    private val binding by lazy { ActivityBuyurtmaBinding.inflate(layoutInflater) }
    lateinit var myDbHelper: MyDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        val sotuvchiList = myDbHelper.getAllSotuvchi()
        val xaridorList = myDbHelper.getAllXaridor()

        val snl = ArrayList<String>()
        sotuvchiList.forEach { snl.add(it.name!!) }
        val sa = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, snl)
        binding.spinnerSotuvchi.adapter = sa

        val xnl = ArrayList<String>()
        xaridorList.forEach { xnl.add(it.name!!) }
        val xa = ArrayAdapter(this, android.R.layout.simple_list_item_1, xnl)
        binding.spinnerXaridor.adapter = xa

        binding.apply {
            btnSave.setOnClickListener {
                val buyurtma = Buyurtma(
                    edtName.text.toString(),
                    sotuvchiList[spinnerSotuvchi.selectedItemPosition],
                    xaridorList[spinnerXaridor.selectedItemPosition]
                )
                myDbHelper.addBuyurtma(buyurtma)
                onResume()
                Toast.makeText(this@BuyurtmaActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.rv.adapter = BuyurtmaAdapter(myDbHelper.getAllBuyurtma())
    }
}
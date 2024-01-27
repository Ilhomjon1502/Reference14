package uz.ilhomjon.reference14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.ilhomjon.reference14.adapter.SotuvchiXaridorAdapter
import uz.ilhomjon.reference14.databinding.ActivitySotuvchiXaridorBinding
import uz.ilhomjon.reference14.db.MyDbHelper
import uz.ilhomjon.reference14.models.Sotuvchi

class SotuvchiActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySotuvchiXaridorBinding.inflate(layoutInflater) }
    lateinit var myDbHelper: MyDbHelper
    lateinit var sotuvchiXaridorAdapter: SotuvchiXaridorAdapter<Sotuvchi>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        sotuvchiXaridorAdapter = SotuvchiXaridorAdapter(myDbHelper.getAllSotuvchi())
        binding.rv.adapter = sotuvchiXaridorAdapter

        binding.apply {
            btnSave.setOnClickListener {
                val sotuvchi = Sotuvchi(
                    edtName.text.toString(),
                    edtPhone.text.toString()
                )
                myDbHelper.addSotuvchi(sotuvchi)
                sotuvchiXaridorAdapter.list.add(sotuvchi)
                sotuvchiXaridorAdapter.notifyItemInserted(sotuvchiXaridorAdapter.list.size-1)
                Toast.makeText(this@SotuvchiActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
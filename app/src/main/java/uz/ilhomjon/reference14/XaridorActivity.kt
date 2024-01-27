package uz.ilhomjon.reference14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import uz.ilhomjon.reference14.adapter.SotuvchiXaridorAdapter
import uz.ilhomjon.reference14.databinding.ActivitySotuvchiXaridorBinding
import uz.ilhomjon.reference14.db.MyDbHelper
import uz.ilhomjon.reference14.models.Sotuvchi
import uz.ilhomjon.reference14.models.Xaridor

class XaridorActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySotuvchiXaridorBinding.inflate(layoutInflater) }
    lateinit var myDbHelper: MyDbHelper
    lateinit var sotuvchiXaridorAdapter: SotuvchiXaridorAdapter<Xaridor>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.edtAddress.visibility = View.VISIBLE

        myDbHelper = MyDbHelper(this)
        sotuvchiXaridorAdapter = SotuvchiXaridorAdapter(myDbHelper.getAllXaridor())
        binding.rv.adapter = sotuvchiXaridorAdapter

        binding.apply {
            btnSave.setOnClickListener {
                val xaridor = Xaridor(
                    edtName.text.toString(),
                    edtPhone.text.toString(),
                    edtAddress.text.toString()
                )
                myDbHelper.addXaridor(xaridor)
                sotuvchiXaridorAdapter.list.add(xaridor)
                sotuvchiXaridorAdapter.notifyItemInserted(sotuvchiXaridorAdapter.list.size-1)
                Toast.makeText(this@XaridorActivity, "Saved", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
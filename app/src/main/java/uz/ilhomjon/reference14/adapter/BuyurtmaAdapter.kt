package uz.ilhomjon.reference14.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.reference14.databinding.ItemBuyurtmaBinding
import uz.ilhomjon.reference14.models.Buyurtma

class BuyurtmaAdapter(var list:ArrayList<Buyurtma> = ArrayList()): RecyclerView.Adapter<BuyurtmaAdapter.Vh>() {

         inner class Vh(val itemRvBinding: ItemBuyurtmaBinding):RecyclerView.ViewHolder(itemRvBinding.root){

             fun onBind(buyurtma: Buyurtma){
                 itemRvBinding.tvName.text = buyurtma.name
                 itemRvBinding.tvDate.text = buyurtma.date
                 itemRvBinding.tvSotuvchi.text = buyurtma.sotuvchi?.name
                 itemRvBinding.tvXaridor.text = buyurtma.xaridor?.name
             }
         }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
             return Vh(ItemBuyurtmaBinding.inflate(LayoutInflater.from(parent.context),parent, false))
         }

         override fun getItemCount(): Int = list.size

         override fun onBindViewHolder(holder: Vh, position: Int) {
             holder.onBind(list[position])
         }
     }
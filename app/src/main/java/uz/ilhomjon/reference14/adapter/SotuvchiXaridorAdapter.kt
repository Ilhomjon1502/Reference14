package uz.ilhomjon.reference14.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.reference14.databinding.ItemRvBinding
import uz.ilhomjon.reference14.models.Sotuvchi
import uz.ilhomjon.reference14.models.Xaridor

class SotuvchiXaridorAdapter<T>(var list:ArrayList<T> = ArrayList()):RecyclerView.Adapter<SotuvchiXaridorAdapter<T>.Vh>() {

         inner class Vh(val itemRvBinding: ItemRvBinding): RecyclerView.ViewHolder(itemRvBinding.root){

             fun onBindSotuvchi(sotuvchi: Sotuvchi){
                 itemRvBinding.tvName.text = sotuvchi.name
                 itemRvBinding.tvPhone.text=sotuvchi.phone
             }
             fun onBindXaridor(xaridor: Xaridor){
                 itemRvBinding.tvName.text = xaridor.name
                 itemRvBinding.tvPhone.text=xaridor.phone
                 itemRvBinding.tvAddress.visibility = View.VISIBLE
                 itemRvBinding.tvAddress.text = xaridor.address
             }
         }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
             return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent, false))
         }

         override fun getItemCount(): Int = list.size

         override fun onBindViewHolder(holder: Vh, position: Int) {
             try {
                 val sotuvchi:Sotuvchi = list[position] as Sotuvchi
                 holder.onBindSotuvchi(sotuvchi)
             }catch (e:Exception){
                 val xaridor:Xaridor = list[position] as Xaridor
                 holder.onBindXaridor(xaridor)
             }
         }
     }
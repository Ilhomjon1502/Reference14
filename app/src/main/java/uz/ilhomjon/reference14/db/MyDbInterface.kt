package uz.ilhomjon.reference14.db

import uz.ilhomjon.reference14.models.Buyurtma
import uz.ilhomjon.reference14.models.Sotuvchi
import uz.ilhomjon.reference14.models.Xaridor

interface MyDbInterface {
    fun addSotuvchi(sotuvchi: Sotuvchi)
    fun getAllSotuvchi():ArrayList<Sotuvchi>

    fun addXaridor(xaridor: Xaridor)
    fun getAllXaridor():ArrayList<Xaridor>

    fun addBuyurtma(buyurtma: Buyurtma)
    fun getAllBuyurtma():ArrayList<Buyurtma>

    fun getSotuvchiById(id:Int):Sotuvchi
    fun getXaridorById(id:Int):Xaridor
}
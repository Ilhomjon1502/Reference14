package uz.ilhomjon.reference14.models

import java.text.SimpleDateFormat
import java.util.Date

class Buyurtma {
    var id:Int? = null
    var name:String? = null
    var date:String? = null
    var sotuvchi:Sotuvchi? = null
    var xaridor:Xaridor? = null

    constructor(id: Int?, name: String?, date: String?, sotuvchi: Sotuvchi?, xaridor: Xaridor?) {
        this.id = id
        this.name = name
        this.date = date
        this.sotuvchi = sotuvchi
        this.xaridor = xaridor
    }

    constructor(name: String?, sotuvchi: Sotuvchi?, xaridor: Xaridor?) {
        this.name = name
        this.sotuvchi = sotuvchi
        this.xaridor = xaridor
        this.date = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date())
    }

    override fun toString(): String {
        return "Buyurtma(id=$id, name=$name, date=$date, sotuvchi=$sotuvchi, xaridor=$xaridor)"
    }


}
package uz.ilhomjon.reference14.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.ilhomjon.reference14.models.Buyurtma
import uz.ilhomjon.reference14.models.Sotuvchi
import uz.ilhomjon.reference14.models.Xaridor

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), MyDbInterface {

    companion object{
        const val DB_NAME = "my_db_14"
        const val DB_VERSION = 1
        const val SOTUVCHI_TABLE = "sotuvchi_table"
        const val XARIDOR_TABLE = "xaridor_table"
        const val BUYURTMA_TABLE = "buyurtma_table"

        const val SOTUVCHI_ID = "sotuvchi_id"
        const val XARIDOR_ID = "xaridor_id"

        const val DATE = "dates"
        const val ID = "id"
        const val NAME = "name"
        const val PHONE = "phone"
        const val ADDRESS = "address"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sq = "create table $SOTUVCHI_TABLE ($ID integer not null primary key autoincrement unique, $NAME text not null, $PHONE text not null);"
        val xq = "create table $XARIDOR_TABLE ($ID integer not null primary key autoincrement unique, $NAME text not null, $PHONE text not null, $ADDRESS text not null);"
        val bq = "create table $BUYURTMA_TABLE ($ID integer not null primary key autoincrement unique, $NAME text not null, $SOTUVCHI_ID integer not null, $XARIDOR_ID integer not null, $DATE text not null, foreign key ($SOTUVCHI_ID) references $SOTUVCHI_TABLE ($ID), foreign key ($XARIDOR_ID) references $XARIDOR_TABLE (${ID}) );"
        db?.execSQL(sq)
        db?.execSQL(xq)
        db?.execSQL(bq)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addSotuvchi(sotuvchi: Sotuvchi) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, sotuvchi.name)
        contentValues.put(PHONE, sotuvchi.phone)
        database.insert(SOTUVCHI_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllSotuvchi(): ArrayList<Sotuvchi> {
        val list = ArrayList<Sotuvchi>()
        val database = this.readableDatabase
        val query = "select * from $SOTUVCHI_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    Sotuvchi(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun addXaridor(xaridor: Xaridor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, xaridor.name)
        contentValues.put(PHONE, xaridor.phone)
        contentValues.put(ADDRESS, xaridor.address)
        database.insert(XARIDOR_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllXaridor(): ArrayList<Xaridor> {
        val list = ArrayList<Xaridor>()
        val database = this.readableDatabase
        val query = "select * from $XARIDOR_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun addBuyurtma(buyurtma: Buyurtma) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, buyurtma.name)
        contentValues.put(SOTUVCHI_ID, buyurtma.sotuvchi?.id)
        contentValues.put(XARIDOR_ID, buyurtma.xaridor?.id)
        contentValues.put(DATE, buyurtma.date)
        database.insert(BUYURTMA_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllBuyurtma(): ArrayList<Buyurtma> {
        val list = ArrayList<Buyurtma>()
        val database = this.readableDatabase
        val query = "select * from $BUYURTMA_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()){
            do {
                val buyurtma = Buyurtma(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(4),
                    getSotuvchiById(cursor.getInt(2)),
                    getXaridorById(cursor.getInt(3))
                )
                list.add(buyurtma)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun getSotuvchiById(id: Int): Sotuvchi {
        val database = this.readableDatabase
        val cursor = database.query(
            SOTUVCHI_TABLE,
            arrayOf(ID, NAME, PHONE),
            "$ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.moveToFirst()
        val sotuvchi = Sotuvchi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return sotuvchi
    }

    override fun getXaridorById(id: Int): Xaridor {
        val database = this.readableDatabase
        val cursor = database.query(
            XARIDOR_TABLE,
            arrayOf(ID, NAME, PHONE, ADDRESS),
            "$ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.moveToFirst()
        val sotuvchi = Xaridor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        return sotuvchi
    }
}
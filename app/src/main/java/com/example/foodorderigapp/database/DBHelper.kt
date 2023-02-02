package com.example.foodorderigapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.example.foodorderigapp.models.OrdersModel

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION ) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "FoodDatabase"
        private const val TABLE_NAME = "orders"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("create table " +
                TABLE_NAME +
                "(" +
                " id integer primary key autoincrement," +
                " name text," +
                " phone text," +
                " image integer," +
                " foodName text," +
                " price integer," +
                " quantity integer," +
                " description text" +
                ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP table if exists $TABLE_NAME")
        onCreate(db)
    }

    fun insertOrder(name: String, phone: String, image: Int, foodName: String, price: Int, quantity: Int?, description: String?): Boolean {
        val database = this.readableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("phone", phone)
        contentValues.put("image", image)
        contentValues.put("foodName", foodName)
        contentValues.put("price", price)
        contentValues.put("quantity", quantity)
        contentValues.put("description", description)
        val id = database.insert(TABLE_NAME, null, contentValues)
        return when {
            id <= 0 -> false
            else -> true
        }
    }

    fun getOrders(): MutableList<OrdersModel> {
        val ordersList = mutableListOf<OrdersModel>()
        val database = this.writableDatabase
        val cursor = database.rawQuery("select id, name, image, price from $TABLE_NAME", null)
        var image: Int
        var name: String
        var number: String
        var price: String
        if(cursor.moveToFirst()) {
            do {
                number = cursor.getInt(0).toString()
                name = cursor.getString(1)
                image = cursor.getInt(2)
                price = cursor.getString(3)
                val order = OrdersModel(image,name, number, price)
                ordersList.add(order)
            }while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return ordersList
    }

    fun getOrderDetailsById(id: Int): Cursor {
        val database = this.writableDatabase
        val cursor = database.rawQuery("select * from $TABLE_NAME where id = $id", null)
        if(cursor != null)
            cursor.moveToFirst()
        return cursor
    }

    fun updateOrder(name: String, phone: String, image: Int, foodName: String, price: Int, quantity: Int?, description: String?, id: Int): Boolean {
        val database = this.readableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("phone", phone)
        contentValues.put("image", image)
        contentValues.put("foodName", foodName)
        contentValues.put("price", price)
        contentValues.put("quantity", quantity)
        contentValues.put("description", description)
        val value = database.update(TABLE_NAME,contentValues, "id = $id", null)
        return when {
            value <= 0 -> false
            else -> true
        }
    }
}
package com.example.foodorderigapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderigapp.adapters.OrdersAdapter
import com.example.foodorderigapp.database.DBHelper
import com.example.foodorderigapp.databinding.ActivityOrdersBinding
import com.example.foodorderigapp.models.OrdersModel

class OrdersActivity : AppCompatActivity() {
    private lateinit var binding:ActivityOrdersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper = DBHelper(this)

        val ordersList: MutableList<OrdersModel> = helper.getOrders()


        val adapter = OrdersAdapter(this, ordersList)
        binding.recyclerViewOrders.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewOrders.layoutManager = layoutManager
    }


}
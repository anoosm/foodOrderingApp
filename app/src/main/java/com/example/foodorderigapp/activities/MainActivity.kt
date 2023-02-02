package com.example.foodorderigapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderigapp.R
import com.example.foodorderigapp.adapters.MainAdapter
import com.example.foodorderigapp.databinding.ActivityMainBinding
import com.example.foodorderigapp.models.MainModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodList: MutableList<MainModel> = mutableListOf()
        foodList.add(MainModel(R.drawable.burger, "Burger", "1", "This is a Burger"))
        foodList.add(MainModel(R.drawable.pizza, "pizza", "15", "This is a pizza"))
        foodList.add(MainModel(R.drawable.steakbeef, "Beef Steak", "50", "This is a Beef Steak"))
        foodList.add(MainModel(R.drawable.shawaya, "Shawaya", "30", "This is a Shawaya"))
        foodList.add(MainModel(R.drawable.alooparatha, "Aloo paratha", "5", "This is Aloo paratha"))
        foodList.add(MainModel(R.drawable.biriyanichicken, "Chiken biriyani", "10", "This is Chiken biriyani"))
        foodList.add(MainModel(R.drawable.sambarrice, "sambar rice", "3", "This is sambarrice"))
        foodList.add(MainModel(R.drawable.payasamsem, "Semiya Payasam", "5", "This is Semiya Payasam"))

        val adapter = MainAdapter(this, foodList)
        binding.recyclerView.adapter = adapter

        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuOrders -> startActivity(Intent(this@MainActivity, OrdersActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
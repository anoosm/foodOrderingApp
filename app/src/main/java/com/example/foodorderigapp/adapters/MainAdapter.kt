package com.example.foodorderigapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderigapp.R
import com.example.foodorderigapp.activities.DetailsActivity
import com.example.foodorderigapp.models.MainModel

const val TAG = "MainAdapter"

class MainAdapter(val context: Context, val foodList: List<MainModel>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentFood = foodList[position]
        holder.foodImage.setImageResource(currentFood.image)
        holder.foodName.setText(currentFood.name)
        holder.foodPrice.setText(currentFood.price)
        holder.foodDescription.setText(currentFood.description)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("image", currentFood.image)
            intent.putExtra("name", currentFood.name)
            intent.putExtra("price", currentFood.price)
            intent.putExtra("description", currentFood.description)
            intent.putExtra("type", 1)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return foodList.size
    }

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.imageView_food)
        val foodName: TextView = itemView.findViewById(R.id.textView_name)
        val foodPrice: TextView = itemView.findViewById(R.id.textView_price)
        val foodDescription: TextView = itemView.findViewById(R.id.textView_Desc)

    }
}
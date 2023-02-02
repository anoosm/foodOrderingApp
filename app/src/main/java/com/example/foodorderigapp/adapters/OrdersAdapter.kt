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
import com.example.foodorderigapp.models.OrdersModel

class OrdersAdapter(val context: Context, val orderslist: List<OrdersModel>): RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.orders_layout, parent, false)
        return OrdersViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val currentOrder = orderslist[position]
        holder.orderImage.setImageResource(currentOrder.image)
        holder.orderName.setText(currentOrder.orderName)
        holder.orderNumber.setText(currentOrder.orderNumber)
        holder.orderPrice.setText(currentOrder.orderPrice)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", currentOrder.orderNumber)
            intent.putExtra("type", 2)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return orderslist.size
    }

    class OrdersViewHolder(orderView: View): RecyclerView.ViewHolder(orderView) {
        val orderImage: ImageView = orderView.findViewById(R.id.imageView_ordersFood)
        val orderName: TextView = orderView.findViewById(R.id.textView_orderName)
        val orderNumber: TextView = orderView.findViewById(R.id.textView_orderNo)
        val orderPrice: TextView = orderView.findViewById(R.id.textView_ordersPrice)
    }
}
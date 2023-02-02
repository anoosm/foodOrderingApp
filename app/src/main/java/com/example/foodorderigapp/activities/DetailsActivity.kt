package com.example.foodorderigapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodorderigapp.database.DBHelper
import com.example.foodorderigapp.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    val helper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getIntExtra("type", 0) == 1) {

            val image = intent.getIntExtra("image", 0)
            val name = intent.getStringExtra("name")
            val price = intent.getStringExtra("price")!!.toInt()
            val description = intent.getStringExtra("description")

            binding.imageViewDetailsFood.setImageResource(image)
            binding.textViewDetailsName.text = name
            binding.textViewDetailsPrice.text = price.toString()
            binding.textViewDetailsDesc.text = description

            binding.btnDetailsOrder.setOnClickListener {

                val isInserted = helper.insertOrder(
                    binding.editTextTextPersonName.text.toString(),
                    binding.editTextPhone.text.toString(),
                    image,
                    name!!,
                    price,
                    binding.textViewDetailsQuantity.text.toString().toInt(),
                    description
                )

                if (isInserted)
                    Toast.makeText(this@DetailsActivity, "Data Success", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "An Error Occurred", Toast.LENGTH_LONG).show()
            }
        } else {
            val id = intent.getStringExtra("id")
            val cursor = helper.getOrderDetailsById(id!!.toInt())
            val imageid = cursor.getInt(3)
            binding.imageViewDetailsFood.setImageResource(imageid)
            binding.textViewDetailsName.text = cursor.getString(4)
            binding.textViewDetailsPrice.text = cursor.getString(5)
            binding.textViewDetailsDesc.text = cursor.getString(6)

            binding.editTextTextPersonName.setText(cursor.getString(1))
            binding.editTextPhone.setText(cursor.getString(2))
            binding.btnDetailsOrder.setText("Update Now")
            binding.btnDetailsOrder.setOnClickListener {
                val isUpdated = helper.updateOrder(
                    binding.editTextTextPersonName.text.toString(),
                    binding.editTextPhone.text.toString(),
                    imageid,
                    binding.textViewDetailsName.text.toString(),
                    binding.textViewDetailsPrice.text.toString().toInt(),
                    binding.textViewDetailsQuantity.text.toString().toInt(),
                    binding.textViewDetailsDesc.text.toString(),
                    id.toInt()
                )
                when {
                    isUpdated -> Toast.makeText(this@DetailsActivity, "Update Success", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(this@DetailsActivity, "Failed to Update!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
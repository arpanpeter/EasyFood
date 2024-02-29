package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.pojo.MealsByCategory

class MostPopularRecyclerAdapter : RecyclerView.Adapter<MostPopularRecyclerAdapter.MostPopularMealViewHolder>() {
    private var mealsList: List<MealsByCategory> = ArrayList()
   // private lateinit var onItemClick: OnItemClick
    private lateinit var onLongItemClick: OnLongItemClick
    lateinit var onItemClick:((MealsByCategory)->Unit)


    fun setMealList(mealsList: List<MealsByCategory>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

//    fun setOnClickListener(onItemClick: OnItemClick) {
//        this.onItemClick = onItemClick
//    }

//    fun setOnLongCLickListener(onLongItemClick: OnLongItemClick) {
//        this.onLongItemClick = onLongItemClick
//    }

    inner class MostPopularMealViewHolder(private val binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: MealsByCategory) {
            binding.apply {
                Glide.with(itemView)
                    .load(category.strMealThumb)
                    .into(imgPopularMealItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularMealViewHolder {
        val binding = PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MostPopularMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MostPopularMealViewHolder, position: Int) {
        val category = mealsList[position]
        holder.bind(category)
        holder.itemView.setOnClickListener {
            onItemClick.invoke((mealsList[position]))
        }




    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}

interface OnItemClick {
    fun onItemClick(meal: MealsByCategory)
}

interface OnLongItemClick {
    fun onItemLongClick(meal: MealsByCategory)
}

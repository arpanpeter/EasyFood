package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.pojo.Category
import com.example.easyfood.databinding.CategoryitemBinding
import com.example.easyfood.pojo.CategoryList

class CategoriesRecyclerAdapter : RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoryViewHolder>() {
    private var categoryList:List<Category> = ArrayList()
    var onItemClick : ((Category)->Unit)?=null
    private lateinit var onLongCategoryClick:OnLongCategoryClick


    fun setCategoryList(categoryList: List<Category>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun setOnLongCategoryClick(onLongCategoryClick:OnLongCategoryClick){
        this.onLongCategoryClick = onLongCategoryClick
    }



//    fun onItemClicked(onItemClick: OnItemCategoryClicked){
//        this.onItemClick = onItemClick
//    }

    class CategoryViewHolder(val binding:CategoryitemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryitemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.binding.tvCategoryName.text = currentItem.strCategory
        Glide.with(holder.itemView)
            .load(currentItem.strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }



    override fun getItemCount(): Int {
        return categoryList.size
    }

    interface OnItemCategoryClicked{
        fun onClickListener(category:Category)
    }

    interface OnLongCategoryClick{
        fun onCategoryLongCLick(category:Category)
    }
}
package com.example.easyfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.easyfood.adapters.CategoryMealsAdapter
import com.example.easyfood.databinding.ActivityCategoryBinding
import com.example.easyfood.viewModel.CategoryMealsViewModel

class CategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryBinding
    lateinit var categoryMealsViewModel:CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryBinding.inflate(layoutInflater)

        setContentView(binding.root)
        prepareRecyclerView()
        categoryMealsViewModel=ViewModelProviders.of(this)[CategoryMealsViewModel::class.java]
        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
categoryMealsViewModel.observeMealsLiveData().observe(this, Observer { mealsList->
    binding.tvCategoryCount.text=mealsList.size.toString()
    categoryMealsAdapter.setMealsList(mealsList)

})
    }

    private fun prepareRecyclerView() {
categoryMealsAdapter= CategoryMealsAdapter()


    binding.mealRecyclerview.apply {
        layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        adapter=categoryMealsAdapter
    }}
}
package com.example.easyfood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Meal
import com.bumptech.glide.Glide
import com.example.easyfood.adapters.CategoriesRecyclerAdapter
import com.example.easyfood.adapters.MostPopularRecyclerAdapter
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.MealsByCategory
import com.example.easyfood.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    companion object {
        const val MEAL_ID = "package com.example.easyfood.idMeal"
        const val MEAL_NAME = "package com.example.easyfood.nameMeal"
        const val MEAL_THUMB = "package com.example.easyfood.thumbMeal"
        const val CATEGORY_NAME="package.com.example.easyfood.categoryName"

    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: MostPopularRecyclerAdapter
    private lateinit var categoriesRecyclerAdapter: CategoriesRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
popularItemsAdapter=MostPopularRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparepopularitemRecyclerView()
prepareCategoriesRecyclerView()
        homeMvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        homeMvvm.getPopularItems()
        observePopularItemsLiveData()
        onPopularItemClick()

        homeMvvm.getCategories()
        observeCategoriesLiveData()
        onCategoryClick()

    }

    private fun onCategoryClick() {
        categoriesRecyclerAdapter.onItemClick={category ->
            val intent=Intent(activity,CategoryActivity::class.java)
            intent.putExtra(CATEGORY_NAME,category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        categoriesRecyclerAdapter = CategoriesRecyclerAdapter() // Initialize your adapter first
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesRecyclerAdapter // Assign the adapter to the RecyclerView
        }
    }


    private fun observeCategoriesLiveData() {
        homeMvvm.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories->
            categories.forEach { category ->
                categoriesRecyclerAdapter.setCategoryList(categories)


            }
        })
    }

    private fun onPopularItemClick() {
        popularItemsAdapter.onItemClick={
            meal->
            val intent=Intent(activity,activity_meal::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun preparepopularitemRecyclerView() {
binding.recViewMealsPopular.apply {
    layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
    adapter=popularItemsAdapter
}    }

    private fun observePopularItemsLiveData() {
        homeMvvm.observerPopularItemsLiveData().observe(viewLifecycleOwner
        ) {
            mealList->
            popularItemsAdapter.setMealList(mealsList = mealList as ArrayList<MealsByCategory>)

        }
    }

    private fun onRandomMealClick() {
        binding.randomMeal.setOnClickListener {
            val intent = Intent(activity, activity_meal::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner, { meal ->

            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)
            this.randomMeal=meal

        })
    }
}




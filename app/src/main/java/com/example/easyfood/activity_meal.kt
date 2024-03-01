package com.example.easyfood

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.MealViewModel

class activity_meal : AppCompatActivity() {

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealMvvm: MealViewModel
    private lateinit var youtubeLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]
        getMealInfoFromIntent()
        setInfoInViews()
        mealMvvm.getMealDetail(mealId)
        observerMealDeatailsLiveData()
        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDeatailsLiveData() {
        mealMvvm.meals.observe(this, object : Observer<Meal> {
            override fun onChanged(value: Meal) {
                val meal = value
                binding.tvCategoryInfo.text = "Category:${meal!!.strCategory}"
                binding.tvAreaInfo.text = "Area:${meal!!.strArea}"
                binding.tvInstructions.text = meal.strInstructions
                youtubeLink = meal.strYoutube.toString()

            }
        })
    }


    private fun setInfoInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.black))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInfoFromIntent() {
        val tempIntent = intent
        this.mealId = tempIntent.getStringExtra(HomeFragment.MEAL_ID)!!
        this.mealName = tempIntent.getStringExtra(HomeFragment.MEAL_NAME)!!
        this.mealThumb = tempIntent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}

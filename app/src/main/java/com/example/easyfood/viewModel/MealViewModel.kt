package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.easyfood.db.MealDataBase
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealList
import com.example.easyfood.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(
   // val mealDataBase: MealDataBase
):ViewModel() {

    private val _mealDetailsLiveData = MutableLiveData<Meal>()
    val meals: LiveData<Meal>
        get() = _mealDetailsLiveData

    fun getMealDetail(id: String) {
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    _mealDetailsLiveData.value = response.body()!!.meals[0]
                    //randomMealLiveData.value = randomMeal
                } else {
                    return

                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MAinActivity", t.message.toString())
            }
        })


    }
}

//    fun insertMeal(meal: Meal){
//        viewModelScope.launch{
//            mealDataBase.mealDao().update(meal)
//        }
//
//    }
//    fun deleteMeal(meal: Meal){
//        viewModelScope.launch {
//            mealDataBase.mealDao().delete(meal)
//        }
//    }
//}
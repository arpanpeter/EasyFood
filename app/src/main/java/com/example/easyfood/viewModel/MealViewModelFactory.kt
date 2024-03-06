//package com.example.easyfood.viewModel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.easyfood.db.MealDataBase
//
//class MealViewModelFactory(
//    val mealDataBase: MealDataBase
//) :ViewModelProvider.Factory
//{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MealViewModel(mealDataBase)as T
//    }
//}
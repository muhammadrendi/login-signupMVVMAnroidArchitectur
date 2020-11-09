package com.example.loginsignupmvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginsignupmvvm.model.AuthViewModel
import com.example.loginsignupmvvm.data.repository.AuthRepository
import com.example.loginsignupmvvm.data.repository.BaseRepository
import com.example.loginsignupmvvm.data.repository.UserRepository
import com.example.loginsignupmvvm.model.HomeViewModel

class ViewModelFactory (
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java)->HomeViewModel(repository as UserRepository ) as T
            else -> throw IllegalAccessException("viewModelClass Not Found")
        }
    }
}
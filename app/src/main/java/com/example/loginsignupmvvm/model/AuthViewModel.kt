package com.example.loginsignupmvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsignupmvvm.base.BaseViewModel
import com.example.loginsignupmvvm.data.network.Resource
import com.example.loginsignupmvvm.data.repository.AuthRepository
import com.example.loginsignupmvvm.data.response.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(private val repository:AuthRepository): BaseViewModel(repository) {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse:LiveData<Resource<LoginResponse>>
    get() = _loginResponse



    fun login(email:String, password:String) = viewModelScope.launch{
        _loginResponse.value = repository.login(email, password)
    }

    suspend  fun saveAuthToken(token: String) {
         repository.saveAuthToken(token)
    }
}
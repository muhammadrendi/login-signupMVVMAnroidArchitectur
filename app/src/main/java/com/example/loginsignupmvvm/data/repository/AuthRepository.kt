package com.example.loginsignupmvvm.data.repository

import androidx.datastore.preferences.preferencesKey
import com.example.loginsignupmvvm.data.UserPreferences
import com.example.loginsignupmvvm.data.network.AuthApi

class AuthRepository(private val api: AuthApi, private val preferences: UserPreferences) : BaseRepository(){
    suspend fun login(email:String, password:String) = safeApiCall{
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}
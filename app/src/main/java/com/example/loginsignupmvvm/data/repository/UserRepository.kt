package com.example.loginsignupmvvm.data.repository

import androidx.datastore.preferences.preferencesKey
import com.example.loginsignupmvvm.data.UserPreferences
import com.example.loginsignupmvvm.data.network.AuthApi
import com.example.loginsignupmvvm.data.network.UserApi

class UserRepository(private val api: UserApi) : BaseRepository(){

     suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}
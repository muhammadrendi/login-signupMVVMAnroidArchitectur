package com.example.loginsignupmvvm.base

import androidx.lifecycle.ViewModel
import com.example.loginsignupmvvm.data.network.UserApi
import com.example.loginsignupmvvm.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(private val repository: BaseRepository): ViewModel() {
    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO){ repository.logout(api)}
}
package com.example.loginsignupmvvm.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.loginsignupmvvm.base.BaseFragments
import com.example.loginsignupmvvm.data.network.Resource
import com.example.loginsignupmvvm.data.network.UserApi
import com.example.loginsignupmvvm.data.repository.UserRepository
import com.example.loginsignupmvvm.data.response.User
import com.example.loginsignupmvvm.databinding.FragmentHomeBinding
import com.example.loginsignupmvvm.model.HomeViewModel
import com.example.loginsignupmvvm.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragments<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBarHome.visible(false)
        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    binding.progressBarHome.visible(false)
                    UpdateUI(it.value.user)
                }
                is Resource.Loading ->{
                    binding.progressBarHome.visible(true)
                }
            }
        })

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun UpdateUI(user: User) {
        with(binding){
            textViewIdUser.text = "ID User : "+user.id.toString()
            textViewUserName.text = "User Name : "+user.name
            textViewEmailUser.text = "User Email :" +user.email
        }
    }

    override fun getViewModel() = HomeViewModel::class.java
    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking {  userPreferences.authToken.first() }
       val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return  UserRepository(api)
    }


}
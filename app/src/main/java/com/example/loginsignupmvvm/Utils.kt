package com.example.loginsignupmvvm

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.loginsignupmvvm.base.BaseFragments
import com.example.loginsignupmvvm.data.network.Resource
import com.example.loginsignupmvvm.fragments.LoginFragment
import com.google.android.material.snackbar.Snackbar

fun<A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisble:Boolean){
    visibility = if (isVisble) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean){
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (()-> Unit)? = null){
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
        failure: Resource.Failure,
        retry: (()-> Unit)? = null
){
    when{
        failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
        failure.errorCode == 401 ->{
            if (this is LoginFragment){
                requireView().snackbar("You've entered incorret email or password")
            }else{
                (this as BaseFragments<*,*,*>).logout()
            }
        }
        else -> {
            val error =failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}
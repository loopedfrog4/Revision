package com.example.revision

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class simpleViewModel: ViewModel() {

    private val _myLiveData = MutableLiveData<Int>()

    val myLiveData : LiveData<Int> = _myLiveData

    fun updateLiveData(newValue: Int){
        _myLiveData.value = newValue
    }

}
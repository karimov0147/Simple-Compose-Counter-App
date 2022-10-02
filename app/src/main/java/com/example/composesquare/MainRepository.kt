package com.example.composesquare

import androidx.lifecycle.MutableLiveData

class MainRepository {

    private var number = 0

    fun incrementCounter() : Int {
        number += 1
        return number
    }


}
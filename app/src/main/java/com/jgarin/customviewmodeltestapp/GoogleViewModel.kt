package com.jgarin.customviewmodeltestapp

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class GoogleViewModel(private val number: Int) : ViewModel() {

	private val liveData = MutableLiveData<State>()

	fun getStateLiveData(): LiveData<State> = liveData

	init {
		liveData.value = State(number, allItems.filterNot { it == number })
	}

	data class State(val number: Int, val otherNumbers: List<Int>)

	class Factory(private val number: Int) : ViewModelProvider.Factory {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			return GoogleViewModel(number) as T
		}
	}

}

private val allItems = (0 until 10).toList()
package com.jgarin.customviewmodeltestapp

abstract class BaseViewModel<out T : BaseParams>(protected val params: T)

interface BaseParams
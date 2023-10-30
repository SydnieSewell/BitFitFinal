package com.example.bitfitfinal


import android.app.Application

class CycleApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}
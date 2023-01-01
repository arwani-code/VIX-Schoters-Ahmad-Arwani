package com.arwani.ahmad.schotersnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arwani.ahmad.schotersnews.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_main)
    }
}
package com.example.netflix_compose.adapters

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter: RecyclerView.Adapter<MyComposeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyComposeViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyComposeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}

class MyComposeViewHolder(
    val composeView: ComposeView
): RecyclerView.ViewHolder(composeView){

}
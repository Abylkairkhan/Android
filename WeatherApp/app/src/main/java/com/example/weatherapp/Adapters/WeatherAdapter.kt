package com.example.weatherapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.DataModels.WeatherModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class WeatherAdapter(private val listener: Listener?): ListAdapter<WeatherModel, WeatherAdapter.Holder>(Comparator()){

    class Holder(view: View, private val listener: Listener?): RecyclerView.ViewHolder(view){

        private val binding = ListItemBinding.bind(view)
        var itemTemp: WeatherModel? = null

        init {
            itemView.setOnClickListener{
                itemTemp?.let { it1 -> listener?.onClick(it1) }
            }
        }

        fun init(data: WeatherModel) = with(binding){
            itemTemp = data
            textTimeData.text = data.time
            textCondition.text = data.condition
            textTemp.text = if(data.currentTemp.isEmpty())
                "${data.maxTemp}°C / ${data.minTemp}°C" else data.currentTemp + "°C"
            Picasso.get().load("https:" + data.imageUrl).into(imageCondition)
        }
    }

    class Comparator: DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return  oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.init(getItem(position))
    }

    interface Listener{
        fun onClick(item:WeatherModel)
    }
}


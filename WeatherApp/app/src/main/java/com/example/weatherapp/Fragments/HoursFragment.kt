package com.example.weatherapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapters.WeatherAdapter
import com.example.weatherapp.DataModels.WeatherModel
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject

class HoursFragment : Fragment() {

    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()
        model.liveDataCurrent.observe(viewLifecycleOwner){
            adapter.submitList(parseHourData(it))
        }
    }

    private fun initRecView() = with(binding){
        WeatherRV.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        WeatherRV.adapter = adapter
    }

    private fun parseHourData(weatherItem: WeatherModel): List<WeatherModel>{
        val hourList = JSONArray(weatherItem.hours)
        val list = ArrayList<WeatherModel>()
        for (i in 0 until hourList.length()){
            val item = WeatherModel(
                weatherItem.city,
                (hourList[i] as JSONObject).getString("time"),
                (hourList[i] as JSONObject).getJSONObject("condition")
                    .getString("text"),
                (hourList[i] as JSONObject).getString("temp_c").toFloat().toInt().toString(),
                "",
                "",
                (hourList[i] as JSONObject).getJSONObject("condition")
                    .getString("icon"),
                ""
            )
            list.add(item)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}
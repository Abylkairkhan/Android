package com.example.weatherapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.Adapters.WeatherAdapter
import com.example.weatherapp.DataModels.WeatherModel
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDaysBinding

class DaysFragment : Fragment(), WeatherAdapter.Listener {

    private lateinit var binding: FragmentDaysBinding
    private lateinit var adapter: WeatherAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.liveDataList.observe(viewLifecycleOwner){
            adapter.submitList(it.subList(1, it.size))
        }
    }

    override fun onClick(item: WeatherModel) {
        model.liveDataCurrent.value = item
    }

    private fun init() = with(binding){
        adapter = WeatherAdapter(this@DaysFragment)
        RecView.layoutManager = LinearLayoutManager(activity)
        RecView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }


}
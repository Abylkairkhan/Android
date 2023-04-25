package com.example.weatherapp.Fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.Adapters.ViewPagerAdapter
import com.example.weatherapp.DataModels.WeatherModel
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.json.JSONObject

const val API_KEY = "8487205fd0e5481382875914232103"

class MainFragment : Fragment() {

    private val fragmentList = listOf(HoursFragment.newInstance(), DaysFragment.newInstance())
    private val stringList = listOf("HOURS", "DAYS")
    private val model: MainViewModel by activityViewModels()
    private lateinit var fLocationClient: FusedLocationProviderClient
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        updateCard()
        requestWeatherAPI("Almaty")
//        getLocation()
    }

    private fun parseWeatherData(data: String){
        val mainObj = JSONObject(data)
        val list = parseDaysData(mainObj)
        parseCurrentData(mainObj, list[0])
    }

    private fun updateCard() = with(binding){
        model.liveDataCurrent.observe(viewLifecycleOwner){
            textData.text = it.time
            Picasso.get().load("https:" + it.imageUrl).into(imageWeather)
            textCity.text = it.city
            textCurrentTemp.text = if(it.currentTemp.isEmpty())
                "${it.maxTemp}°C / ${it.minTemp}°C" else it.currentTemp + "°C"
            textDayCondition.text = it.condition
            textMaxMinTemp.text = if(it.currentTemp.isEmpty()) "" else it.maxTemp + "°C / " + it.minTemp + "°C"
        }
    }

    private fun parseDaysData(mainObj:JSONObject): List<WeatherModel>{
        val list = ArrayList<WeatherModel>()
        val daysArray = mainObj.getJSONObject("forecast").getJSONArray("forecastday")
        val name = mainObj.getJSONObject("location").getString("name")

        for (x in 0 until daysArray.length()){
            val day = daysArray[x] as JSONObject
            val item = WeatherModel(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c").toFloat().toInt().toString(),
                day.getJSONObject("day").getString("mintemp_c").toFloat().toInt().toString(),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        model.liveDataList.value = list
        return list
    }

    private fun parseCurrentData(mainObj:JSONObject, weatherItem: WeatherModel){
        val item = WeatherModel(
            mainObj.getJSONObject("location")
                .getString("name"),
            mainObj.getJSONObject("current")
                .getString("last_updated"),
            mainObj.getJSONObject("current")
                .getJSONObject("condition")
                .getString("text"),
            mainObj.getJSONObject("current")
                .getString("temp_c").toFloat().toInt().toString(),
            weatherItem.maxTemp.toFloat().toInt().toString(),
            weatherItem.minTemp.toFloat().toInt().toString(),
            mainObj.getJSONObject("current")
                .getJSONObject("condition")
                .getString("icon"),
            weatherItem.hours
        )
        model.liveDataCurrent.value = item
//        Log.d("MyLog", "MaxTemp ${item.minTemp}")
//        Log.d("MyLog", "MaxTemp ${item.maxTemp}")
    }

    private fun requestWeatherAPI(city: String){
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" + API_KEY +
                "&q=" + city + "&days=5" + "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                res -> parseWeatherData(res)
            },
            {
                res -> Log.d("MyLog", "Error $res")
            }
        )
        queue.add(request)
    }

    private fun init(){
        fLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val adapter = ViewPagerAdapter(activity as FragmentActivity, fragmentList)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, pos -> tab.text = stringList[pos]
        }.attach()

        binding.ibSync.setOnClickListener(){
            getLocation()
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0))
        }
    }

    private fun getLocation(){
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnCompleteListener{
                Log.d("MyLog","${it.result.latitude},${it.result.longitude}")
            }
    }

    private fun permissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission(){
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
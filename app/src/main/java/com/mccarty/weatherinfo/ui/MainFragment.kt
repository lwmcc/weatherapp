package com.mccarty.weatherinfo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mccarty.weatherinfo.R
import com.mccarty.weatherinfo.adapters.WeatherAdapter
import com.mccarty.weatherinfo.api.WeatherResponse
import com.mccarty.weatherinfo.networkutils.utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val mainModel: MainViewModel by activityViewModels()

    private lateinit var adapter: WeatherAdapter
    private lateinit var list: MutableList<WeatherResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list = mutableListOf()

        mainModel.weatherResponseArrayList.observe(requireActivity(), Observer { weatherArray ->
            weatherArray?.let {
                list.clear()
                list.addAll(weatherArray)
                notifyDataChange()
            }
        })

        mainModel.userCity.observe(requireActivity(), Observer { city ->
            mainModel.getWeatherData(city)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.main_fragment, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_weather)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WeatherAdapter(requireActivity(), mainModel, list)
        recyclerView.adapter = adapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_city.setOnClickListener {
            if(utils.networkIsAvailable(requireContext())) {
                mainModel.setUserCity(edit_text_city.text.toString().trim())
            } else {
                Toast.makeText(requireContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun notifyDataChange() {
        adapter.notifyDataSetChanged()
    }
}
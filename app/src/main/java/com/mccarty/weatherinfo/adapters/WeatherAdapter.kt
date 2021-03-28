package com.mccarty.weatherinfo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mccarty.weatherinfo.R
import com.mccarty.weatherinfo.api.WeatherResponse
import com.mccarty.weatherinfo.ui.MainViewModel

class WeatherAdapter(private val context: Context, private val mainModel: MainViewModel,
                     private val weatherArray: MutableList<WeatherResponse>):

    RecyclerView.Adapter<WeatherAdapter.ViewHolder>()  {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val temp: TextView = view.findViewById(R.id.temp)
        val feelsLike: TextView = view.findViewById(R.id.feels_like)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_item_layout,
            viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.temp.text = context.resources.getString(R.string.temp) + " " + weatherArray[position].main.temp.toString()
        viewHolder.feelsLike.text = context.resources.getString(R.string.feels_like) + " " + weatherArray[position].main.feels_like.toString()

        viewHolder.temp.setOnClickListener {
            mainModel.setWeatherResponseObject(weatherArray[0]) // Set object so we can get it's data from ViewModel in Details fragment
        }
    }

    override fun getItemCount() = weatherArray.size

}
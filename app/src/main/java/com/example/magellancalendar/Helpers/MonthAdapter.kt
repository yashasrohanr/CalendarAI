package com.example.magellancalendar.Helpers

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.magellancalendar.R

private val LOGTAG = "MonthAdapter"

@RequiresApi(Build.VERSION_CODES.O)
class DayViewHolder(view: View ) :RecyclerView.ViewHolder(view){
    private var tv : TextView= view.findViewById(R.id.text_view)
    fun bind(day : Day, onDayClickListener: (Day) -> Unit){
        tv.text = day.date.dayOfMonth.toString()
        Log.d(LOGTAG, "bind called for ${day.isSelected} & ${day.date.dayOfMonth}")
        if(day.isSelected) {
            tv.setTextColor(Color.parseColor("#0000FF"))
        } else{
            tv.setTextColor(Color.parseColor("#00FFFF"))
        }
        tv.setOnClickListener {
            onDayClickListener(day)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
class MonthAdapter(private val onDayClickListener: (Day) -> Unit) : RecyclerView.Adapter<DayViewHolder>() {

    var days :List<Day> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_view_holder, parent, false)
        return DayViewHolder(view)
    }
    override fun getItemCount() = days.size
    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(days[position], onDayClickListener)

        holder.itemView.setOnClickListener {
            onDayClickListener(days[position])
        }

    }

}
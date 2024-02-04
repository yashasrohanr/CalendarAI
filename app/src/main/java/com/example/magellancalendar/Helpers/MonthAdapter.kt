package com.example.magellancalendar.Helpers

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.magellancalendar.R
import com.example.magellancalendar.SharedViewModel

private const val LOGTAG = "MonthAdapter"

@RequiresApi(Build.VERSION_CODES.O)
class DayViewHolder(view: View , private val viewmodel : SharedViewModel?) :RecyclerView.ViewHolder(view){
    private var tv : TextView = view.findViewById(R.id.text_view)
    private var e1 : TextView = view.findViewById(R.id.event1)
    private var e2 : TextView = view.findViewById(R.id.event2)

    fun bind(day: Day, onDayClickListener: (Day) -> Unit) {
        tv.text = day.date.dayOfMonth.toString()
        if (day.isSelected) {
            tv.setTextColor(Color.parseColor("#0000FF"))
        } else {
            tv.setTextColor(Color.parseColor("#00FFFF"))
        }

        e2.text = ""
        e2.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))

        // Get coordinates on the screen for event1 (e1)
        e1.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Ensure we call this only once
                e1.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val e1Coordinates = IntArray(2)
                e1.getLocationOnScreen(e1Coordinates)

                day.event1StartX = e1Coordinates[0]
                day.event1StartY = e1Coordinates[1]
                day.event1EndX = day.event1StartX + e1.width
                day.event1EndY = day.event1StartY + e1.height
                Log.i(LOGTAG, "bind: day = $day")

                viewmodel?.addDayWithCoordinates(day)
                tv.setOnClickListener {
                    onDayClickListener(day)
                }
            }
        })
    }
}
@RequiresApi(Build.VERSION_CODES.O)
class MonthAdapter(private val onDayClickListener: (Day) -> Unit, private val viewmodel : SharedViewModel?) : RecyclerView.Adapter<DayViewHolder>() {
    var days :List<Day> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_view_holder, parent, false)
        return DayViewHolder(view, viewmodel)
    }
    override fun getItemCount() = days.size
    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(days[position], onDayClickListener)
        holder.itemView.setOnClickListener {
            onDayClickListener(days[position])
        }
    }
}
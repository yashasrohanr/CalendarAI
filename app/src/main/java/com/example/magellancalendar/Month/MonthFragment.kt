package com.example.magellancalendar.Month

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magellancalendar.Helpers.Day
import com.example.magellancalendar.Helpers.MonthAdapter
import com.example.magellancalendar.R
import com.example.magellancalendar.SharedViewModel
import com.example.magellancalendar.databinding.FragmentMonthBinding
import java.time.LocalDate
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
class MonthFragment() : Fragment() {
    companion object {
        fun newInstance(year : Int, month : Int): MonthFragment {
            var fragment = MonthFragment()
            var args = Bundle()
            args.putInt("year", year)
            args.putInt("month", month)
            fragment.arguments = args
            return fragment
        }
    }
    private var _binding: FragmentMonthBinding? = null
    private val binding get() = _binding!!
    private val LOGTAG = "MonthFragment"
    private var sharedViewModel: SharedViewModel? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MonthAdapter
    private var prevSelectedDay : Day? = null

    private var mYear = -1
    private var mMonth = -1


    private fun updateSelectedDay(selectedDay: Day) {
        if (prevSelectedDay != null) {
            // Unselect the previous day
            prevSelectedDay!!.isSelected = false
        }
        // Select the current day
        selectedDay.isSelected = true
        prevSelectedDay = selectedDay
        Log.d(LOGTAG, "day selected is $selectedDay")
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMonthBinding.inflate(layoutInflater)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        adapter = MonthAdapter ({selectedDay -> updateSelectedDay(selectedDay)}, sharedViewModel)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 7)
        recyclerView.adapter = adapter

        binding.btnToast.setOnClickListener {
            //displayRandomCoordinate()
            addEventBetweenDates(1, 7)
        }
        return binding.root
    }
    fun addTextViewBetweenCoordinates(startX : Int, startY : Int, endX : Int, endY : Int, text : String) {
        Log.i(LOGTAG, "addTextViewBetweenCoordinates: startX = $startX, startY = $startY, endX = $endX, endY = $endY")
        val tv = TextView(requireContext())
        tv.text = text
        tv.gravity = Gravity.CENTER
        tv.background = ContextCompat.getDrawable(requireContext(), R.drawable.rounded_rectangle_grey)
        val layoutParams = FrameLayout.LayoutParams(
            endX- startX,
            endY - startY
        )
        layoutParams.leftMargin = startX
        layoutParams.topMargin = startY

        val fl = requireView().findViewById<FrameLayout>(R.id.flCalendar)
        fl.addView(tv, layoutParams)
    }

    fun addEventBetweenDates(startDay : Int, endDay : Int) {
        val list = sharedViewModel?.getDaysWithCoordinates()
        val sd = LocalDate.of(2024,2,startDay)
        val ed = LocalDate.of(2024, 2, endDay)
        val si = list?.indexOfFirst { it.date == sd }
        val ei = list?.indexOfFirst { it.date == ed }
        Log.i(LOGTAG, "addEventBetweenDates: si = $si & ei = $ei")
        if(list?.isNotEmpty()!!)
        {
            val sx = list!![si!!].event1StartX
            val sy = list!![si!!].event1StartY
            val ex = list!![ei!!].event1EndX
            val ey = list!![ei!!].event1EndY
            addTextViewBetweenCoordinates(sx,sy, ex, ey, "Random")
            Log.i(LOGTAG, "addEventBetweenDates: list[1] = ${list!![1]} & list[5] = ${list!![5]}")
        }
    }


    private fun displayRandomCoordinate() {
        if(sharedViewModel?.daysWithCoordinates!!.isNotEmpty()) {
            val day = sharedViewModel?.daysWithCoordinates!![Random.nextInt(sharedViewModel?.daysWithCoordinates!!.size)]
            val message = "Coordinates for ${day.date}: StartX=${day.event1StartX}, StartY=${day.event1StartY}, EndX=${day.event1EndX}, EndY=${day.event1EndY}"
            binding.tvCoordinate.text = message
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null) {
            mYear = requireArguments().getInt("year")
            mMonth = requireArguments().getInt("month")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val days = mutableListOf<Day>()
        val now = LocalDate.now()
        val firstDayOfMonth = now.withDayOfMonth(1)
        val daysInMonth = now.lengthOfMonth()
        for(i in 0..daysInMonth){
            val dayView = Day(
                date = firstDayOfMonth.plusDays(i.toLong()),
                isSelected = false,
                event1StartX = 0,
                event1StartY = 0,
                event1EndX = 0,
                event1EndY = 0
            )
            // Add the dayView to the list
            days.add(dayView)
        }
        Log.d(LOGTAG, "days size  = ${days.size}")
        adapter.days = days
        adapter.notifyDataSetChanged()
    }
}
package com.example.magellancalendar.Month

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magellancalendar.Helpers.Day
import com.example.magellancalendar.Helpers.MonthAdapter
import com.example.magellancalendar.SharedViewModel
import com.example.magellancalendar.databinding.FragmentMonthBinding
import java.time.LocalDate

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
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter = MonthAdapter {selectedDay -> updateSelectedDay(selectedDay)}
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

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 7)
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null) {
            mYear = requireArguments().getInt("year")
            mMonth = requireArguments().getInt("month")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val days = mutableListOf<Day>()
        val now = LocalDate.now()
        val firstDayOfMonth = now.withDayOfMonth(1)
        val daysInMonth = now.lengthOfMonth()
        for(i in 0..daysInMonth){
            days.add(Day(firstDayOfMonth.plusDays(i.toLong()), isSelected = false))
        }
        Log.d(LOGTAG, "days size  = ${days.size}")
        adapter.days = days
        adapter.notifyDataSetChanged()
    }

}
package com.example.magellancalendar.Month

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.magellancalendar.R

class MonthFragment : Fragment() {

    companion object {
        fun newInstance() = MonthFragment()
    }

    private lateinit var viewModel: MonthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_month, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MonthViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
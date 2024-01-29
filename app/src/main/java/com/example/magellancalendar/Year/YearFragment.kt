package com.example.magellancalendar.Year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.magellancalendar.R
import com.example.magellancalendar.SharedViewModel

class YearFragment : Fragment() {

    companion object {
        fun newInstance() = YearFragment()
    }

    private lateinit var viewModel: YearViewModel
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_year, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel = ViewModelProvider(this).get(YearViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
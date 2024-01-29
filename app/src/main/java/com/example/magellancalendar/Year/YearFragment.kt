package com.example.magellancalendar.Year

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.magellancalendar.R

class YearFragment : Fragment() {

    companion object {
        fun newInstance() = YearFragment()
    }

    private lateinit var viewModel: YearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_year, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(YearViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
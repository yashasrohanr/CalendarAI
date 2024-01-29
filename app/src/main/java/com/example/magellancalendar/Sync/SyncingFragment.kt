package com.example.magellancalendar.Sync

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.magellancalendar.R

class SyncingFragment : Fragment() {

    companion object {
        fun newInstance() = SyncingFragment()
    }

    private lateinit var viewModel: SyncingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_syncing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SyncingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.example.viewmodel.ui.counter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.viewmodel.R

class CounterFragment : Fragment(R.layout.fragment_counter) {

    private val viewModel by viewModels<CounterJetpackViewModel>()
    //private val viewModel = CounterSimpleViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCount = view.findViewById<Button>(R.id.btn_count)
        val textCounter = view.findViewById<TextView>(R.id.text_counter)

        btnCount.setOnClickListener {
            if (viewModel.isCounterRunning) {
                viewModel.stopCount()
            } else {
                viewModel.startCount()
            }
        }

        viewModel.btnTitleLiveData.observe(viewLifecycleOwner, Observer {
            btnCount.setText(it)
        })

        viewModel.counterLiveData.observe(viewLifecycleOwner, Observer {
            textCounter.text = it.toString()
        })
    }
}
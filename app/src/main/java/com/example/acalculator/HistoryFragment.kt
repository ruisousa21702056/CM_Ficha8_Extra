package com.example.acalculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private val operations = ArrayList<Operation>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val op = Operation("3+3",4.0)
        operations.add(op)
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        this.list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        this.list_historic?.adapter = HistoryAdapter(activity as Context,R.layout.item_expression, operations)
        super.onActivityCreated(savedInstanceState)
    }

}

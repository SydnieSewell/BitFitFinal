package com.example.bitfitfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private lateinit var rvCycle: RecyclerView
    private lateinit var addBtn: Button
    private var entries: MutableList<Cycle> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        rvCycle = view.findViewById(R.id.rvCycle)

        // Create adapter passing in the list
        val adapter = CyclesAdapter(view.context, entries)
        // Attach the adapter to the RecyclerView to populate items
        rvCycle.adapter = adapter
        // Set layout manager to position the items
        rvCycle.layoutManager = LinearLayoutManager(view.context)

        lifecycleScope.launch {
            (requireActivity().application as CycleApplication).db.CycleDao()
                .getAll().collect { databaseList ->
                    databaseList.map { entity ->
                        CycleEntity(
                            entity.id,
                            entity.dayOfweek,
                            entity.cycleLength,
                        )
                    }.also { mappedList ->
                        val cycleEntries = mappedList.map { CycleEntity ->
                            Cycle(
                                CycleEntity.dayOfweek,
                                CycleEntity.cycleLength,
                            )
                        }
                        entries.clear()
                        entries.addAll(cycleEntries)
                        rvCycle.adapter?.notifyDataSetChanged()
                    }
                }
        }
        return view
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}
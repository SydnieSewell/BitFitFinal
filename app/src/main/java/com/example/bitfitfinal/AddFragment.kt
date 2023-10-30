package com.example.bitfitfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragment : Fragment() {
    private lateinit var etCycle: EditText
    private lateinit var etCyc: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        etCycle = view.findViewById(R.id.etCycle)
        etCyc = view.findViewById(R.id.etCyc)
        submitBtn = view.findViewById(R.id.recordBtn)

        submitBtn.setOnClickListener {
            val CycleName = etCycle.text.toString()
            val length = etCyc.text.toString().toInt()

            let {
                lifecycleScope.launch(Dispatchers.IO) {
                    val list = ArrayList<CycleEntity>()
                    list.add(CycleEntity(dayOfweek = CycleName, cycleLength = length))
                    (requireActivity().application as CycleApplication).db.CycleDao()
                        .insertAll(list)
                }
            }
        }

        return view
    }

    companion object {
        fun newInstance(): AddFragment {
            return AddFragment()
        }
    }
}
package com.example.bitfitfinal


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode

class CycleSummaryFragment(private val displayEntry : List<DisplayEnteries>) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        if (displayEntry.isNotEmpty()) {
            Log.v("Cycle Summary/", "The list of records is not empty")
            updateView(view, displayEntry)
        } else {
            Log.e("Cycle Summary/", "The list of records is empty")
        }
        return view
    }

    private fun updateView(view: View, displayEntry : List<DisplayEnteries>) {
        val avgCycleLengthView=view.findViewById(R.id.avgCycLength) as TextView


        //This will be the totalrecords for the size of entries
        var totalRecords = displayEntry.size

        var totalCycle = 0

        displayEntry.forEach { record ->
            if (record.cycleLength != null) {
                totalCycle += record.cycleLength
            }
        }

        var avgCycLength = BigDecimal(totalCycle/ totalRecords).setScale(2, RoundingMode.HALF_UP).toInt()

        avgCycleLengthView.text = "$avgCycLength Days."
    }

    //    companion object {
//        fun newInstance(): CycleSummaryFragment{
//            return CycleSummaryFragment()
//        }
//    }
    companion object {
        fun newInstance(displayEntery: List<DisplayEnteries>): CycleSummaryFragment {
            return CycleSummaryFragment(displayEntery)
        }
    }

}
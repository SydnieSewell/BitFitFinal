package com.example.bitfitfinal
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddActivity: AppCompatActivity() {
    private lateinit var etCycle: EditText
    private lateinit var etCyc: EditText
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_page)
        etCycle= findViewById(R.id.etCycle)
        etCyc = findViewById(R.id.etCyc)
        submitBtn = findViewById(R.id.recordBtn)

        submitBtn.setOnClickListener { addItem() }
    }

    private fun addItem() {
        val CycleName = etCycle.text.toString()
        val length = etCyc.text.toString().toInt()

        let {
            lifecycleScope.launch(IO) {
                val list = ArrayList<CycleEntity>()
                list.add(CycleEntity(dayOfweek = CycleName, cycleLength = length))
                (application as CycleApplication).db.CycleDao().insertAll(list)
            }
        }

        finish()
    }
}
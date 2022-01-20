package com.example.studentsrandomapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsrandomapp.databinding.ActivityMainBinding
import java.io.Serializable
import kotlin.String as String

class MainActivity : AppCompatActivity(), Serializable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                val resultCode = activityResult.resultCode
                val data = activityResult.data
                if (resultCode == RESULT_OK) {
                    val randomStudentList = data?.getSerializableExtra(AbsentListActivity.EXTRA_KEY) as ArrayList<String>
                    binding.selectBtn.setOnClickListener {
                        val student = randomStudentList?.random()
                        binding.student.text = student
                    }
                }
            }

        binding.markAbsentBtn.setOnClickListener {
            getContent.launch(Intent(this, AbsentListActivity::class.java))
        }
    }
}
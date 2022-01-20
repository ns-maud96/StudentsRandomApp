package com.example.studentsrandomapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsrandomapp.databinding.AbsentListActivityBinding

class AbsentListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEY = "extraKey"
    }

    private var studentsList: ArrayList<String> = arrayListOf(
        "Ярослав", "Егор", "Стас", "Alex",
        "Саша", "Настя", "Анна", "Марина", "Надежда", "Дарья", "Наташа", "Антон", "Дима"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AbsentListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClick(R.id.check1)
        handleClick(R.id.check2)
        handleClick(R.id.check3)
        handleClick(R.id.check4)
        handleClick(R.id.check5)
        handleClick(R.id.check6)
        handleClick(R.id.check7)
        handleClick(R.id.check8)
        handleClick(R.id.check9)
        handleClick(R.id.check10)
        handleClick(R.id.check11)
        handleClick(R.id.check12)
        handleClick(R.id.check13)

        binding.confirmBtn.setOnClickListener {
            studentsList.removeIf { obj: String? -> obj == "" }
            if (studentsList.isEmpty()) {
                Toast.makeText(this, "Все отсутствуют", Toast.LENGTH_LONG).show()
            } else {
                setResult(
                    RESULT_OK, Intent().apply {
                        putExtra(EXTRA_KEY, studentsList)
                    }
                )
            }
            finish()
        }
    }

    private fun handleClick(idCheckBox: Int) {
        findViewById<CheckBox>(idCheckBox)?.setOnClickListener {
            studentsList = changeStudentsList(findViewById(idCheckBox))
        }
    }

    private fun changeStudentsList(view: CheckBox): ArrayList<String> {
        when (view.id) {
            R.id.check1 -> if (view.isChecked) studentsList[0] = ""
            R.id.check2 -> if (view.isChecked) studentsList[1] = ""
            R.id.check3 -> if (view.isChecked) studentsList[2] = ""
            R.id.check4 -> if (view.isChecked) studentsList[3] = ""
            R.id.check5 -> if (view.isChecked) studentsList[4] = ""
            R.id.check6 -> if (view.isChecked) studentsList[5] = ""
            R.id.check7 -> if (view.isChecked) studentsList[6] = ""
            R.id.check8 -> if (view.isChecked) studentsList[7] = ""
            R.id.check9 -> if (view.isChecked) studentsList[8] = ""
            R.id.check10 -> if (view.isChecked) studentsList[9] = ""
            R.id.check11 -> if (view.isChecked) studentsList[10] = ""
            R.id.check12 -> if (view.isChecked) studentsList[11] = ""
            R.id.check13 -> if (view.isChecked) studentsList[12] = ""
        }
        return studentsList
    }
}
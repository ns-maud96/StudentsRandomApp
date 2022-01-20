package com.example.studentsrandomapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

class AbsentListFragment : Fragment() {

    companion object {
        const val REQUESTED_KEY = "requestedKey"
        const val BUNDLE_KEY = "bundleKey"
        const val TAG = "com.example.studentsRandomApp.AbsentListFragment"
        fun newInstance() = AbsentListFragment()
    }

    private var studentsList: ArrayList<String> = arrayListOf(
        "Ярослав", "Егор", "Стас", "Alex",
        "Саша", "Настя", "Анна", "Марина", "Надежда", "Дарья", "Наташа", "Антон", "Дима"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.absent_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        view.findViewById<Button>(R.id.confirmBtn).setOnClickListener {
            studentsList.removeIf { obj: String? -> obj == "" }
            if (studentsList.isEmpty()) {
                Toast.makeText(context, "Все отсутствуют", Toast.LENGTH_LONG).show()
            } else {
                setFragmentResult(
                    REQUESTED_KEY,
                    bundleOf(BUNDLE_KEY to studentsList)
                )
            }
            parentFragmentManager.popBackStack()
        }
    }

    private fun handleClick(idCheckBox: Int) {
        view?.findViewById<CheckBox>(idCheckBox)?.setOnClickListener {
            studentsList = changeStudentsList(view!!.findViewById(idCheckBox))
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
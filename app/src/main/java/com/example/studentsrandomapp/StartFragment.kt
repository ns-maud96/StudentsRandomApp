package com.example.studentsrandomapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class StartFragment : Fragment() {

    companion object {
        const val TAG = "com.example.studentsRandomApp.StartFragment"
        fun newInstance() = StartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.start_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(AbsentListFragment.REQUESTED_KEY) { _, bundle ->
            val randomStudentList =
                bundle.get(AbsentListFragment.BUNDLE_KEY) as ArrayList<Student?>
            view.findViewById<Button>(R.id.selectBtn).setOnClickListener {
                val student = randomStudentList.random()
                view.findViewById<TextView>(R.id.student).text = student?.name.toString()
            }


            view.findViewById<Button>(R.id.markAbsentBtn).setOnClickListener {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(AbsentListFragment.TAG)
                    .replace(R.id.fragmentContainer, AbsentListFragment.newInstance())
                    .commit()
            }
        }
    }
}


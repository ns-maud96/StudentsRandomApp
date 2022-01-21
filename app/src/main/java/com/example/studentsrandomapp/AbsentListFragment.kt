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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AbsentListFragment : Fragment() {

    companion object {
        const val REQUESTED_KEY = "requestedKey"
        const val BUNDLE_KEY = "bundleKey"
        const val TAG = "com.example.studentsRandomApp.AbsentListFragment"
        fun newInstance() = AbsentListFragment()
    }

    var initialStudentList = StudentsList.studentsList.toMutableList() as ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.absent_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        val adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        view.findViewById<Button>(R.id.confirmBtn).setOnClickListener {
            StudentsList.studentsList.removeIf { obj: String? -> obj == "" }
            if (StudentsList.studentsList.isEmpty()) {
                Toast.makeText(context, "Все отсутствуют", Toast.LENGTH_LONG).show()
            } else {
                setFragmentResult(
                    REQUESTED_KEY,
                    bundleOf(BUNDLE_KEY to StudentsList.studentsList)
                )
            }
            parentFragmentManager.popBackStack()
            StudentsList.studentsList = initialStudentList
        }
    }
}

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = StudentsList.studentsList[position]
        holder.checkBox.text = item


        // PROBLEM HERE

        holder.checkBox.setOnClickListener {
            if (holder.checkBox.isChecked) {
                StudentsList.studentsList[position] = ""
            }
        }
    }

    override fun getItemCount(): Int = StudentsList.studentsList.size
}

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var checkBox = view.findViewById<CheckBox>(R.id.checkBox)
}

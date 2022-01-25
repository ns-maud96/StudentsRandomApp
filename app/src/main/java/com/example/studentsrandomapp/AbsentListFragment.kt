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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AbsentListFragment : Fragment() {

    companion object {
        const val REQUESTED_KEY = "requestedKey"
        const val BUNDLE_KEY = "bundleKey"
        const val TAG = "com.example.studentsRandomApp.AbsentListFragment"
        fun newInstance() = AbsentListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.absent_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        var sl: ArrayList<Student?> = viewModel.studentsLiveData.value as ArrayList<Student?>

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        val clickListener: (Int) -> Unit = { position ->
            if (view.findViewById<CheckBox>(R.id.checkBox).isChecked) {
                sl[position] = null
            }
        }


        val adapter = RecyclerViewAdapter(clickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel.studentsLiveData.observe(viewLifecycleOwner) { students ->
            adapter.students = students
        }

        view.findViewById<Button>(R.id.confirmBtn).setOnClickListener {
            sl.removeIf { obj: Student? -> obj == null }
        }

        if (sl.isEmpty()) {
            Toast.makeText(context, "Все отсутствуют", Toast.LENGTH_LONG).show()
        } else {
            setFragmentResult(
                REQUESTED_KEY,
                bundleOf(BUNDLE_KEY to sl)
            )
        }
        parentFragmentManager.popBackStack()
        sl = viewModel.studentsLiveData.value as ArrayList<Student?>
    }
}


class RecyclerViewAdapter(private var clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerViewHolder>() {

    var students: List<Student?> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = students[position]
        holder.checkBox.text = item?.name
        holder.checkBox.isChecked = item?.isAbsent!!

        holder.checkBox.setOnClickListener {
            if (!holder.checkBox.isChecked) {
                holder.checkBox.isChecked = true
                clickListener.invoke(position)
            } else {
                holder.checkBox.isChecked = false
            }
        }
    }

    override fun getItemCount(): Int = students.size
}

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var checkBox = view.findViewById<CheckBox>(R.id.checkBox)
}

package com.example.studentsrandomapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentsViewModel : ViewModel() {

    val studentsLiveData = MutableLiveData<List<Student?>>()

    init {
        studentsLiveData.value = listOf(
            Student("Ярослав", false),
            Student("Егор", false),
            Student("Стас", false),
            Student("Alex", false),
            Student("Саша", false),
            Student("Настя", false),
            Student("Анна", false),
            Student("Марина", false),
            Student("Надежда", false),
            Student("Дарья", false),
            Student("Наташа", false),
            Student("Антон", false),
            Student("Дима", false),
            Student("Андрей", false)
        )
    }
}
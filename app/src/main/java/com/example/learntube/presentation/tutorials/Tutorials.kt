package com.example.learntube.presentation.tutorials

internal class Tutorials {
    fun getTutorials(): List<String> {
        val listOfCourses = mutableListOf<String>()
        listOfCourses.add("Java Courses");
        listOfCourses.add("Python Courses");
        listOfCourses.add("Kotlin Courses");
        listOfCourses.add("Room");
        listOfCourses.add("Git & Github");
        listOfCourses.add("Hilt");
        listOfCourses.add("Dagger");
        listOfCourses.add("Android Development")
        listOfCourses.add("Clean Architecture")
        listOfCourses.add("MVVM & MVP & MVI")
        listOfCourses.add("JavaScript")
        return listOfCourses
    }
}
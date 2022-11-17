package com.example.karo.pages.students

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.components.ProgressBar
import com.example.karo.utils.Response.*

@Composable
fun Students(
    viewModel: StudentsViewModel = hiltViewModel(),
    studentsContent: @Composable (students: Students) -> Unit
) {
    when (val studentsResponse = viewModel.studentsResponse) {
        is Loading -> ProgressBar()
        is Success -> studentsContent(studentsResponse.data)
        is Failure -> print(studentsResponse.e)
    }
}
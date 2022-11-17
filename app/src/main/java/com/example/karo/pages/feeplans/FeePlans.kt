package com.example.karo.pages.feeplans

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.components.ProgressBar
import com.example.karo.utils.Response.*

@Composable
fun FeePlans(
    plansContent: @Composable (plans: FeePlans) -> Unit,
    viewModel: FeePlansViewModel = hiltViewModel()
) {
    when (val feePlansResponse = viewModel.feePlansResponse) {
        is Loading -> ProgressBar()
        is Success -> plansContent(feePlansResponse.data)
        is Failure -> print(feePlansResponse.e)
    }
}
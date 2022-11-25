package com.example.karo.pages.feepayment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.karo.*
import com.example.karo.components.CustomOutlinedSelectField
import com.example.karo.models.Transaction
import com.example.karo.models.TransactionStatus
import com.example.karo.models.TransactionType
import com.example.karo.models.Wallet
import com.example.karo.pages.feepayment.components.TopUpModal
import com.example.karo.pages.feeplans.FeePlans
import com.example.karo.pages.feeplans.FeePlansViewModel
import com.example.karo.pages.transactions.TransactionsViewModel
import com.example.karo.utils.Helpers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeePaymentPage(onNavigate: (route: String) -> Unit, viewModel: FeePaymentViewModel = hiltViewModel()) {
    val bottomDrawerState = rememberBottomDrawerState(viewModel.bottomDrawerValue)
    val scope = rememberCoroutineScope()

    viewModel.getWallet()

    Wallet({ wallet ->
        if (wallet.id == null) {
            viewModel.createWallet()
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp),
                Alignment.Center
            ) { Text("Wallet created. Revisit page.") }
        } else {
            BottomDrawer(
                drawerState = bottomDrawerState,
                drawerContent = {
                    TopUpModal(onTopUp = { wallet ->
                        viewModel.updateWallet(wallet, true)
                        scope.launch { bottomDrawerState.close() }
                    }, wallet = wallet)
                }
            ) {
                Column(Modifier.fillMaxHeight()) {
                    if (wallet.amount != null) {
                        wallet.amount?.let {
                            WalletInfo(
                                balance = it.toDouble(),
                                scope = scope,
                                bottomDrawerState = bottomDrawerState
                            )
                        }
                    } else {
                        WalletInfo(
                            balance = 0.00,
                            scope = scope,
                            bottomDrawerState = bottomDrawerState
                        )
                    }
                    FeePaymentForm(onNavigate, viewModelPayment = viewModel)
                }
            }
        }
    })
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FeePaymentForm(onNavigate: (route: String) -> Unit, viewModelPayment: FeePaymentViewModel) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // fee plan select field
        val feePlanVM: FeePlansViewModel = hiltViewModel()
        val viewModelTransaction: TransactionsViewModel = hiltViewModel()


        feePlanVM.getFeePlans(studentId = viewModelPayment.user!!.uid)
        FeePlans({ plans ->
            var planValue by rememberSaveable { mutableStateOf("") }
            val plan by remember {
                derivedStateOf {
                    plans.first { plan ->
                        "${Helpers.currencyFormat(plan.amount)}/${plan.frequency} for Yr. ${plan.year} Sem. ${plan.semester}" == planValue
                    }
                }
            }
            val isValidPlan by remember { derivedStateOf { planValue.isNotBlank() } }
            val feePlansList: MutableList<String> = mutableListOf()

            plans.forEach {
                feePlansList.add("${Helpers.currencyFormat(it.amount)}/${it.frequency} for Yr. ${it.year} Sem. ${it.semester}")
                /*feePlansList.add(listOf(
                    it.id.toString(),
                    it.amount.toString(),
                    it.year.toString(),
                    it.semester.toString(),
                    it.frequency.toString()).toString()
                )*/
            }

            CustomOutlinedSelectField(
                label = "Fee Plan",
                value = planValue,
                onValueChange = { planValue = it },
                options = feePlansList.toList(),
                showError = !isValidPlan,
                errorMessage = "Plan is required"
            )
            /*
            * planList[0] => id
            * planList[1] => amount
            * planList[2] => year
            * planList[3] => semester
            * planList[4] => frequency
            * */

            ConfirmButton(label = "Confirm Payment") {
                viewModelPayment.updateWallet(
                    wallet = Wallet(
                        id = viewModelPayment.user.uid,
                        amount = plan.amount
                    ), topUp = false
                )
                viewModelTransaction.createTransaction(
                    transaction = Transaction(
                        amount = plan.amount,
                        type = TransactionType.INVOICE,
                        status = TransactionStatus.COMPLETED,
                        description = "Fees for year ${plan.year}, semester ${plan.semester}",
                        date = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("E, MMMM d, yyyy"))
                    )
                )
                /*TODO -> add toast*/
                Helpers.showToast(c = context, message = "Payment Successful")

                onNavigate(Routes.Transactions.name)
            }
        })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WalletInfo(balance: Double, scope: CoroutineScope, bottomDrawerState: BottomDrawerState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Wallet Balance", fontSize = 20.sp)
            Text(
                text = "KES ${"%,.2f".format(balance)}",
                fontSize = 30.sp,
                color = MaterialTheme.colors.primary
            )
        }
        Button({ scope.launch { bottomDrawerState.open() } }) {
            Text("Top Up")
        }
    }

    Divider(
        thickness = 0.5.dp,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}
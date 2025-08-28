package com.lmorda.homework.ui.selectAccount

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import com.lmorda.homework.domain.model.SelectAccount
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.Initial
import com.lmorda.homework.ui.theme.DayAndNightPreview
import com.lmorda.homework.ui.theme.HomeworkTheme
import com.lmorda.homework.ui.theme.sizeSmall
import com.lmorda.homework.ui.theme.sizeXLarge
import com.lmorda.homework.ui.theme.topAppBarColors
import com.lmorda.homework.R

@Composable
fun SelectAccountScreenRoute(
    viewModel: SelectAccountViewModel,
    onBack: () -> Unit,
    onNavigateToExplore: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    SelectAccountScreen(
        state = state,
        push = viewModel::push,
        onBack = onBack,
        onNavigateToExplore = onNavigateToExplore,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectAccountScreen(
    state: State,
    push: (Event) -> Unit,
    onBack: () -> Unit,
    onNavigateToExplore: () -> Unit,
) {
    val accounts = remember {
        listOf(
            SelectAccount("City of Fleetio", "Admin"),
            SelectAccount("Demo Company", "Admin"),
            SelectAccount("Demo FIFO", "Admin"),
            SelectAccount("EPL Advanced (FIFO Sandbox)", "Owner"),
            SelectAccount("EPL Advanced (FIFO)", "Owner"),
            SelectAccount("EPL Advanced (Static Sandbox)", "Owner"),
            SelectAccount("EPL Advanced (Static)", "Owner"),
            SelectAccount("EPL Enterprise", "Owner"),
            SelectAccount("EPL Essential", "Owner"),
            SelectAccount("EPL Parts & Labor", "Owner"),
            SelectAccount("EPL Premium (FIFO)", "Owner")
        )
    }
    var selectedAccount by remember { mutableStateOf<SelectAccount?>(null) }

    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    colors = topAppBarColors(),
                    title = {
                        Text(
                            text = stringResource(id = R.string.select_account_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                modifier = Modifier.size(sizeXLarge),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = MaterialTheme.colorScheme.onSurface,
                                contentDescription = stringResource(R.string.accessibility_back),
                            )
                        }
                    },
                )
                HorizontalDivider(
                    modifier = Modifier.padding(bottom = sizeSmall),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
                )
            }
        },
    ) { paddingValues ->
        when (state) {
            is Initial -> AccountList(
                listState = listState,
                accounts = accounts,
                paddingValues = paddingValues,
                // onAccountSelected = { selectedAccount = it }
                onNavigateToExplore = onNavigateToExplore,
            )
        }
    }
}

@Composable
fun AccountList(
    listState: LazyListState,
    accounts: List<SelectAccount>,
    paddingValues: PaddingValues,
    onNavigateToExplore: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        state = listState,
    ) {
        items(accounts) { account ->
            AccountItem(
                account = account,
                isSelected = false,
                onNavigateToExplore = onNavigateToExplore
            )
        }
    }
}

@Composable
fun AccountItem(
    account: SelectAccount,
    isSelected: Boolean,
    onNavigateToExplore: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToExplore() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onNavigateToExplore() }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = account.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = account.role,
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.Gray
            )
        }
    }
}

@Composable
@DayAndNightPreview
private fun SelectAccountScreenPreview() {
    HomeworkTheme {
        SelectAccountScreen(
            state = Initial,
            push = {},
            onBack = {},
            onNavigateToExplore = {},
        )
    }
}

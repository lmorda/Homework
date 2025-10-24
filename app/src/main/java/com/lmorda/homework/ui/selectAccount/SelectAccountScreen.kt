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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.Event
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.Initial
import com.lmorda.homework.ui.theme.DayAndNightPreview
import com.lmorda.homework.ui.theme.HomeworkTheme
import com.lmorda.homework.ui.theme.sizeSmall
import com.lmorda.homework.ui.theme.sizeXLarge
import com.lmorda.homework.ui.theme.topAppBarColors
import com.lmorda.homework.R
import com.lmorda.homework.domain.model.Account
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.AccountLoadError
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.AccountSelected
import com.lmorda.homework.ui.selectAccount.SelectAccountContract.State.AccountsLoaded
import com.lmorda.homework.ui.shared.HomeworkProgressIndicator
import kotlinx.coroutines.launch

@Composable
fun SelectAccountScreenRoute(
    viewModel: SelectAccountViewModel,
    onBack: () -> Unit,
    onNavigateToExplore: () -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val state by viewModel.state.collectAsState()
    SelectAccountScreen(
        state = state,
        push = viewModel::push,
        onBack = onBack,
        onNavigateToExplore = onNavigateToExplore,
        snackbarHostState = snackbarHostState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectAccountScreen(
    state: State,
    push: (Event) -> Unit,
    onBack: () -> Unit,
    onNavigateToExplore: () -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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
            is Initial -> {}
            is AccountSelected -> onNavigateToExplore()
            is AccountsLoaded -> AccountList(
                listState = listState,
                accounts = state.accounts,
                paddingValues = paddingValues,
                onNavigateToExplore = onNavigateToExplore,
                push = push,
            )
            AccountLoadError -> {} // snackbar
        }
    }
}

@Composable
fun AccountList(
    listState: LazyListState,
    accounts: List<Account>,
    paddingValues: PaddingValues,
    onNavigateToExplore: () -> Unit,
    push: (Event) -> Unit,
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
                onNavigateToExplore = onNavigateToExplore,
                push = push,
            )
        }
    }
}

@Composable
fun AccountItem(
    account: Account,
    isSelected: Boolean,
    onNavigateToExplore: () -> Unit,
    push: (Event) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigateToExplore()
                push(Event.Internal.OnAccountSelected(account.id))
            }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = {
                onNavigateToExplore()
                push(Event.Internal.OnAccountSelected(account.id))
            }
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
                text = account.userType,
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
            snackbarHostState = remember { SnackbarHostState() },
        )
    }
}

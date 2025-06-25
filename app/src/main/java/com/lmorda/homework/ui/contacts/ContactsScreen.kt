package com.lmorda.homework.ui.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.lmorda.homework.R
import com.lmorda.homework.domain.model.Contact
import com.lmorda.homework.domain.model.mockContactsDomainData
import com.lmorda.homework.domain.model.mockContactsDomainDataPage1
import com.lmorda.homework.ui.contacts.ContactsContract.Event
import com.lmorda.homework.ui.contacts.ContactsContract.State
import com.lmorda.homework.ui.shared.HomeworkLoadingError
import com.lmorda.homework.ui.shared.HomeworkNextPageIndicator
import com.lmorda.homework.ui.shared.HomeworkProgressIndicator
import com.lmorda.homework.ui.shared.Utils
import com.lmorda.homework.ui.theme.DayAndNightPreview
import com.lmorda.homework.ui.theme.HomeworkTheme
import com.lmorda.homework.ui.theme.PaginationEffect
import com.lmorda.homework.ui.theme.sizeDefault
import com.lmorda.homework.ui.theme.sizeMedium
import com.lmorda.homework.ui.theme.sizeSmall
import com.lmorda.homework.ui.theme.sizeXLarge
import com.lmorda.homework.ui.theme.topAppBarColors
import java.util.Locale

@Composable
fun ContactsScreenRoute(
    viewModel: ContactsViewModel,
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    ContactsScreen(
        state = state,
        push = viewModel::push,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContactsScreen(
    state: State,
    push: (Event) -> Unit,
    onBack: () -> Unit,
) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    colors = topAppBarColors(),
                    title = {
                        Text(
                            text = stringResource(id = R.string.contacts_title),
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
        PullToRefreshBox(
            isRefreshing = state is State.LoadingRefresh,
            onRefresh = { push(Event.OnRefresh) },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ContactsContent(
                state = state,
                listState = listState,
                push = push,
            )
        }
    }
}

@Composable
private fun ContactsContent(
    state: State,
    push: (Event) -> Unit,
    listState: LazyListState,
) {
    when (state) {
        is State.Initial, State.LoadingRefresh -> HomeworkProgressIndicator()
        is State.LoadingPage -> {
            ContactsList(
                listState = listState,
                contacts = state.contacts,
                isLoadingNextPage = true,
            )
        }

        is State.Loaded -> when {
            state.contacts.isEmpty() -> HomeworkLoadingError(stringResId = R.string.list_empty)
            else -> {
                ContactsList(
                    listState = listState,
                    contacts = state.contacts,
                    isLoadingNextPage = false,
                )
                PaginationEffect(
                    listState = listState,
                    onLoadMore = { push(Event.OnLoadNextPage) },
                )
            }
        }
    }
}

@Composable
private fun ContactsList(
    listState: LazyListState,
    contacts: List<Contact>,
    isLoadingNextPage: Boolean,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = sizeDefault),
        state = listState,
    ) {
        items(contacts) { contact ->
            ContactItem(
                contact = contact,
            )
        }
        if (isLoadingNextPage) {
            item { HomeworkNextPageIndicator() }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier.padding(all = sizeMedium)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(size = dimensionResource(R.dimen.list_item_image)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(contact.defaultImageUrl)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.contact_placeholder),
            contentDescription = stringResource(R.string.accessibility_contact),
        )
        Column(
            modifier = Modifier
                .padding(start = sizeDefault)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = contact.email,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = stringResource(R.string.added) + " " +
                        Utils.formatDateTimeMonthDayYear(
                            dateTime = contact.createdAt,
                            locale = Locale.getDefault(),
                        ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
@DayAndNightPreview
private fun ContactsScreenPreview() {
    HomeworkTheme {
        ContactsScreen(
            state = State.Loaded(
                contacts = mockContactsDomainDataPage1,
                nextCursor = null,
            ),
            push = { },
            onBack = { },
        )
    }
}

@Composable
@DayAndNightPreview
private fun ContactsItemPreview() {
    HomeworkTheme {
        ContactItem(
            contact = mockContactsDomainData[0].contacts[0],
        )
    }
}

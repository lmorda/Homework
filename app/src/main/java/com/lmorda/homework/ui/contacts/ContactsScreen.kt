package com.lmorda.homework.ui.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.Composable
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
import com.lmorda.homework.ui.shared.Utils
import com.lmorda.homework.ui.theme.DayAndNightPreview
import com.lmorda.homework.ui.theme.HomeworkTheme
import com.lmorda.homework.ui.theme.sizeDefault
import com.lmorda.homework.ui.theme.sizeMedium
import com.lmorda.homework.ui.theme.sizeSmall
import com.lmorda.homework.ui.theme.sizeXLarge
import com.lmorda.homework.ui.theme.topAppBarColors
import java.util.Locale

@Composable
fun ContactsScreenRoute(
    onBack: () -> Unit,
) {
    ContactsScreen(
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContactsScreen(
    onBack: () -> Unit,
) {
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
        ContactsList(
            contacts = mockContactsDomainData,
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun ContactsList(
    paddingValues: PaddingValues,
    contacts: List<Contact>,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        state = listState,
    ) {
        items(contacts) { contact ->
            ContactItem(
                contact = contact,
            )
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
                .size(size = dimensionResource(R.dimen.explore_item_image)),
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
            onBack = { },
        )
    }
}

@Composable
@DayAndNightPreview
private fun ContactsItemPreview() {
    HomeworkTheme {
        ContactItem(
            contact = mockContactsDomainData[0],
        )
    }
}

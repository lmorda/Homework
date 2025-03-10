package com.lmorda.homework.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.lmorda.homework.R
import com.lmorda.homework.ui.explore.ExploreContract.Event
import com.lmorda.homework.ui.explore.ExploreContract.Event.OnSearchName
import com.lmorda.homework.ui.theme.DayAndNightPreview
import com.lmorda.homework.ui.theme.HomeworkTheme
import com.lmorda.homework.ui.theme.sizeDefault
import com.lmorda.homework.ui.theme.sizeLarge
import com.lmorda.homework.ui.theme.sizeSmall
import com.lmorda.homework.ui.theme.sizeXLarge
import com.lmorda.homework.ui.theme.topAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExploreTopBar(
    isFiltering: MutableState<Boolean>,
    push: (Event) -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = topAppBarColors(),
        title = {
            when {
                isFiltering.value -> ExploreAppBarFiltering(
                    onSearch = { push(OnSearchName(it)) },
                    onBackClick = {
                        push(OnSearchName(""))
                        isFiltering.value = false
                    },
                )

                else -> ExploreAppBarNotFiltering(
                    onFilterClick = { isFiltering.value = true },
                )
            }
        },
    )
}

@Composable
internal fun ExploreAppBarNotFiltering(
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = sizeDefault),
    ) {
        Text(
            modifier = Modifier
                .align(CenterVertically),
            text = stringResource(id = R.string.explore_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.align(CenterVertically),
            onClick = onFilterClick,
        ) {
            Icon(
                modifier = Modifier.size(sizeXLarge),
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.accessibility_search),
            )
        }
    }
}

@Composable
fun ExploreAppBarFiltering(
    onBackClick: () -> Unit,
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = sizeSmall),
    ) {
        IconButton(
            modifier = Modifier.align(CenterVertically),
            onClick = onBackClick,
        ) {
            Icon(
                modifier = Modifier.size(sizeLarge),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.accessibility_back),
            )
        }

        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                onSearch(it)
            },
            modifier = Modifier
                .align(CenterVertically)
                .background(color = MaterialTheme.colorScheme.background)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        keyboardController?.show()
                    }
                },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            cursorBrush = SolidColor(value = MaterialTheme.colorScheme.onBackground),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(query)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            decorationBox = { innerTextField ->
                Box(Modifier.fillMaxWidth()) {
                    if (query.isEmpty()) {
                        Text(
                            text = stringResource(R.string.explore_search_hint),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
@DayAndNightPreview
fun ExploreAppBarNotFilteringPreview() {
    HomeworkTheme {
        Column(modifier = Modifier.height(64.dp)) {
            ExploreAppBarNotFiltering(
                onFilterClick = {},
            )
        }
    }
}

@Composable
@DayAndNightPreview
fun ExploreAppBarFilteringPreview() {
    HomeworkTheme {
        Column(modifier = Modifier.height(64.dp)) {
            ExploreAppBarFiltering(
                onBackClick = {},
                onSearch = {},
            )
        }
    }
}

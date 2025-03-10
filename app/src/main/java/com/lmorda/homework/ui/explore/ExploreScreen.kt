package com.lmorda.homework.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.lmorda.homework.R
import com.lmorda.homework.domain.model.Vehicle
import com.lmorda.homework.domain.model.mockDomainData
import com.lmorda.homework.ui.explore.ExploreContract.Event
import com.lmorda.homework.ui.explore.ExploreContract.Event.OnLoadFirstPage
import com.lmorda.homework.ui.explore.ExploreContract.State
import com.lmorda.homework.ui.shared.HomeworkLoadingError
import com.lmorda.homework.ui.shared.HomeworkProgressIndicator
import com.lmorda.homework.ui.theme.DayAndNightPreview
import com.lmorda.homework.ui.theme.HomeworkTheme
import com.lmorda.homework.ui.theme.PaginationEffect
import com.lmorda.homework.ui.theme.sizeDefault
import com.lmorda.homework.ui.theme.sizeMedium

@Composable
fun ExploreScreenRoute(
    viewModel: ExploreViewModel,
    onNavigateToDetails: (Long) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.push(OnLoadFirstPage)
    }
    val state = requireNotNull(viewModel.state.observeAsState().value)
    ExploreScreen(
        state = state,
        push = viewModel::push,
        onNavigateToDetails = onNavigateToDetails,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExploreScreen(
    state: State,
    push: (Event) -> Unit,
    onNavigateToDetails: (Long) -> Unit,
) {
    val listState = rememberLazyListState()
    val isFiltering = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            // TODO: Add sorting pills
            ExploreTopBar(isFiltering = isFiltering, push = push)
        },
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state is State.LoadingRefresh,
            onRefresh = { push(Event.OnRefresh) },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ExploreContent(
                state = state,
                listState = listState,
                onNavigateToDetails = onNavigateToDetails,
                push = push,
            )
        }
    }
}

@Composable
private fun ExploreContent(
    state: State,
    listState: LazyListState,
    onNavigateToDetails: (Long) -> Unit,
    push: (Event) -> Unit
) {
    when (state) {
        is State.Initial -> {}
        is State.LoadingFirstPage, State.LoadingRefresh -> HomeworkProgressIndicator()
        is State.LoadingPage -> {
            ExploreList(
                listState = listState,
                vehicles = state.vehicles,
                isLoadingNextPage = true,
                onNavigateToDetails = onNavigateToDetails,
            )
        }

        is State.Loaded -> when {
            state.vehicles.isEmpty() -> HomeworkLoadingError(stringResId = R.string.list_empty)
            else -> {
                ExploreList(
                    listState = listState,
                    vehicles = state.vehicles,
                    isLoadingNextPage = false,
                    onNavigateToDetails = onNavigateToDetails,
                )
                PaginationEffect(
                    listState = listState,
                    onLoadMore = { push(Event.OnLoadNextPage) },
                )
            }
        }

        is State.LoadError -> HomeworkLoadingError(stringResId = R.string.list_error)
    }
}

@Composable
private fun ExploreList(
    listState: LazyListState,
    vehicles: List<Vehicle>,
    isLoadingNextPage: Boolean,
    onNavigateToDetails: (Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = sizeDefault),
        state = listState,
    ) {
        items(vehicles) { vehicle ->
            ExploreItem(
                vehicle = vehicle,
                onNavigateToDetails = onNavigateToDetails,
            )
        }
        if (isLoadingNextPage) {
            item { ExploreNextPageIndicator() }
        }
    }

}

@Composable
private fun ExploreItem(vehicle: Vehicle, onNavigateToDetails: (Long) -> Unit) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .padding(all = sizeMedium)
            .clickable {
                onNavigateToDetails(vehicle.id)
            },
    ) {
        AsyncImage(
            modifier = Modifier
                .size(size = dimensionResource(R.dimen.explore_item_image))
                .clip(shape = CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(vehicle.defaultImageUrlSmall)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.placeholder),
            contentDescription = stringResource(R.string.accessibility_vehicle),
        )
        Column(
            modifier = Modifier
                .padding(start = sizeDefault)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            Text(
                text = vehicle.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            val makeModel = "${vehicle.make} ${vehicle.model}".trimStart()
            makeModel.takeIf { it.isNotBlank() }?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = vehicle.vehicleTypeName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun ExploreNextPageIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(sizeMedium),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(dimensionResource(R.dimen.explore_next_page_indicator)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
@DayAndNightPreview
private fun ExploreScreenPreview() {
    HomeworkTheme {
        ExploreScreen(
            state = State.Loaded(
                vehicles = mockDomainData.records,
                nextCursor = null,
                nameLike = null,
            ),
            push = {},
            onNavigateToDetails = {},
        )
    }
}

@Composable
@DayAndNightPreview
private fun ExploreItemPreview() {
    HomeworkTheme {
        ExploreItem(
            vehicle = mockDomainData.records[0],
            onNavigateToDetails = {},
        )
    }
}

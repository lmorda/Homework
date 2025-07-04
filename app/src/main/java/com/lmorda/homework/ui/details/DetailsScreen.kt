package com.lmorda.homework.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.lmorda.homework.R
import com.lmorda.homework.domain.model.Driver
import com.lmorda.homework.domain.model.Geolocation
import com.lmorda.homework.domain.model.Vehicle
import com.lmorda.homework.domain.model.mockDomainData
import com.lmorda.homework.ui.details.DetailsContract.Event.OnLoadDetails
import com.lmorda.homework.ui.details.DetailsContract.State
import com.lmorda.homework.ui.shared.HomeworkLoadingError
import com.lmorda.homework.ui.shared.HomeworkProgressIndicator
import com.lmorda.homework.ui.shared.MAP_DEFAULT_ZOOM
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
fun DetailsScreenRoute(
    viewModel: DetailsViewModel,
    onBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.push(OnLoadDetails)
    }
    val state by viewModel.state.collectAsState()
    DetailsScreen(
        state = state,
        onBack = onBack,
    )
}

@Composable
internal fun DetailsScreen(
    state: State,
    onBack: () -> Unit,
) {
    when (state) {
        State.Initial -> {}
        is State.Loading -> DetailsScaffold(
            screenContent = {
                HomeworkProgressIndicator()
            },
            onBack = onBack,
        )

        is State.Loaded -> DetailsScaffold(
            titleContent = {
                Text(
                    text = state.vehicle.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            },
            screenContent = {
                DetailsItems(state.vehicle)
            },
            onBack = onBack,
        )

        is State.LoadError -> DetailsScaffold(
            onBack = onBack,
            screenContent = {
                HomeworkLoadingError(stringResId = R.string.details_error)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScaffold(
    titleContent: @Composable () -> Unit = {},
    screenContent: @Composable () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(),
                title = titleContent,
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
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = contentPadding)
                .fillMaxSize()
        ) {
            screenContent()
        }
    }
}

@Composable
private fun DetailsMap(geolocation: Geolocation, date: String) {
    val location = LatLng(
        geolocation.latitude,
        geolocation.longitude,
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, MAP_DEFAULT_ZOOM)
    }
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = sizeDefault)
            .height(dimensionResource(R.dimen.details_map)),
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = MarkerState(position = location),
            title = location.toString(),
            snippet = Utils.formatDateTimeMonthDayYearTime(
                dateTime = date,
                locale = Locale.getDefault(),
            )
        )
    }
}

@Composable
private fun DetailsItems(vehicle: Vehicle) {
    Column {
        vehicle.currentLocationEntry?.takeIf { it.geolocation.latitude != 0.0 }?.let {
            DetailsMap(it.geolocation, it.date)
        } ?: Spacer(modifier = Modifier.height(sizeDefault))
        DetailsItem(
            label = stringResource(R.string.label_primary_meter),
            value = "${vehicle.primaryMeterValue} ${vehicle.primaryMeterUnit}",
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = sizeDefault))
        DetailsItem(
            label = stringResource(R.string.label_secondary_meter),
            value = "${vehicle.secondaryMeterValue} ${vehicle.secondaryMeterUnit}",
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = sizeDefault))
        DetailsItem(
            label = stringResource(R.string.label_vehicle_status),
            value = vehicle.vehicleStatusName,
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = sizeDefault))
        DetailsItem(
            label = stringResource(R.string.label_driver),
            value = vehicle.driver?.fullName.orEmpty(),
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = sizeDefault))
        DetailsItem(
            label = stringResource(R.string.label_vin),
            value = vehicle.vin,
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = sizeDefault))
        DetailsItem(
            label = stringResource(R.string.label_license_plate),
            value = vehicle.licensePlate,
        )
    }
}

@Composable
private fun DetailsItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = sizeDefault, end = sizeMedium)
    ) {
        Text(
            text = label.trim(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(start = sizeSmall),
            text = value.trim(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@DayAndNightPreview
@Composable
private fun DetailsLoadedScreenPreview() {
    HomeworkTheme {
        DetailsScreen(
            state = State.Loaded(
                vehicle = mockDomainData.records[0]
                    .copy(
                        driver = Driver(fullName = "Sorry UI designer, I have a very long name.")
                    )
            ),
            onBack = {},
        )
    }
}

@DayAndNightPreview
@Composable
private fun DetailsLoadedScreenNoMapPreview() {
    HomeworkTheme {
        DetailsScreen(
            state = State.Loaded(
                vehicle = mockDomainData.records[0]
                    .copy(currentLocationEntry = null)
            ),
            onBack = {},
        )
    }
}

@DayAndNightPreview
@Composable
private fun DetailsErrorScreenPreview() {
    HomeworkTheme {
        DetailsScreen(
            state = State.LoadError(errorMessage = null),
            onBack = {},
        )
    }
}

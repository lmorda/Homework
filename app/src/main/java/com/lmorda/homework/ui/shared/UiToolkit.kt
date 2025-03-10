package com.lmorda.homework.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lmorda.homework.R
import com.lmorda.homework.ui.theme.sizeDefault

@Composable
internal fun HomeworkLoadingError(stringResId: Int) {
    Box(modifier = Modifier.padding(sizeDefault)) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.loading_error),
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
        )
        Text(
            modifier = Modifier.padding(start = sizeDefault),
            text = stringResource(id = stringResId),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}

@Composable
fun HomeworkProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.loading),
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}

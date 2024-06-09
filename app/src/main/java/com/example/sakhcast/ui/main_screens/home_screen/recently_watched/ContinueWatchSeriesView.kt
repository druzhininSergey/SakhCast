package com.example.sakhcast.ui.main_screens.home_screen.recently_watched

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sakhcast.Dimens
import com.example.sakhcast.model.SeriesRecent

@Preview(showBackground = true)
@Composable
fun Preview1() {
//    ContinueWatchSeriesView(seriesCard = Samples.getOneSeries())
}

@Preview(showBackground = true)
@Composable
fun ContinueWatchSeriesView(seriesCard: SeriesRecent) {
    val context = LocalContext.current
    val imageUrl = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        seriesCard.data.backdropAlt + ".avif"
    } else {
        seriesCard.data.backdropAlt + ".webp"
    }
    val backdropPainter: Painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = imageUrl)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    //            placeholder(R.drawable.placeholder) // Укажите ресурс-заполнитель
                    //            error(R.drawable.error) // Укажите ресурс ошибки
                }).build()
        )
    Card(
        modifier = Modifier
            .height(234.dp)
            .width(416.dp)
            .padding(Dimens.mainPadding)
    ) {
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = backdropPainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                Modifier
                    .padding(start = 6.dp, bottom = 6.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = seriesCard.data.name,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = "Сезон " + seriesCard.data.userLastSeason + " эпизод " + seriesCard.data.userLastEp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
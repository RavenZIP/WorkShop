package com.ravenzip.workshop.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * [HorizontalPagerIndicator] - Горизонтальный индикатор пейджера
 *
 * @param pagerState состояние составного элемента
 * @param indicatorColor цвет неактивного индикатора
 * @param selectedIndicatorColor цвет активного элемента
 * @param height высота
 * @param width ширина
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    indicatorColor: Color,
    selectedIndicatorColor: Color,
    height: Int = 10,
    width: Int = 10,
) {
    Row(
        Modifier.fillMaxWidth().padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) selectedIndicatorColor else indicatorColor

            Box(
                modifier =
                    Modifier.padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(width.dp, height.dp)
            )
        }
    }
}

/**
 * [VerticalPagerIndicator] - Вертикальный индикатор пейджера
 *
 * @param pagerState состояние составного элемента
 * @param indicatorColor цвет неактивного индикатора
 * @param selectedIndicatorColor цвет активного элемента
 * @param height высота
 * @param width ширина
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPagerIndicator(
    pagerState: PagerState,
    indicatorColor: Color,
    selectedIndicatorColor: Color,
    height: Int = 10,
    width: Int = 10,
) {
    Column(
        Modifier.fillMaxWidth().padding(start = 20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) selectedIndicatorColor else indicatorColor

            Box(
                modifier =
                    Modifier.padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(width.dp, height.dp)
            )
        }
    }
}

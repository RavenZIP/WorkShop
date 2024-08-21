package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * [VerticalGrid] - Вертикальная сетка
 *
 * @param width ширина сетки
 * @param items список элементов
 * @param columnsCount количество колонок
 * @param spacing отступы между элементами
 * @param oneSizeForEveryone одинаковый размер для всех элементов, вне зависимости от количества
 *   элементов в списке
 * @param content внутренее содержимое сетки
 */
@Composable
fun <T> VerticalGrid(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    items: List<T>,
    columnsCount: Int = 2,
    spacing: Dp = 10.dp,
    oneSizeForEveryone: Boolean = false,
    content: @Composable (modifier: Modifier, item: T) -> Unit,
) {
    val rowsCount = items.size / columnsCount + (if (items.size % columnsCount > 0) 1 else 0)
    Column(modifier = Modifier.fillMaxWidth(width)) {
        repeat(rowsCount) { indexOfElementRow ->
            Row {
                repeat(columnsCount) { indexOfElementColumn ->
                    val elementNum = indexOfElementColumn + 1 + indexOfElementRow * columnsCount
                    val isOutOfRange = elementNum > items.size

                    if (!isOutOfRange) content(Modifier.weight(1f), items[elementNum - 1])
                    else if (oneSizeForEveryone) Box(modifier = Modifier.weight(1f))

                    if (
                        indexOfElementColumn != columnsCount - 1 &&
                            (elementNum != items.size && !isOutOfRange || oneSizeForEveryone)
                    ) {
                        Spacer(modifier = Modifier.width(spacing))
                    }
                }
            }

            if (indexOfElementRow != rowsCount - 1) Spacer(modifier = Modifier.height(spacing))
        }
    }
}

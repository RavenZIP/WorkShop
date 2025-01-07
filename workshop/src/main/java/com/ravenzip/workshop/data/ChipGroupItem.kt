package com.ravenzip.workshop.data

import com.ravenzip.workshop.data.icon.Icon
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental data class ChipGroupItem<T>(val value: T, val icon: Icon)

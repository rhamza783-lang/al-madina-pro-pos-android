package com.almadina.pos.util

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(amount: Double): String {
    return NumberFormat.getCurrencyInstance(Locale("en", "PK")).format(amount)
}

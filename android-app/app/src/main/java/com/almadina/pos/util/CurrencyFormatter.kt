package com.almadina.pos.util

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(amount: Double): String {
    // Creates a formatter for Pakistani Rupee (PKR), e.g., "Rs 1,200.00"
    return NumberFormat.getCurrencyInstance(Locale("en", "PK")).format(amount)
}

package com.almadina.pos.printing

import com.almadina.pos.model.Order
import com.almadina.pos.ui.dashboard.formatCurrency
import com.dantsu.escposprinter.EscPosPrinter
import java.text.SimpleDateFormat
import java.util.*

class ThermalPrintJob(private val printer: EscPosPrinter) {

    fun printReceipt(order: Order) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val receiptText = buildString {
            appendLine("[C]<b><font size='big'>AL-MADINA RESTAURANT</font></b>")
            appendLine("[C]================================")
            appendLine("[L]<b>Invoice:</b> ${order.invoiceNumber}")
            appendLine("[L]<b>Table:</b> ${order.tableNumber}")
            appendLine("[L]<b>Date:</b> ${dateFormat.format(Date(order.createdAt))}")
            appendLine("[C]--------------------------------")
            order.items.forEach { item ->
                appendLine("[L]<b>${item.quantity}x ${item.itemName}</b>[R]${formatCurrency(item.totalPrice)}")
            }
            appendLine("[C]--------------------------------")
            appendLine("[L]<b>Subtotal:</b>[R]${formatCurrency(order.subtotal)}")
            appendLine("[L]<b>Total:</b>[R]<b><font size='tall'>${formatCurrency(order.totalAmount)}</font></b>")
            appendLine("[C]================================")
            appendLine("[C]Thank you for your visit!")
        }
        printer.printFormattedTextAndCut(receiptText)
    }

    fun printKitchenTicket(order: Order) {
        val ticketText = buildString {
            appendLine("[C]<b><font size='big'>KOT - Table ${order.tableNumber}</font></b>")
            appendLine("[C]<b><font size='normal'>${order.invoiceNumber}</font></b>")
            appendLine("[C]================================")
            order.items.forEach { item ->
                appendLine("[L]<b><font size='tall'>${item.quantity}x ${item.itemName}</font></b>")
                if (item.notes?.isNotBlank() == true) {
                    appendLine("[L]  <font size='normal'>Note: ${item.notes}</font>")
                }
            }
            appendLine("[C]--------------------------------")
        }
        printer.printFormattedTextAndCut(ticketText)
    }
}

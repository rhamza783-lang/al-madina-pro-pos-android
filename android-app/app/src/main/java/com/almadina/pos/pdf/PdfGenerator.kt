package com.almadina.pos.pdf

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import com.almadina.pos.model.Order
import com.almadina.pos.ui.dashboard.formatCurrency
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PdfGenerator(private val context: Context) {

    fun generateInvoicePdf(order: Order): File? {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        var yPosition = 60f

        // Header
        paint.textAlign = Paint.Align.CENTER
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 20f
        canvas.drawText("AL-MADINA RESTAURANT", pageInfo.pageWidth / 2f, yPosition, paint)
        
        yPosition += 20f
        paint.textSize = 14f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        canvas.drawText("Invoice #${order.invoiceNumber}", pageInfo.pageWidth / 2f, yPosition, paint)
        
        // Details
        yPosition += 40f
        paint.textAlign = Paint.Align.LEFT
        paint.textSize = 12f
        canvas.drawText("Table: ${order.tableNumber}", 40f, yPosition, paint)
        
        paint.textAlign = Paint.Align.RIGHT
        val dateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        canvas.drawText("Date: ${dateFormat.format(Date(order.createdAt))}", (pageInfo.pageWidth - 40f), yPosition, paint)

        // Table Header
        yPosition += 40f
        paint.textAlign = Paint.Align.LEFT
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText("Item", 40f, yPosition, paint)
        canvas.drawText("Qty", 350f, yPosition, paint)
        canvas.drawText("Price", 420f, yPosition, paint)
        canvas.drawText("Total", 500f, yPosition, paint)
        yPosition += 5f
        canvas.drawLine(40f, yPosition, pageInfo.pageWidth - 40f, yPosition, paint)

        // Table Rows
        yPosition += 20f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        for (item in order.items) {
            canvas.drawText(item.itemName, 40f, yPosition, paint)
            canvas.drawText(item.quantity.toString(), 350f, yPosition, paint)
            canvas.drawText(formatCurrency(item.unitPrice), 420f, yPosition, paint)
            canvas.drawText(formatCurrency(item.totalPrice), 500f, yPosition, paint)
            yPosition += 20f
        }
        
        // Total
        yPosition += 20f
        canvas.drawLine(350f, yPosition, pageInfo.pageWidth - 40f, yPosition, paint)
        yPosition += 25f
        paint.textAlign = Paint.Align.RIGHT
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        paint.textSize = 16f
        canvas.drawText("TOTAL: ${formatCurrency(order.totalAmount)}", (pageInfo.pageWidth - 40f), yPosition, paint)

        pdfDocument.finishPage(page)

        // Save the file
        val file = File(context.getExternalFilesDir(null), "Invoice_${order.invoiceNumber}.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            pdfDocument.close()
            return file
        } catch (e: Exception) {
            e.printStackTrace()
            pdfDocument.close()
            return null
        }
    }
}

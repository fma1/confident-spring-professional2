package com.marcobehler

import java.util.{List => JList}
import java.util.concurrent.CopyOnWriteArrayList

class InvoiceService {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  def findAll(): JList[Invoice] = {
    invoices
  }

  def create(userId: String, amount: Int): Invoice = {
    // TODO: real pdf creation and storing it on network server
    val invoice = Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf")
    invoices.add(invoice)
    invoice
  }
}

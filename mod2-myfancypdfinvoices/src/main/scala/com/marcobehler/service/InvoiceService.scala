package com.marcobehler.service

import com.marcobehler.model.Invoice
import org.springframework.stereotype.Component

import java.util.concurrent.CopyOnWriteArrayList
import java.util.{List => JList}

@Component
class InvoiceService(userService: UserService) {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  def findAll(): JList[Invoice] = {
    invoices
  }

  def create(userId: String, amount: Int): Invoice = {
    val user = userService.findById(userId)
    if (user == null) {
      throw new IllegalStateException()
    }

    // TODO: real pdf creation and storing it on network server
    val invoice = Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf")
    invoices.add(invoice)
    invoice
  }

  def getUserService: UserService = userService
}

package com.marcobehler.service

import com.marcobehler.model.Invoice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util.concurrent.CopyOnWriteArrayList
import java.util.{List => JList}
import scala.beans.BeanProperty

@Component
class InvoiceService {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  @Autowired
  @BeanProperty
  var userService: UserService = _

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
}

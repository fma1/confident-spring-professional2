package com.marcobehler.service

import com.marcobehler.model.Invoice
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.util.concurrent.CopyOnWriteArrayList
import java.util.{List => JList}
import javax.annotation.{PostConstruct, PreDestroy}

@Component
class InvoiceService(userService: UserService, @Value("${cdn.url}") cdnUrl: String) {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  @PostConstruct
  def init(): Unit = {
    println("Fetching PDF Template from S3...")
    // TODO download from s3 and save locally
  }

  @PreDestroy
  def shutdown(): Unit = {
    println("Deleting downloaded templates...")
    // TODO actual deletion of PDFs
  }

  def findAll(): JList[Invoice] = {
    invoices
  }

  def create(userId: String, amount: Int): Invoice = {
    val user = userService.findById(userId)
    if (user == null) {
      throw new IllegalStateException()
    }

    // TODO: real pdf creation and storing it on network server
    val invoice = Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf")
    invoices.add(invoice)
    invoice
  }

  def getUserService: UserService = userService
}

package com.marcobehler.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Service
@Profile(Array("dev"))
class DummyInvoiceServiceLoader(invoiceService: InvoiceService) {
  @PostConstruct
  def setup(): Unit = {
    println("Creating dev invoices...")
    invoiceService.create("someUserId", 50)
    invoiceService.create("someOtherUserId", 100)
  }
}

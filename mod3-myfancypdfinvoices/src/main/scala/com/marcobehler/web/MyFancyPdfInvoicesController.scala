package com.marcobehler.web

import com.marcobehler.model.Invoice
import com.marcobehler.service.InvoiceService
import org.springframework.web.bind.annotation.{GetMapping, ResponseBody, RestController}

import java.util.{List => JList}

@RestController
class MyFancyPdfInvoicesController(invoiceService: InvoiceService) {

  @GetMapping(Array("/invoices"))
  def invoices(): JList[Invoice] = {
    invoiceService.findAll()
  }
}

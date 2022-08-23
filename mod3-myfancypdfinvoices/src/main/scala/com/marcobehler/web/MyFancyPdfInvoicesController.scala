package com.marcobehler.web

import com.marcobehler.dto.InvoiceDto
import com.marcobehler.model.Invoice
import com.marcobehler.service.InvoiceService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestBody, RequestParam, ResponseBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid
import javax.validation.constraints.{Max, Min, NotBlank}

@RestController
@Validated
class MyFancyPdfInvoicesController(invoiceService: InvoiceService) {
  @GetMapping(Array("/invoices"))
  def invoices(): JList[Invoice] = {
    invoiceService.findAll()
  }

  @PostMapping(Array("/invoices"))
  def createInvoice(@RequestBody @Valid invoiceDto: InvoiceDto): Invoice = {
    invoiceService.create(invoiceDto.userId, invoiceDto.amount)
  }

  @PostMapping(Array("/invoicesRP"))
  def createInvoiceRequestParam(@RequestParam("user_id") @NotBlank userId: String, @RequestParam @Min(10) @Max(50) amount: Int): Invoice = {
    invoiceService.create(userId, amount)
  }

  @PostMapping(Array("/invoices/{userId}/{amount}"))
  def createInvoicePathVariable(@PathVariable userId: String, @PathVariable amount: Int): Invoice = {
    invoiceService.create(userId, amount)
  }
}

package com.marcobehler.web

import com.marcobehler.dto.InvoiceDto
import com.marcobehler.model.Invoice
import com.marcobehler.service.InvoiceService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation._

import java.lang.{Iterable => JIterable}
import javax.validation.Valid
import javax.validation.constraints.{Max, Min, NotBlank}

@RestController
@Validated
class InvoicesController(invoiceService: InvoiceService) {
  @GetMapping(Array("/invoices"))
  def invoices(): JIterable[Invoice] = {
    invoiceService.findAll()
  }

  @GetMapping(Array("/invoices/user/{userId}"))
  def invoicesByUserId(@PathVariable userId: String): JIterable[Invoice] = {
    invoiceService.findById(userId)
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

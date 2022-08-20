package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.service.InvoiceService

object Application {
  val invoiceService = new InvoiceService()
  val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}

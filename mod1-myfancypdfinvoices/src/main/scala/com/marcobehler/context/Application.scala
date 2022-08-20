package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.service.{InvoiceService, UserService}

object Application {
  val userService = new UserService()
  val invoiceService = new InvoiceService(userService)
  val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}

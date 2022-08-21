package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.service.{InvoiceService, UserService}
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class MyFancyPdfInvoicesApplicationConfiguration {
  @Bean
  def userService = new UserService()
  @Bean
  def invoiceService(userService: UserService) = new InvoiceService(userService)
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}

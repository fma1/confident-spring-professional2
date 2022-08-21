package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.MyFancyPdfInvoicesApplicationLauncher2
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[MyFancyPdfInvoicesApplicationLauncher2]))
class MyFancyPdfInvoicesApplicationConfiguration {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}

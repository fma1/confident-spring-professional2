package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.BankApplicationLauncher2
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[BankApplicationLauncher2]))
class BankApplicationConfiguration {
  @Bean
  def objectMapper(): ObjectMapper = new ObjectMapper()
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule())
}

package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.MyFancyPdfInvoicesApplicationLauncher2
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[MyFancyPdfInvoicesApplicationLauncher2]))
@PropertySource(value = Array("classpath:/application.properties"))
@PropertySource(value = Array("classpath:/application-${spring.profiles.active}.properties")
  , ignoreResourceNotFound = true)
class MyFancyPdfInvoicesApplicationConfiguration {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}

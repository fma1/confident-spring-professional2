package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.BankApplicationLauncher3
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurer}

import java.util.{List => JList}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[BankApplicationLauncher3]))
@PropertySource(value = Array("classpath:/application.properties"))
@EnableWebMvc
class BankApplicationConfiguration extends WebMvcConfigurer {
  @Bean
  def objectMapper(): ObjectMapper = new ObjectMapper()
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule())

  @Bean
  def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
    val mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter()
    mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper())
    mappingJackson2HttpMessageConverter
  }

  override def configureMessageConverters(converters: JList[HttpMessageConverter[_]]): Unit = {
    converters.add(mappingJackson2HttpMessageConverter())
  }
}

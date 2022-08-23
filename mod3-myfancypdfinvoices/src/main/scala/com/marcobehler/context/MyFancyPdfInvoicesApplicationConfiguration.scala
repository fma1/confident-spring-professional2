package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.MyFancyPdfInvoicesApplicationLauncher3
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.{ContentNegotiationConfigurer, EnableWebMvc, WebMvcConfigurer}

import java.util.{List => JList}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[MyFancyPdfInvoicesApplicationLauncher3]))
@PropertySource(value = Array("classpath:/application.properties"))
@PropertySource(value = Array("classpath:/application-${spring.profiles.active}.properties")
  , ignoreResourceNotFound = true)
@EnableWebMvc
class MyFancyPdfInvoicesApplicationConfiguration extends WebMvcConfigurer {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  @Bean
  def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
    val mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper)
    mappingJackson2HttpMessageConverter
    new MappingJackson2HttpMessageConverter(objectMapper)
  }

  override def configureMessageConverters(converters: JList[HttpMessageConverter[_]]): Unit = {
    converters.add(mappingJackson2HttpMessageConverter())
  }

  override def configureContentNegotiation(configurer: ContentNegotiationConfigurer): Unit = {
    configurer.defaultContentType(MediaType.APPLICATION_JSON)
  }
}

package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.MyFancyPdfInvoicesApplicationLauncher5
import org.h2.jdbcx.JdbcDataSource
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor
import org.springframework.web.servlet.config.annotation.{ContentNegotiationConfigurer, EnableWebMvc, WebMvcConfigurer}
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

import java.util.{List => JList}
import javax.sql.DataSource

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[MyFancyPdfInvoicesApplicationLauncher5]))
@PropertySource(value = Array("classpath:/application.properties"))
@PropertySource(value = Array("classpath:/application-${spring.profiles.active}.properties")
  , ignoreResourceNotFound = true)
@EnableWebMvc
class ApplicationConfiguration extends WebMvcConfigurer {
  @Bean
  def methodValidationPostProcessor() = new MethodValidationPostProcessor()

  @Bean
  def dataSource(): DataSource = {
    val dataSource = new JdbcDataSource()
    dataSource.setURL("jdbc:h2:~/myFirstH2Database;INIT=RUNSCRIPT FROM 'classpath:schema.sql'")
    dataSource.setUser("sa")
    dataSource.setPassword("sa")
    dataSource
  }

  @Bean
  def jdbcTemplate(): JdbcTemplate = {
    new JdbcTemplate(dataSource())
  }

  @Bean
  def objectMapper(): ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  @Bean
  def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
    val mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper())
    mappingJackson2HttpMessageConverter
  }

  @Bean
  def viewResolver(): ThymeleafViewResolver = {
    val viewResolver = new ThymeleafViewResolver()
    viewResolver.setTemplateEngine(templateEngine())
    viewResolver
  }

  @Bean
  def templateEngine(): SpringTemplateEngine = {
    val templateEngine = new SpringTemplateEngine()
    templateEngine.setTemplateResolver(templateResolver())
    templateEngine
  }

  @Bean
  def templateResolver(): SpringResourceTemplateResolver = {
    val templateResolver = new SpringResourceTemplateResolver()
    templateResolver.setPrefix("classpath:/templates/")
    templateResolver.setCacheable(false)
    templateResolver
  }

  override def configureMessageConverters(converters: JList[HttpMessageConverter[_]]): Unit = {
    converters.add(mappingJackson2HttpMessageConverter())
  }

  override def configureContentNegotiation(configurer: ContentNegotiationConfigurer): Unit = {
    configurer.defaultContentType(MediaType.APPLICATION_JSON)
  }
}

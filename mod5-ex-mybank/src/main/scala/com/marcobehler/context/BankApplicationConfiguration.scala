package com.marcobehler.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.BankApplicationLauncher5
import com.marcobehler.web.forms.TransactionForm
import org.h2.jdbcx.JdbcDataSource
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurer}
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

import java.util.{List => JList}
import javax.sql.DataSource

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[BankApplicationLauncher5]))
@PropertySource(value = Array("classpath:/application.properties"))
@EnableWebMvc
@EnableTransactionManagement
class BankApplicationConfiguration extends WebMvcConfigurer {
  @Bean
  def objectMapper(): ObjectMapper = new ObjectMapper()
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule())

  @Bean
  def dataSource(): DataSource = {
    val dataSource = new JdbcDataSource()
    dataSource.setURL("jdbc:h2:~/BankH2Database;INIT=RUNSCRIPT FROM 'classpath:schema.sql'")
    dataSource.setUser("sa")
    dataSource.setPassword("sa")
    dataSource
  }

  @Bean
  def jdbcTemplate(): JdbcTemplate = {
    new JdbcTemplate(dataSource())
  }

  @Bean
  def platformTransactionManager: PlatformTransactionManager = {
    new DataSourceTransactionManager(dataSource())
  }

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

  override def addFormatters(registry: FormatterRegistry): Unit = {
    val stringToBigDecimalConverter = new Converter[String, BigDecimal] {
      override def convert(source: String): BigDecimal = {
        BigDecimal(source)
      }
    }
    registry.addConverter(stringToBigDecimalConverter)
  }

  override def configureMessageConverters(converters: JList[HttpMessageConverter[_]]): Unit = {
    converters.add(mappingJackson2HttpMessageConverter())
  }
}

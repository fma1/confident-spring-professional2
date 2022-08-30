package com.marcobehler

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

object BankSpringBootApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[BankSpringBootApplication], args: _*)
  }

  @Configuration
  class MyConfig extends WebMvcConfigurer {
    override def addFormatters(registry: FormatterRegistry): Unit = {
      val stringToBigDecimalConverter = new Converter[String, BigDecimal] {
        override def convert(source: String): BigDecimal = {
          BigDecimal(source)
        }
      }
      registry.addConverter(stringToBigDecimalConverter)
    }
  }
}

@SpringBootApplication
class BankSpringBootApplication

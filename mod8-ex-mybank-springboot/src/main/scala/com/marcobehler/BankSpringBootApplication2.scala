package com.marcobehler

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

import java.math.{BigDecimal => JBigDecimal}

object BankSpringBootApplication2 {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[BankSpringBootApplication2], args: _*)
  }

  @Configuration
  class MyConfig extends WebMvcConfigurer {
    override def addFormatters(registry: FormatterRegistry): Unit = {
      val stringToJBigDecimalConverter = new Converter[String, JBigDecimal] {
        override def convert(source: String): JBigDecimal = {
          new JBigDecimal(source)
        }
      }

      registry.addConverter(stringToJBigDecimalConverter)
    }
  }
}

@SpringBootApplication
class BankSpringBootApplication2

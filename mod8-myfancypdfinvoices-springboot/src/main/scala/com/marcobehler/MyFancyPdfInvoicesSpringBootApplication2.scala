package com.marcobehler

import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import javax.sql.DataSource

object MyFancyPdfInvoicesSpringBootApplication2 {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[MyFancyPdfInvoicesSpringBootApplication2], args: _*)
  }
}

@SpringBootApplication
class MyFancyPdfInvoicesSpringBootApplication2
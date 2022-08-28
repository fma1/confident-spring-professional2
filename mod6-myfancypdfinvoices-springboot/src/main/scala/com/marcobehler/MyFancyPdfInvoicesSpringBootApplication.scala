package com.marcobehler

import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import javax.sql.DataSource

object MyFancyPdfInvoicesSpringBootApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[MyFancyPdfInvoicesSpringBootApplication], args: _*)
  }
}

@SpringBootApplication
class MyFancyPdfInvoicesSpringBootApplication {
  @Bean
  def runner(dataSource: DataSource): InitializingBean = {
    () => {
      println(s"This is the datasource this Spring Boot project is using: $dataSource");
    }
  }
}
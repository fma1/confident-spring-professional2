package com.marcobehler

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object MyFancyPdfInvoicesSpringBootApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[MyFancyPdfInvoicesSpringBootApplication], args: _*)
  }
}

@SpringBootApplication
class MyFancyPdfInvoicesSpringBootApplication

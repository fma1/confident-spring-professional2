package com.marcobehler.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.context.MyFancyPdfInvoicesApplicationConfiguration
import com.marcobehler.service.{InvoiceService, UserService}
import org.springframework.context.annotation.AnnotationConfigApplicationContext

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class MyFancyPdfInvoicesServlet3 extends HttpServlet {
  var userService: UserService = _
  var objectMapper: ObjectMapper = _
  var invoiceService: InvoiceService = _

  override def init(): Unit = {
    val ctx = new AnnotationConfigApplicationContext(classOf[MyFancyPdfInvoicesApplicationConfiguration])
    userService = ctx.getBean(classOf[UserService])
    objectMapper = ctx.getBean(classOf[ObjectMapper])
    invoiceService = ctx.getBean(classOf[InvoiceService])

    ctx.registerShutdownHook()
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    if (request.getRequestURI.equalsIgnoreCase("/")) {
      response.setContentType("text/html; charset=UTF-8")
      response.getWriter.print(
        "<html>\n" +
          "<body>\n" +
          "<h1>Hello World</h1>\n" +
          "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
          "</body>\n" +
          "</html>")
    }
    else if (request.getRequestURI.equalsIgnoreCase("/invoices")) {
      response.setContentType("application/json; charset=UTF-8")
      val invoices = invoiceService.findAll()
      response.getWriter.print(objectMapper.writeValueAsString(invoices))
    }
  }

  override def doPost(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    if (request.getRequestURI.equalsIgnoreCase("/invoices")) {
      val userId = request.getParameter("user_id")
      val amount = request.getParameter("amount").toInt

      val invoice = invoiceService.create(userId, amount)

      response.setContentType("application/json; charset=UTF-8")
      val json = objectMapper.registerModule(DefaultScalaModule).writeValueAsString(invoice)
      response.getWriter.print(json)
    }
  }
}
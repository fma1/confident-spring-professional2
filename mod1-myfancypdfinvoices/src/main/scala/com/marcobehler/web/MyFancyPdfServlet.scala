package com.marcobehler.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.service.InvoiceService

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class MyFancyPdfServlet extends HttpServlet {
  private val invoiceService = new InvoiceService()
  private val objectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

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
package com.marcobehler.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.marcobehler.context.BankApplicationConfiguration
import com.marcobehler.service.TransactionService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class BankServlet extends HttpServlet {
  var transactionService: TransactionService = _
  var objectMapper: ObjectMapper = _

  override def init(): Unit = {
    val ctx = new AnnotationConfigApplicationContext(classOf[BankApplicationConfiguration])
    transactionService = ctx.getBean(classOf[TransactionService])
    objectMapper = ctx.getBean(classOf[ObjectMapper])
  }

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/")) {
      resp.setContentType("text/html; charset=UTF-8")
      resp.getWriter.write("<html><head><title>Index</title></head><body>Hello World</body></html>")
    } else if (req.getRequestURI.equalsIgnoreCase("/transactions")) {
      val transactions = transactionService.findAll()

      resp.setContentType("application/json; charset=UTF-8")
      val json = objectMapper.writeValueAsString(transactions)
      resp.getWriter.write(json)
    }
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/transactions")) {
      val amount = BigDecimal(req.getParameter("amount"))
      val reference = req.getParameter("reference")

      val transaction = transactionService.create(amount, reference)

      resp.setContentType("application/json; charset=UTF-8")
      val json = objectMapper.writeValueAsString(transaction)
      resp.getWriter.write(json)
    }
  }
}

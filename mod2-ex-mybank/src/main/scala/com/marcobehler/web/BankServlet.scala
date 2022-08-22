package com.marcobehler.web

import com.marcobehler.context.BankApplicationContext._

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class BankServlet extends HttpServlet {
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

package com.marcobehler

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class MyFirstServlet extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    response.setContentType("text/html; charset=UTF-8")
    response.getWriter.print(
      "<html>\n" +
        "<body>\n" +
        "<h1>Hello World</h1>\n" +
        "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
        "</body>\n" +
        "</html>")
  }
}
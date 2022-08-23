package com.marcobehler

import com.marcobehler.web.MyFancyPdfInvoicesServlet3
import org.apache.catalina.startup.Tomcat

object MyFancyPdfInvoicesApplicationLauncher3 {
  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(8080)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFancyPdfInvoicesServlet3())
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/*")

    tomcat.start()
  }
}

class MyFancyPdfInvoicesApplicationLauncher3

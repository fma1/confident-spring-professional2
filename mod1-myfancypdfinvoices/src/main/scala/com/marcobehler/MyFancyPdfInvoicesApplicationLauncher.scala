package com.marcobehler

import com.marcobehler.web.MyFancyPdfServlet
import org.apache.catalina.startup.Tomcat

object MyFancyPdfInvoicesApplicationLauncher {
  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(8080)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFancyPdfServlet())
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/*")

    tomcat.start()
  }
}

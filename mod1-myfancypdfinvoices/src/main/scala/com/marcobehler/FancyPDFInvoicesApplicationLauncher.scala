package com.marcobehler

import org.apache.catalina.startup.Tomcat

object FancyPDFInvoicesApplicationLauncher {
  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(8080)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFirstServlet())
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/*")

    tomcat.start()
  }
}

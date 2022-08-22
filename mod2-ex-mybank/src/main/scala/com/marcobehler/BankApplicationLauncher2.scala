package com.marcobehler

import com.marcobehler.web.BankServlet
import org.apache.catalina.startup.Tomcat

object BankApplicationLauncher2 {
  val PORT: Int = System.getProperty("server.port", "8080").toInt

  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(PORT)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "BankServlet", new BankServlet())
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/")

    tomcat.start()
  }
}

package com.marcobehler

import com.marcobehler.context.BankApplicationConfiguration
import org.apache.catalina.startup.Tomcat
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext

object BankApplicationLauncher3 {
  val PORT: Int = System.getProperty("server.port", "8080").toInt

  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(PORT)
    tomcat.getConnector

    val tomcatCtx = tomcat.addContext("", null)
    val appCtx = createApplicationContext(tomcatCtx.getServletContext)
    val servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", new DispatcherServlet(appCtx))
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/")

    tomcat.start()
  }

  def createApplicationContext(servletContext: ServletContext): WebApplicationContext = {
    val ctx = new AnnotationConfigWebApplicationContext()
    ctx.register(classOf[BankApplicationConfiguration])
    ctx.setServletContext(servletContext)
    ctx.refresh()
    ctx.registerShutdownHook()
    ctx
  }
}

class BankApplicationLauncher3

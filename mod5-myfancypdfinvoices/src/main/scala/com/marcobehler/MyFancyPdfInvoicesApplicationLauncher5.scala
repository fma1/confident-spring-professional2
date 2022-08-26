package com.marcobehler

import com.marcobehler.context.ApplicationConfiguration
import org.apache.catalina.startup.Tomcat
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext

object MyFancyPdfInvoicesApplicationLauncher5 {
  System.setProperty("spring.profiles.active", "dev")

  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(8080)
    tomcat.getConnector

    val tomcatCtx = tomcat.addContext("", null)
    val appCtx = createApplicationContext(tomcatCtx.getServletContext)
    val dispatcherServlet = new DispatcherServlet(appCtx)
    val servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet)
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/*")

    tomcat.start()
  }

  def createApplicationContext(servletContext: ServletContext): WebApplicationContext = {
    val ctx = new AnnotationConfigWebApplicationContext()
    ctx.register(classOf[ApplicationConfiguration])
    ctx.setServletContext(servletContext)
    ctx.refresh()
    ctx.registerShutdownHook()
    ctx
  }
}

class MyFancyPdfInvoicesApplicationLauncher5

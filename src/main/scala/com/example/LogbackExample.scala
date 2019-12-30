package com.example

import ch.qos.logback.classic.{Level, Logger, LoggerContext, PatternLayout}
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.encoder.LayoutWrappingEncoder
import org.slf4j.LoggerFactory


object LogbackExample extends App {

  val log: Logger = LoggerFactory.getLogger(getClass.getName).asInstanceOf[Logger]
val requiredConsoleLog=true


  //add console logs dynamically [requiredConsoleLog can be made configurable]
  if(requiredConsoleLog){
    createConsoleAppender()
  }

  //To dynamically change the log level.
  changeRootLevelLogging(Level.ERROR)

  log.info("This is an Info Log By Anmol")
  log.debug("This is a Debug Log By Anmol")
  log.warn("This is a Warn Log By Anmol")
  log.error("This is an Error Log By Anmol")


  def changeRootLevelLogging(level:Level) ={
    log.setLevel(level) //Default is Info as provided in logback.xml
  }

  def createConsoleAppender(): Unit = {
    val lc: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]

    val ca = new ConsoleAppender[ILoggingEvent]
    ca.setContext(lc)
    ca.setName("console")
    val encoder = new LayoutWrappingEncoder[ILoggingEvent]
    encoder.setContext(lc)
    val layout = new PatternLayout
    layout.setPattern("%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n")
    layout.setContext(lc)
    layout.start()
    encoder.setLayout(layout)
    ca.setEncoder(encoder)
    ca.start()
    log.addAppender(ca)
  }

}

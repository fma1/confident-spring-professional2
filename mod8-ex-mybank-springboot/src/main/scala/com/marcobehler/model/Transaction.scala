package com.marcobehler.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.{Id, Version}
import org.springframework.data.relational.core.mapping.Table

import java.time.ZonedDateTime
import java.util.UUID
import scala.beans.BeanProperty

import java.math.{BigDecimal => JBigDecimal}

@Table("transactions")
case class Transaction(@BeanProperty var amount: JBigDecimal, @BeanProperty var reference: String, @BeanProperty var bankSlogan: String, @BeanProperty var receivingUser: String) {
  @Id
  @BeanProperty
  var id: String = UUID.randomUUID().toString

  @BeanProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
  var timestamp: ZonedDateTime = ZonedDateTime.now()

  @BeanProperty
  @Version
  var version: Int = _

  def this() = this(new JBigDecimal(0), "", "", "")
}

package com.marcobehler.service

import com.marcobehler.model.Invoice
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component

import java.sql.{Connection, ResultSet, Statement}
import java.util.{UUID, List => JList}
import javax.annotation.{PostConstruct, PreDestroy}

@Component
class InvoiceService(userService: UserService, jdbcTemplate: JdbcTemplate, @Value("${cdn.url}") cdnUrl: String) {
  @PostConstruct
  def init(): Unit = {
    println("Fetching PDF Template from S3...")
    // TODO download from s3 and save locally
  }

  @PreDestroy
  def shutdown(): Unit = {
    println("Deleting downloaded templates...")
    // TODO actual deletion of PDFs
  }

  def findAll(): JList[Invoice] = {
    jdbcTemplate.query("select id, user_id, pdf_url, amount from invoices", (resultSet: ResultSet, rowNum: Int) => {
      val invoice = new Invoice()
      invoice.id = resultSet.getObject("id").toString
      invoice.pdfUrl = resultSet.getString("pdf_url")
      invoice.userId = resultSet.getString("user_id")
      invoice.amount = resultSet.getInt("amount")
      invoice
    })
  }

  def create(userId: String, amount: Int): Invoice = {
    val generatedPdfUrl = s"$cdnUrl/images/default/sample.pdf"
    val keyHolder = new GeneratedKeyHolder()

    jdbcTemplate.update((conn: Connection) => {
      val preparedStatement = conn.
        prepareStatement("insert into invoices (user_id, pdf_url, amount) values (?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS)

      preparedStatement.setString(1, userId)
      preparedStatement.setString(2, generatedPdfUrl)
      preparedStatement.setInt(3, amount)

      preparedStatement
    }, keyHolder)

    val uuid = Option(keyHolder.getKeys.values)
      .map(_.iterator.next.asInstanceOf[UUID].toString)
      .orNull

    val invoice = Invoice(userId, amount, generatedPdfUrl)
    invoice.id = uuid
    invoice
  }

  def getUserService: UserService = userService
}

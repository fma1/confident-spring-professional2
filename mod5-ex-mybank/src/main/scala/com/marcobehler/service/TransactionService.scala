package com.marcobehler.service

import com.marcobehler.model.Transaction
import com.marcobehler.service.TransactionService.ROW_MAPPER
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import java.sql.{Connection, ResultSet, Statement}
import java.util.{UUID, List => JList}

@Component
class TransactionService(jdbcTemplate: JdbcTemplate, @Value("${bank.slogan}") bankSlogan: String) {
  @Transactional
  def findAll(): JList[Transaction] = {
    jdbcTemplate.query("select id, amount, reference, bank_slogan, receiving_user from transactions", ROW_MAPPER)
  }

  @Transactional
  def findByReceivingUser(userId: String): JList[Transaction] = {
    jdbcTemplate.query("select id, amount, reference, bank_slogan, receiving_user from transactions where receiving_user = ?", ROW_MAPPER, userId)
  }

  @Transactional
  def create(amount: BigDecimal, reference: String, receivingUser: String): Transaction = {
    val keyHolder = new GeneratedKeyHolder()

    jdbcTemplate.update((conn: Connection) => {
      val preparedStatement = conn.
        prepareStatement("insert into transactions (amount, reference, bank_slogan, receiving_user) values (?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS)
      preparedStatement.setBigDecimal(1, amount.bigDecimal)
      preparedStatement.setString(2, reference)
      preparedStatement.setString(3, bankSlogan)
      preparedStatement.setString(4, receivingUser)
      preparedStatement
    }, keyHolder)

    val uuid = Option(keyHolder.getKeys.values)
      .map(_.iterator.next.asInstanceOf[UUID].toString)
      .orNull

    val transaction = Transaction(amount, reference, bankSlogan, receivingUser)
    transaction.setId(uuid)
    transaction
  }
}

object TransactionService {
  final val ROW_MAPPER: RowMapper[Transaction] = (resultSet: ResultSet, _: Int) => {
    val transaction = new Transaction()
    transaction.id = resultSet.getObject("id").toString
    transaction.amount = resultSet.getBigDecimal("amount")
    transaction.reference = resultSet.getString("reference")
    transaction.bankSlogan = resultSet.getString("bank_slogan")
    transaction.receivingUser = resultSet.getString("receiving_user")
    transaction
  }
}
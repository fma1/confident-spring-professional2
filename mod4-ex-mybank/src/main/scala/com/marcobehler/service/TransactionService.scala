package com.marcobehler.service

import com.marcobehler.model.Transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.util.concurrent.CopyOnWriteArrayList
import java.util.{ArrayList, List => JList}
import scala.jdk.CollectionConverters._

@Component
class TransactionService(@Value("${bank.slogan}") bankSlogan: String) {
  private val transactions = new CopyOnWriteArrayList[Transaction]()

  def findAll(): JList[Transaction] = {
    transactions
  }

  def findByReceivingUser(userId: String): JList[Transaction] = {
    val filteredCollection = transactions.asScala.filter(_.receivingUser == userId).asJavaCollection
    val aryList = new ArrayList[Transaction](filteredCollection)
    aryList
  }

  def create(amount: BigDecimal, reference: String, receivingUser: String): Transaction = {
    val transaction = Transaction(amount, reference, bankSlogan, receivingUser)
    transactions.add(transaction)
    transaction
  }
}

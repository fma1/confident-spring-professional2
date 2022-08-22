package com.marcobehler.service

import com.marcobehler.model.Transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.util.concurrent.CopyOnWriteArrayList
import java.util.{List => JList}

@Component
class TransactionService(@Value("${bank.slogan}") bankSlogan: String) {
  private val transactions = new CopyOnWriteArrayList[Transaction]()

  def findAll(): JList[Transaction] = {
    transactions
  }

  def create(amount: BigDecimal, reference: String): Transaction = {
    val transaction = Transaction(amount, reference, bankSlogan)
    transactions.add(transaction)
    transaction
  }
}

package com.marcobehler.service

import com.marcobehler.model.Transaction

import java.util.concurrent.CopyOnWriteArrayList
import java.util.{List => JList}

class TransactionService {
  private val transactions = new CopyOnWriteArrayList[Transaction]()

  def findAll(): JList[Transaction] = {
    transactions
  }

  def create(amount: BigDecimal, reference: String): Transaction = {
    val transaction = Transaction(amount, reference)
    transactions.add(transaction)
    transaction
  }
}

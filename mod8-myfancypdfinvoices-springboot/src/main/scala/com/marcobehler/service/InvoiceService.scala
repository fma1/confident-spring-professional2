package com.marcobehler.service

import com.marcobehler.model.Invoice
import com.marcobehler.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

import java.lang.{Iterable => JIterable}
import javax.annotation.{PostConstruct, PreDestroy}

@Component
class InvoiceService(invoiceRepository: InvoiceRepository, @Value("${cdn.url}") cdnUrl: String) {
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

  @Transactional
  def findAll(): JIterable[Invoice] = {
    checkIfTransactionOpen()

    invoiceRepository.findAll()
  }

  def findById(userId: String): JIterable[Invoice] = {
    checkIfTransactionOpen()

    invoiceRepository.findByUserId(userId)
  }

  @Transactional
  def create(userId: String, amount: Int): Invoice = {
    checkIfTransactionOpen()

    val generatedPdfUrl = s"$cdnUrl/images/default/sample.pdf"
    val invoice = Invoice(userId, amount, generatedPdfUrl)
    invoiceRepository.save(invoice)
  }

  def checkIfTransactionOpen(): Unit = {
    println(s"Is a database transaction open? = ${TransactionSynchronizationManager.isActualTransactionActive}")
  }
}

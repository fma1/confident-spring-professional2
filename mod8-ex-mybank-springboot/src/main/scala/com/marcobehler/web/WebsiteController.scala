package com.marcobehler.web

import com.marcobehler.service.TransactionService
import com.marcobehler.web.forms.TransactionForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.{GetMapping, ModelAttribute, PathVariable, PostMapping, RequestParam}

import java.util.Date
import javax.validation.Valid

@Controller
class WebsiteController(transactionService: TransactionService) {
  @GetMapping(Array("/"))
  def homepage(model: Model, @RequestParam(required = false, defaultValue = "stranger") username: String): String = {
    model.addAttribute("username", username)
    model.addAttribute("currentDate", new Date())
    "index.html"
  }

  @GetMapping(Array("/account/{userId}"))
  def transactionList(model: Model, @PathVariable("userId") userId: String): String = {
    buildAccountPageModel(model, userId)
    model.addAttribute("txForm", new TransactionForm())
    "account.html"
  }

  @PostMapping(Array("/account/{userId}"))
  def createTransaction(@ModelAttribute("txForm") @Valid txForm: TransactionForm, bindingResult: BindingResult, model: Model, @PathVariable("userId") userId: String): String = {
    buildAccountPageModel(model, userId)

    if (bindingResult.hasErrors) {
      model.addAttribute("txError", true)
      "account.html"
    } else {
      transactionService.create(txForm.amount, txForm.reference, txForm.receivingUser)
      s"redirect:/account/${userId}"
    }
  }


  def buildAccountPageModel(model: Model, userId: String): Unit = {
    model.addAttribute("userId", userId)
    model.addAttribute("transactions", transactionService.findByReceivingUser(userId))
  }
}

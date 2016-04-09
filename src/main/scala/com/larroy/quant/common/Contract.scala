package com.larroy.quant.common

import java.time.ZonedDateTime

trait Contract {
  val symbol: String
  val exchange: String
  val currency: String
  val expiry: Option[ZonedDateTime] = None
  val contractType: ContractType.Enum
  override def toString: String = {
    val res = new StringBuilder(128)
    res ++= contractType.toString
    res ++= s"Contract(symbol: $symbol, exchange: $exchange, currency: $currency, expiry: $expiry)"
    res.toString
  }
}

case class FutureContract(symbol: String, exchange: String, currency: String = "USD", override val expiry: Option[ZonedDateTime] = None) extends Contract {
  override val contractType = ContractType.Future
}

case class StockContract(symbol: String, exchange: String = "SMART", currency: String = "USD") extends Contract {
  override val contractType = ContractType.Stock
}

case class CurrencyContract(symbol: String, exchange: String = "IDEALPRO", currency: String = "USD") extends Contract {
  override val contractType = ContractType.Currency
}


object Contract {

  def apply(symbol: String, contractType: ContractType.Enum, exchange: String, currency: String = "USD", expiry: Option[ZonedDateTime] = None): Contract = {
    contractType match {
      case ContractType.Future ⇒ FutureContract(symbol, exchange, currency, expiry)
      case ContractType.Stock ⇒ StockContract(symbol, exchange, currency)
      case ContractType.Currency ⇒ CurrencyContract(symbol, exchange, currency)
    }
  }
  def apply(symbol: String, contractType: String, exchange: String, currency: String, expiry: Option[ZonedDateTime]): Contract =
    Contract.apply(symbol, ContractType.withName(contractType), exchange, currency, expiry)
}

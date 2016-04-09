package com.larroy.quant.common

class Error(s: String) extends RuntimeException(s) {
  def this() = this("")
}

object Error {
  def apply(s: String): Error = new Error(s)
  def apply(): Error = new Error("")
}
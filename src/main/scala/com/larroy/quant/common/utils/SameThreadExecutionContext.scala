package com.larroy.quant.common.utils

import scala.concurrent.ExecutionContext

object SameThreadExecutionContext extends ExecutionContext {
  override def execute(runnable: Runnable): Unit = runnable.run()

  override def reportFailure(cause: Throwable): Unit =
    throw new IllegalStateException(s"Exception in SameThreadExecutionContext: $cause")
}

package com.larroy.quant.common.utils

import java.util.concurrent.{TimeUnit, ArrayBlockingQueue, ThreadPoolExecutor}

import scala.concurrent.ExecutionContext

object ThrottlingExecutionContext {
  def apply(poolSize: Int, maxQueued: Int): ExecutionContext = {
    val workQueue = new ArrayBlockingQueue[Runnable](maxQueued) {
      override def offer(x: Runnable): Boolean = {
        put(x)
        true
      }
    }

    ExecutionContext.fromExecutorService(
      new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.SECONDS, workQueue)
    )
  }
}


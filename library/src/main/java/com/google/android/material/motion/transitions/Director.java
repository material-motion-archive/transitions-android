/*
 * Copyright (C) 2016 The Material Motion Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.material.motion.transitions;

import android.view.View;
import com.google.android.material.motion.runtime.Transaction;

/**
 *
 */
public abstract class Director {

  private TransactCallback callback;

  public abstract void setUp(Transaction transaction);

  public void tearDown() {}

  final void setTransactCallback(TransactCallback callback) {
    this.callback = callback;
  }

  protected final void transact(Work work) {
    callback.transact(work);
  }

  protected static abstract class Work {

    public abstract void work(Transaction transaction);
  }

  interface TransactCallback {

    void transact(Work work);
  }

  public static class DirectorInstantiationException extends RuntimeException {
    public DirectorInstantiationException(Class<? extends Director> klass, Exception cause) {
      super(
          "Unable to instantiate Director "
              + klass.getName()
              + ": make sure class name exists, is public, and has an empty constructor that is "
              + "public",
          cause);
    }
  }

  public static abstract class ViewHolder {
    public final View itemView;

    public ViewHolder(View itemView) {
      this.itemView = itemView;
    }
  }
}

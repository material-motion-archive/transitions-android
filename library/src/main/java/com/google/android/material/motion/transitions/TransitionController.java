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

import com.google.android.material.motion.runtime.MotionRuntime;
import com.google.android.material.motion.runtime.MotionRuntime.StateListener;
import com.google.android.material.motion.transitions.Director.DirectorInstantiationException;
import com.google.android.material.motion.transitions.Director.TransactCallback;
import com.google.android.material.motion.transitions.Director.Work;
import com.google.android.material.motion.transitions.TransitionDirector.Direction;

import static com.google.android.material.motion.runtime.MotionRuntime.IDLE;

/**
 *
 */
public abstract class TransitionController implements TransactCallback, StateListener {
  private MotionRuntime runtime;
  private TransitionDirector<?, ?> director;

  public abstract Class<? extends TransitionDirector<?, ?>> getDirectorClass();

  // TODO(markwei): Who calls this?
  private void onStart(@Direction int initialDirection) {
    director = createDirector(getDirectorClass());

    runtime = new MotionRuntime();
    runtime.addStateListener(this);

    director.setUp(runtime);
  }

  private void onFinish() {
    director.tearDown();
  }

  private TransitionDirector<?, ?> createDirector(
    Class<? extends TransitionDirector<?, ?>> directorClass) {
    try {
      TransitionDirector<?, ?> director = directorClass.newInstance();
      director.setTransactCallback(this);

      return director;
    } catch (InstantiationException e) {
      throw new DirectorInstantiationException(directorClass, e);
    } catch (IllegalAccessException e) {
      throw new DirectorInstantiationException(directorClass, e);
    }
  }

  @Override
  public void transact(Work work) {
    work.work(runtime);
  }

  @Override
  public void onStateChange(MotionRuntime runtime, @MotionRuntime.State int newState) {
    if (newState == IDLE) {
      onFinish();
    }
  }
}

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

import android.support.annotation.IntDef;
import com.google.android.material.motion.transitions.Director.ViewHolder;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
public abstract class TransitionDirector<Left extends ViewHolder, Right extends ViewHolder>
    extends Director {

  public static final int TO_THE_RIGHT = 0;
  public static final int TO_THE_LEFT = 1;

  @IntDef({TO_THE_RIGHT, TO_THE_LEFT})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Direction {}

  // TODO(markwei): Who sets these?
  protected Left left;
  protected Right right;
  @Direction protected final int initialDirection;
  @Direction protected int currentDirection;

  public TransitionDirector(@Direction int initialDirection) {
    this.initialDirection = initialDirection;
    this.currentDirection = initialDirection;
  }
}

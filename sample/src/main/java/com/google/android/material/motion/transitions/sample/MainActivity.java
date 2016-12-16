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

package com.google.android.material.motion.transitions.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.material.motion.runtime.MotionRuntime;
import com.google.android.material.motion.runtime.Performer;
import com.google.android.material.motion.runtime.Plan;
import com.google.android.material.motion.transitions.Director;

/**
 * Material Motion Android Transitions sample Activity.
 */
public class MainActivity extends AppCompatActivity {

  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main_activity);

    textView = (TextView) findViewById(R.id.text);

    MotionRuntime runtime = new MotionRuntime();

    DemoDirector director = new DemoDirector();
    director.setUp(runtime);
  }

  private class DemoDirector extends Director {

    @Override
    public void setUp(MotionRuntime runtime) {
      runtime.addPlan(new DemoPlan(), textView);
    }
  }

  private static class DemoPlan extends Plan<TextView> {
    private final String text = "test";

    @Override
    public Class<? extends Performer<TextView>> getPerformerClass() {
      return DemoPerformer.class;
    }
  }

  public static class DemoPerformer extends Performer<TextView> {

    @Override
    public void addPlan(Plan plan) {
      TextView target = getTarget();
      target.setText(((DemoPlan) plan).text);
    }
  }
}

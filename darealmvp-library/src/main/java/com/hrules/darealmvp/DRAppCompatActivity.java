/*
 * Copyright (c) 2016. Héctor de Isidro - hrules6872
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hrules.darealmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRAppCompatActivity<P extends DRPresenter<V>, V extends DRView>
    extends AppCompatActivity implements DRView {

  private P presenter;

  @SuppressWarnings("unchecked") @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    preSetContentView();
    setContentView(getLayoutResource());

    if (presenter == null) {
      try {
        presenter = internalGetPresenter();
      } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | ClassCastException e) {
        Log.e("DRAppCompatActivity", e.getMessage(), e);
      }
    }
    if (presenter == null) {
      throw new IllegalArgumentException();
    }

    presenter.bind((V) this);
    initializeViews();
    if (savedInstanceState != null) {
      presenter.onLoadState(savedInstanceState);
    }
    presenter.onViewReady();
  }

  @SuppressWarnings("unchecked") private P internalGetPresenter()
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    Class clazz = getClass();
    Type genericSuperclass;
    for (; ; ) {
      genericSuperclass = clazz.getGenericSuperclass();
      if (genericSuperclass instanceof ParameterizedType) {
        break;
      }
      clazz = clazz.getSuperclass();
    }
    Type presenterClass = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    return (P) Class.forName(presenterClass.toString().split(" ")[1]).newInstance();
  }

  @SuppressWarnings("unchecked") public P getPresenter() {
    return presenter;
  }

  public void setPresenter(@NonNull P presenter) {
    this.presenter = presenter;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getPresenter().onSaveState(outState);
  }

  @Override protected void onResume() {
    super.onResume();
    getPresenter().onResume();
  }

  @Override protected void onStart() {
    super.onStart();
    getPresenter().onStart();
  }

  @Override protected void onStop() {
    super.onStop();
    getPresenter().onStop();
  }

  @Override protected void onPause() {
    super.onPause();
    getPresenter().onPause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    getPresenter().unbind();
    getPresenter().onDestroy();
  }

  protected void preSetContentView() {
  }

  protected abstract int getLayoutResource();

  protected abstract void initializeViews();
}
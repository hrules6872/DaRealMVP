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

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Deprecated public abstract class DRPreferenceActivity<P extends DRPresenter<V>, V extends DRView> extends PreferenceActivity
    implements DRView {
  private AppCompatDelegate appCompatDelegate;

  private P presenter;

  @SuppressWarnings({ "unchecked", "deprecation" }) @Override protected void onCreate(Bundle savedInstanceState) {
    getDelegate().installViewFactory();
    getDelegate().onCreate(savedInstanceState);
    preSetContentView();
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());
    addPreferencesFromResource(getPreferencesResource());

    if (presenter == null) {
      try {
        presenter = internalGetPresenter();
      } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | ClassCastException e) {
        Log.e("DRPreferenceActivity", e.getMessage(), e);
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

  @SuppressWarnings("unchecked") @NonNull public P getPresenter() {
    return presenter;
  }

  public void setPresenter(@NonNull P presenter) {
    this.presenter = presenter;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getPresenter().onSaveState(outState);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    getDelegate().onPostCreate(savedInstanceState);
  }

  public android.support.v7.app.ActionBar getSupportActionBar() {
    return getDelegate().getSupportActionBar();
  }

  public void setSupportActionBar(@Nullable Toolbar toolbar) {
    getDelegate().setSupportActionBar(toolbar);
  }

  @NonNull @Override public MenuInflater getMenuInflater() {
    return getDelegate().getMenuInflater();
  }

  @Override public void setContentView(@LayoutRes int layoutResID) {
    getDelegate().setContentView(layoutResID);
  }

  @Override public void setContentView(View view) {
    getDelegate().setContentView(view);
  }

  @Override public void setContentView(View view, ViewGroup.LayoutParams params) {
    getDelegate().setContentView(view, params);
  }

  @Override public void addContentView(View view, ViewGroup.LayoutParams params) {
    getDelegate().addContentView(view, params);
  }

  @Override protected void onPostResume() {
    super.onPostResume();
    getDelegate().onPostResume();
    getPresenter().onResume();
  }

  @Override protected void onTitleChanged(CharSequence title, int color) {
    super.onTitleChanged(title, color);
    getDelegate().setTitle(title);
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    getDelegate().onConfigurationChanged(newConfig);
  }

  @Override protected void onStop() {
    super.onStop();
    getDelegate().onStop();
    getPresenter().onStop();
  }

  @Override protected void onPause() {
    super.onPause();
    getPresenter().onPause();
  }

  @Override protected void onStart() {
    super.onStart();
    getPresenter().onStart();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    getDelegate().onDestroy();
    getPresenter().unbind();
    getPresenter().onDestroy();
  }

  public void invalidateOptionsMenu() {
    getDelegate().invalidateOptionsMenu();
  }

  private AppCompatDelegate getDelegate() {
    if (appCompatDelegate == null) {
      appCompatDelegate = AppCompatDelegate.create(this, null);
    }
    return appCompatDelegate;
  }

  protected void preSetContentView() {
  }

  protected abstract void initializeViews();

  protected abstract int getPreferencesResource();

  protected abstract int getLayoutResource();
}
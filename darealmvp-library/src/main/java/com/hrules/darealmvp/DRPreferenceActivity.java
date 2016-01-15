package com.hrules.darealmvp;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRPreferenceActivity<P extends DRPresenter<V>, V extends DRView>
    extends PreferenceActivity implements DRView {
  private AppCompatDelegate appCompatDelegate;

  private P presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
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
        e.printStackTrace();
      }
    }
    presenter.bind((V) this);

    if (savedInstanceState != null) {
      presenter.onLoadState(savedInstanceState);
    }

    initializeViews();
    presenter.onViewReady();
  }

  private P internalGetPresenter()
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

  @NonNull public P getPresenter() {
    return presenter;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    presenter.onSaveState(outState);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
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

  @Override public MenuInflater getMenuInflater() {
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
    presenter.onResume();
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
    presenter.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    getDelegate().onDestroy();
    presenter.unbind();
    presenter.onDestroy();
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override protected void onStart() {
    super.onStart();
    presenter.onStart();
  }

  public void invalidateOptionsMenu() {
    getDelegate().invalidateOptionsMenu();
  }

  private void preSetContentView() {
  }

  public abstract void initializeViews();

  protected abstract int getPreferencesResource();

  protected abstract int getLayoutResource();

  public void setPresenter(P presenter) {
    this.presenter = presenter;
  }

  private AppCompatDelegate getDelegate() {
    if (appCompatDelegate == null) {
      appCompatDelegate = AppCompatDelegate.create(this, null);
    }
    return appCompatDelegate;
  }

  public Context getContext() {
    return this.getApplicationContext();
  }
}


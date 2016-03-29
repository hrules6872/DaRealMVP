package com.hrules.darealmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
        e.printStackTrace();
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

  @SuppressWarnings("EmptyMethod") protected void preSetContentView() {
  }

  protected abstract int getLayoutResource();

  protected abstract void initializeViews();

  public Context getContext() {
    return this;
  }
}
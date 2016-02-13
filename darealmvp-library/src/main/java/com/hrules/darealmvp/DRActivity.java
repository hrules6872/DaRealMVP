package com.hrules.darealmvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRActivity<P extends DRPresenter<V>, V extends DRView> extends Activity
    implements DRView {

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
    presenter.bind((V) this);

    if (savedInstanceState != null) {
      presenter.onLoadState(savedInstanceState);
    }

    initializeViews();
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

  @NonNull public P getPresenter() {
    return presenter;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    presenter.onSaveState(outState);
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override protected void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override protected void onStop() {
    super.onStop();
    presenter.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.unbind();
    presenter.onDestroy();
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  public void preSetContentView() {
  }

  protected abstract int getLayoutResource();

  protected abstract void initializeViews();

  public void setPresenter(P presenter) {
    this.presenter = presenter;
  }

  public Context getContext() {
    return this.getApplicationContext();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}

package com.hrules.darealmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRAppCompatActivity<P extends DRPresenter<V>, V extends DRView>
    extends AppCompatActivity implements DRView {

  private P presenter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    preSetContentView();
    setContentView(getLayoutResource());

    if (presenter == null) {
      try {
        Type mySuperclass = getClass().getGenericSuperclass();
        Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
        presenter = (P) (Class.forName(tType.toString().split(" ")[1]).newInstance());
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
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

  @NonNull public P getPresenter() {
    return presenter;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    presenter.onSaveState(outState);
  }

  @Override public void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override public void onStop() {
    super.onStop();
    presenter.onStop();
  }

  @Override public void onDestroy() {
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

  public abstract int getLayoutResource();

  public abstract void initializeViews();

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

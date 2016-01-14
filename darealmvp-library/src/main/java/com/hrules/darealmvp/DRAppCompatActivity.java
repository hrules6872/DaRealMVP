package com.hrules.darealmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.hrules.darealmvp.base.BaseAppCompatActivity;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRAppCompatActivity<P extends DRPresenter<V>, V extends DRView>
    extends AppCompatActivity implements BaseAppCompatActivity, DRView {

  protected P presenter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    preSetContentView();
    setContentView(getLayoutResource());

    try {
      Type mySuperclass = getClass().getGenericSuperclass();
      Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
      presenter = (P) (Class.forName(tType.toString().split(" ")[1]).newInstance());
      presenter.bind((V) this);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

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

  public void preSetContentView() {
  }

  public abstract int getLayoutResource();

  public abstract void initializeViews();
}

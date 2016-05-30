package com.hrules.darealmvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRAppCompatDialogFragment<P extends DRPresenter<V>, V extends DRView>
    extends AppCompatDialogFragment implements DRView {

  private P presenter;

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    initializePresenter(null, savedInstanceState);
    return getAlertDialog().create();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    int layoutId = getLayoutResource();
    if (!getShowsDialog()) {
      preCreateView();
      return inflater.inflate(layoutId, container, false);
    }
    return null;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initializePresenter(view, savedInstanceState);
  }

  @SuppressWarnings("unchecked")
  private void initializePresenter(View view, @Nullable Bundle savedInstanceState) {
    if (presenter == null) {
      try {
        presenter = internalGetPresenter();
      } catch (java.lang.InstantiationException | ClassNotFoundException | IllegalAccessException | ClassCastException e) {
        Log.e("DRAppCompatDialogF", e.getMessage(), e);
      }
    }

    if (presenter == null) {
      throw new IllegalArgumentException();
    }

    presenter.bind((V) this);
    if (view != null) {
      initializeViews(view);
    }
    if (savedInstanceState != null) {
      presenter.onLoadState(savedInstanceState);
    }
    presenter.onViewReady();
  }

  @SuppressWarnings("unchecked") private P internalGetPresenter()
      throws IllegalAccessException, ClassNotFoundException, java.lang.InstantiationException {
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

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (presenter != null) {
      presenter.onSaveState(outState);
    }
  }

  @Override public void onResume() {
    super.onResume();
    if (presenter != null) {
      presenter.onResume();
    }
  }

  @Override public void onStart() {
    super.onStart();
    if (presenter != null) {
      presenter.onStart();
    }
  }

  @Override public void onStop() {
    super.onStop();
    if (presenter != null) {
      presenter.onStop();
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.onDestroy();
    }
  }

  @Override public void onPause() {
    super.onPause();
    if (presenter != null) {
      presenter.onPause();
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (presenter != null) {
      presenter.unbind();
    }
  }

  protected void preCreateView() {
  }

  protected abstract AlertDialog.Builder getAlertDialog();

  protected abstract int getLayoutResource();

  protected abstract void initializeViews(View view);
}

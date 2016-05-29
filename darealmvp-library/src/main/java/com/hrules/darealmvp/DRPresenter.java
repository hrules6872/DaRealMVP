package com.hrules.darealmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRPresenter<V extends DRView> {
  private V view;
  private V nullView;

  public DRPresenter() {
    try {
      nullView = NullView.of(internalGetViewInterface());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked") private Class<V> internalGetViewInterface() {
    Class clazz = getClass();
    Type genericSuperclass;
    for (; ; ) {
      genericSuperclass = clazz.getGenericSuperclass();
      if (genericSuperclass instanceof ParameterizedType) {
        break;
      }
      clazz = clazz.getSuperclass();
    }
    return (Class<V>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
  }

  @CallSuper protected void bind(@NonNull V view) {
    this.view = view;
  }

  protected void unbind() {

  }

  protected void onResume() {
  }

  protected void onPause() {
  }

  protected void onSaveState(Bundle outState) {
  }

  protected void onLoadState(Bundle savedState) {
  }

  protected void onStart() {
  }

  protected void onStop() {
  }

  @CallSuper protected void onDestroy() {
    this.view = null;
  }

  protected void onViewReady() {
  }

  protected V getView() {
    if (view != null) {
      return view;
    }

    return nullView;
  }

  protected Context getViewContext() {
    return view.getContext();
  }

  protected Context getApplicationContext() {
    return view.getApplicationContext();
  }
}

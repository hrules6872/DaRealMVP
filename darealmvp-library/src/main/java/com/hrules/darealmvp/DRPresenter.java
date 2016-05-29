package com.hrules.darealmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRPresenter<V extends DRView> {
  private WeakReference<V> view;
  private V nullView;

  public DRPresenter() {
    try {
      nullView = NullView.of(internalGetViewInterface());
    } catch (Exception e) {
      Log.e("DRPresenter", e.getMessage(), e);
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
    this.view = new WeakReference<>(view);
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
      V viewReferent = view.get();
      if (viewReferent != null) {
        return viewReferent;
      }
    }

    return nullView;
  }

  protected Context getViewContext() {
    return getView().getContext();
  }

  protected Context getApplicationContext() {
    return getView().getApplicationContext();
  }
}

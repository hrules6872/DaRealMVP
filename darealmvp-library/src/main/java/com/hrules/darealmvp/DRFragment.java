package com.hrules.darealmvp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRFragment<P extends DRPresenter<V>, V extends DRView> extends Fragment
    implements DRView {

  private P presenter;

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(getLayoutResource(), container, false);
  }

  @SuppressWarnings("unchecked") @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (presenter == null) {
      try {
        presenter = internalGetPresenter();
      } catch (java.lang.InstantiationException | ClassNotFoundException | IllegalAccessException | ClassCastException e) {
        e.printStackTrace();
      }
    }

    if (presenter == null) {
      throw new IllegalArgumentException();
    }

    PresenterCache.getInstance().put(getViewTag(), presenter);
    presenter.bind((V) this);
    if (savedInstanceState != null) {
      presenter.onLoadState(savedInstanceState);
    }

    initializeViews(view);
    presenter.onViewReady();
  }

  @SuppressWarnings("unchecked") private P internalGetPresenter()
      throws java.lang.InstantiationException, IllegalAccessException, ClassNotFoundException {
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
    return presenter != null ? presenter : (P) PresenterCache.getInstance().get(getViewTag());
  }

  public void setPresenter(@NonNull P presenter) {
    this.presenter = presenter;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getPresenter().onSaveState(outState);
  }

  @Override public void onResume() {
    super.onResume();
    getPresenter().onResume();
  }

  @Override public void onStart() {
    super.onStart();
    getPresenter().onStart();
  }

  @Override public void onStop() {
    super.onStop();
    getPresenter().onStop();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    getPresenter().onDestroy();
  }

  @Override public void onPause() {
    super.onPause();
    getPresenter().onPause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    getPresenter().unbind();
    PresenterCache.getInstance().removePresenter(getViewTag());
  }

  protected abstract int getLayoutResource();

  protected abstract void initializeViews(View view);

  public Context getContext() {
    return getActivity().getApplicationContext();
  }

  protected abstract @NonNull String getViewTag();
}
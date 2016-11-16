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

public abstract class DRAppCompatDialogFragment<P extends DRPresenter<V>, V extends DRView> extends AppCompatDialogFragment
    implements DRView {

  private P presenter;

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    initializePresenter(null, savedInstanceState);
    return getAlertDialog().create();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

  @SuppressWarnings("unchecked") private void initializePresenter(View view, @Nullable Bundle savedInstanceState) {
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
    presenter.onViewReady(savedInstanceState);
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

  protected abstract void initializeViews(@NonNull View view);
}

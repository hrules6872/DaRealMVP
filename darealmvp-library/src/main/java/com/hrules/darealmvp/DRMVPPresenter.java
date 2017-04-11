/*
 * Copyright (c) 2017. Héctor de Isidro - hrules6872
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

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRMVPPresenter<V extends DRMVPView> {
  private WeakReference<V> view;
  private V nullView;

  protected DRMVPPresenter() {
    try {
      nullView = NullView.of(internalGetViewInterfaceClass());
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
  }

  @SuppressWarnings("unchecked") private Class<V> internalGetViewInterfaceClass() {
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

  @CallSuper public void bind(@NonNull V view) {
    this.view = new WeakReference<>(view);
  }

  @CallSuper public void unbind() {
    this.view = null;
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
}
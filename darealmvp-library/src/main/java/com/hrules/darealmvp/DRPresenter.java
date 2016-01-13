package com.hrules.darealmvp;

import android.support.annotation.NonNull;

public abstract class DRPresenter<V extends DRView> implements BaseDRPresenter<V> {
  protected V view;

  public void bind(@NonNull V view) {
    this.view = view;
  }

  public void unbind() {
    view = null;
  }
}

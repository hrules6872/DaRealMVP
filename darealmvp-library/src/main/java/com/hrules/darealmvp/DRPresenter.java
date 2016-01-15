package com.hrules.darealmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;

public abstract class DRPresenter<V extends DRView> {
  protected V view;

  public void bind(@NonNull V view) {
    this.view = view;
  }

  public void unbind() {
    view = null;
  }

  public void onResume() {
  }

  public void onPause() {
  }

  public void onSaveState(Bundle outState) {
  }

  public void onLoadState(Bundle savedState) {
  }

  public void onStart() {
  }

  public void onStop() {
  }

  public void onDestroy() {
  }

  public void onViewReady() {
  }
}

package com.hrules.darealmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;

public abstract class DRPresenter<V extends DRView> {
  protected V view;

  protected void bind(@NonNull V view) {
    this.view = view;
  }

  protected void unbind() {
    this.view = null;
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

  protected void onDestroy() {
  }

  protected void onViewReady() {
  }
}

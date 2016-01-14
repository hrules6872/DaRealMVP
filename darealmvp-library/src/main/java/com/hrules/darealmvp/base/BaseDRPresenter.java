package com.hrules.darealmvp.base;

import android.os.Bundle;
import com.hrules.darealmvp.DRView;

public interface BaseDRPresenter<V extends DRView> {
  void bind(V DRView);

  void unbind();

  void onResume();

  void onSaveState(Bundle outState);

  void onLoadState(Bundle savedState);

  void onStart();

  void onStop();

  void onDestroy();
}

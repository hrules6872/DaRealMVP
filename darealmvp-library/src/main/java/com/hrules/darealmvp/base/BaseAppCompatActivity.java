package com.hrules.darealmvp.base;

import android.os.Bundle;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;

public interface BaseAppCompatActivity<P extends DRPresenter<V>, V extends DRView> {
  void onCreate(Bundle savedInstanceState);

  void onResume();

  void onStart();

  void onStop();

  void onDestroy();

  void onSaveInstanceState(Bundle outState);

  P getPresenter();
}

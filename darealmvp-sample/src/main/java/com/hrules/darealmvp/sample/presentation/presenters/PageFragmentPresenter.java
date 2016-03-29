package com.hrules.darealmvp.sample.presentation.presenters;

import android.os.Bundle;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.commons.DebugLog;

public class PageFragmentPresenter extends DRPresenter<PageFragmentPresenter.IPage> {
  private static final String BUNDLE_TEST_BOOLEAN = "BUNDLE_TEST_BOOLEAN";

  @Override public void unbind() {
    getView().unbind();
  }

  @Override public void onSaveState(Bundle outState) {
    outState.putBoolean(BUNDLE_TEST_BOOLEAN, true);
    DebugLog.d("onSaveState");
  }

  @Override public void onLoadState(Bundle savedState) {
    DebugLog.d("onLoadState:  " + savedState.getBoolean("BUNDLE_TEST_BOOLEAN"));
  }

  public interface IPage extends DRView {
    void unbind();
  }
}

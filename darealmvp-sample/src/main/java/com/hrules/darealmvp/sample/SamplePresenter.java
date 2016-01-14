package com.hrules.darealmvp.sample;

import android.os.Bundle;
import android.os.Handler;
import com.hrules.darealmvp.DRPresenter;

public class SamplePresenter extends DRPresenter<SampleView> {

  private boolean messageShowed;

  @Override public void onResume() {
    if (messageShowed) {
      view.changeMessage("ROTATE");
    } else {
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          view.changeMessage("ROTATE"); // TODO
          view.showToast();

          messageShowed = true;
        }
      }, 2000);
    }
  }

  @Override public void onSaveState(Bundle outState) {
    super.onSaveState(outState);
    outState.putBoolean("messageShowed", messageShowed);
  }

  @Override public void onLoadState(Bundle savedState) {
    super.onLoadState(savedState);
    messageShowed = savedState.getBoolean("messageShowed");
  }
}

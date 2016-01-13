package com.hrules.darealmvp.sample;

import android.os.Handler;
import com.hrules.darealmvp.DRPresenter;

public class SamplePresenter extends DRPresenter<SampleView> {
  public void loadImage() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        view.showToast();
      }
    }, 2000);
  }
}

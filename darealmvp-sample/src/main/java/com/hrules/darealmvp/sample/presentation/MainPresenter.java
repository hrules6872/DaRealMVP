package com.hrules.darealmvp.sample.presentation;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.R;

public class MainPresenter extends DRPresenter<MainPresenter.MainView> {

  private boolean messageShowed;

  @Override public void onResume() {
    if (messageShowed) {
      showMessage();
    } else {
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          view.showToast();
        }
      }, 2000);
    }
  }

  @Override public void onSaveState(Bundle outState) {
    outState.putBoolean("messageShowed", messageShowed);
  }

  @Override public void onLoadState(Bundle savedState) {
    messageShowed = savedState.getBoolean("messageShowed");
  }

  public void showMessage() {
    view.changeMessage(view.getContext().getString(R.string.try_rotate));
    messageShowed = true;
  }

  public void showListActivity() {
    view.showListActivity();
  }

  public interface MainView extends DRView {
    void showToast();

    void changeMessage(String message);

    Context getContext();

    void showListActivity();
  }
}

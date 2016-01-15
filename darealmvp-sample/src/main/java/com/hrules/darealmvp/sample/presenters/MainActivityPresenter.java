package com.hrules.darealmvp.sample.presenters;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.R;

public class MainActivityPresenter extends DRPresenter<MainActivityPresenter.IMainView> {
  private static final String BUNDLE_MESSAGE_SHOWED = "BUNDLE_MESSAGE_SHOWED";
  private static final String BUNDLE_TOAST_SHOWED = "BUNDLE_TOAST_SHOWED";

  private boolean messageShowed;
  private boolean toastShowed;

  @Override public void onResume() {
    if (messageShowed) {
      showMessage();
    }
    if (!toastShowed) {
      showToast();
    }
  }

  private void showToast() {
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        view.showToast();
        toastShowed = true;
      }
    }, 1000);
  }

  @Override public void onSaveState(Bundle outState) {
    outState.putBoolean(BUNDLE_MESSAGE_SHOWED, messageShowed);
    outState.putBoolean(BUNDLE_TOAST_SHOWED, toastShowed);
  }

  @Override public void onLoadState(Bundle savedState) {
    messageShowed = savedState.getBoolean(BUNDLE_MESSAGE_SHOWED);
    toastShowed = savedState.getBoolean(BUNDLE_TOAST_SHOWED);
  }

  public void showMessage() {
    view.changeMessage(view.getContext().getString(R.string.try_rotate));
    messageShowed = true;
  }

  public void onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_preferences) {
      view.showPreferencesActivity();
    }
  }

  public void onClickButton(Button button) {
    switch (button.getId()) {
      case R.id.showMessage:
        showMessage();
        break;

      case R.id.showListActivity:
        view.showListActivity();
        break;

      default:
        throw new UnsupportedOperationException();
    }
  }

  public interface IMainView extends DRView {
    void showToast();

    void changeMessage(String message);

    void showListActivity();

    void showPreferencesActivity();
  }
}

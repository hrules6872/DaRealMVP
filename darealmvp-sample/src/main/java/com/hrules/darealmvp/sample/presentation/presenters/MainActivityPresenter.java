package com.hrules.darealmvp.sample.presentation.presenters;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.view.MenuItem;
import android.widget.Button;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.commons.NotImplementedException;

public class MainActivityPresenter extends DRPresenter<MainActivityPresenter.MainView> {
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
        getView().showToast();
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

  private void showMessage() {
    getView().changeMessage(R.string.try_rotate);
    messageShowed = true;
  }

  public void onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_preferences:
        getView().showPreferencesActivity();
        break;

      default:
        throw new NotImplementedException();
    }
  }

  public void onClickButton(Button button) {
    switch (button.getId()) {
      case R.id.showMessage:
        showMessage();
        break;

      case R.id.showListActivity:
        getView().showListActivity();
        break;

      case R.id.showPagerActivity:
        getView().showPagerActivity();
        break;

      case R.id.showDialogFragment:
        getView().showDialogFragment();
        break;

      default:
        throw new NotImplementedException();
    }
  }

  public interface MainView extends DRView {
    void showToast();

    void showListActivity();

    void showPagerActivity();

    void showPreferencesActivity();

    void showDialogFragment();

    void changeMessage(@StringRes int message);
  }
}

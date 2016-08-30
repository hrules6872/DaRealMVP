/*
 * Copyright (c) 2016. Héctor de Isidro - hrules6872
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hrules.darealmvp.sample.presentation.presenters.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.view.MenuItem;
import android.widget.Button;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;
import com.hrules.darealmvp.sample.R;

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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
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

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

package com.hrules.darealmvp.sample.presentation.views;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.MainActivityPresenter;

@SuppressWarnings("WeakerAccess") public class MainActivityView
    extends DRAppCompatActivity<MainActivityPresenter, MainActivityPresenter.MainView>
    implements MainActivityPresenter.MainView {
  @Bind(R.id.message) TextView message;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.showMessage) Button showMessage;

  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    getPresenter().onOptionsItemSelected(item);
    return true;
  }

  @Override public void showToast() {
    Toast.makeText(this, getString(R.string.app_welcome), Toast.LENGTH_LONG).show();
  }

  public void showListActivity() {
    startActivity(new Intent(this, ListActivityView.class));
  }

  @Override public void showPagerActivity() {
    startActivity(new Intent(this, PagerActivityView.class));
  }

  @Override public void showPreferencesActivity() {
    startActivity(new Intent(this, PreferenceActivityView.class));
  }

  @Override public void showDialogFragment() {
    new DialogFragmentView().show(getSupportFragmentManager(), "DialogFragmentView");
  }

  @Override public void changeMessage(@StringRes int message) {
    this.message.setText(getString(message));
    showMessage.setEnabled(false);
  }

  @OnClick({
      R.id.showMessage, R.id.showListActivity, R.id.showPagerActivity, R.id.showDialogFragment
  }) void onClickButton(Button button) {
    getPresenter().onClickButton(button);
  }
}

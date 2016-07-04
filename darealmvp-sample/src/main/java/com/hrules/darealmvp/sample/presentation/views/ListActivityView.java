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

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.commons.DebugLog;
import com.hrules.darealmvp.sample.presentation.presenters.ListActivityPresenter;

@SuppressWarnings("WeakerAccess") public class ListActivityView
    extends DRAppCompatActivity<ListActivityPresenter, ListActivityPresenter.ListView>
    implements ListActivityPresenter.ListView {
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.container) FrameLayout container;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .replace(container.getId(), new ListFragmentView(), ListFragmentView.class.getName())
          .commit();
    }
  }

  @SuppressWarnings("ConstantConditions") @Override public void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    try {
      getSupportActionBar().setTitle(getString(R.string.activity_listTitle));
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(true);
    } catch (Exception ignored) {
    }
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_list;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void preSetContentView() {
    DebugLog.d("preSetContentView() called");
  }
}

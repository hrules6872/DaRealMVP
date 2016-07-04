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
import android.net.Uri;
import android.preference.Preference;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRPreferenceActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.PreferenceActivityPresenter;

public class PreferenceActivityView extends
    DRPreferenceActivity<PreferenceActivityPresenter, PreferenceActivityPresenter.PreferenceView>
    implements PreferenceActivityPresenter.PreferenceView, Preference.OnPreferenceClickListener {
  public static final String KEY_PREFS_GOTOREPO = "prefs_gotoRepo";

  @SuppressWarnings("WeakerAccess") @Bind(R.id.toolbar) Toolbar toolbar;

  @Override public int getLayoutResource() {
    return R.layout.activity_preference;
  }

  @Override protected int getPreferencesResource() {
    return R.xml.preferences;
  }

  @SuppressWarnings("deprecation") @Override public void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(getString(R.string.activity_preferencesTitle));
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(true);

    findPreference(KEY_PREFS_GOTOREPO).setOnPreferenceClickListener(this);
  }

  @Override public boolean onPreferenceClick(Preference preference) {
    getPresenter().onPreferenceClick(preference);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void doGotoRepo(@StringRes int url) {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(url))));
  }
}

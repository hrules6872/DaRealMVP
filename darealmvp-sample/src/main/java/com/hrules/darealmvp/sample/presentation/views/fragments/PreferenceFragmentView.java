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

package com.hrules.darealmvp.sample.presentation.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.StringRes;
import android.view.View;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.fragments.PreferenceFragmentPresenter;

public class PreferenceFragmentView extends PreferenceFragment
    implements PreferenceFragmentPresenter.PreferenceFragmentView, Preference.OnPreferenceClickListener {
  private PreferenceFragmentPresenter presenter;

  //region Composition over inheritance
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(getPreferenceResource());
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter = new PreferenceFragmentPresenter();
    getPresenter().bind(this);
    initializeViews();
  }

  private void initializeViews() {
    bindPreference(findPreference(getString(R.string.prefs_gotoRepoKey)));
  }

  private PreferenceFragmentPresenter getPresenter() {
    return presenter;
  }

  private int getPreferenceResource() {
    return R.xml.preferences;
  }
  //endregion

  private void bindPreference(Preference preference) {
    preference.setOnPreferenceClickListener(this);
  }

  @Override public boolean onPreferenceClick(Preference preference) {
    getPresenter().onPreferenceChange(preference);
    return true;
  }

  @Override public void doGotoRepo(@StringRes int url) {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(url))));
  }
}
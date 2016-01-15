package com.hrules.darealmvp.sample.views;

import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.activities.PreferencesActivity;

public class PreferenceActivityView extends PreferencesActivity
    implements Preference.OnPreferenceClickListener {
  public static final String KEY_PREFS_GOTOREPO = "prefs_gotoRepo";

  @Bind(R.id.toolbar) Toolbar toolbar;

  @Override public void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(getString(R.string.activity_preferences_title));
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(true);

    findPreference(KEY_PREFS_GOTOREPO).setOnPreferenceClickListener(this);
  }

  @Override public boolean onPreferenceClick(Preference preference) {
    getPresenter().onPreferenceClick(preference);
    return true;
  }

  @Override public void doGotoRepo(String url) {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
  }
}

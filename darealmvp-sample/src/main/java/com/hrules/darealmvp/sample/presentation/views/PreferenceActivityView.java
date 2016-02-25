package com.hrules.darealmvp.sample.presentation.views;

import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRPreferenceActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.presenters.PreferenceActivityPresenter;

public class PreferenceActivityView extends
    DRPreferenceActivity<PreferenceActivityPresenter, PreferenceActivityPresenter.IPreferenceView>
    implements PreferenceActivityPresenter.IPreferenceView, Preference.OnPreferenceClickListener {
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

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}

package com.hrules.darealmvp.sample.views;

import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.activities.PreferencesActivity;

public class PreferenceView extends PreferencesActivity {
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Override public void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(getString(R.string.activity_preferences_title));
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(true);
  }
}

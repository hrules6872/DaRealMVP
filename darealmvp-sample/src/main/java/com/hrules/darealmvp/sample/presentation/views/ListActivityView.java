package com.hrules.darealmvp.sample.presentation.views;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.activities.ListActivity;

public class ListActivityView extends ListActivity {
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

  @Override public void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(getString(R.string.activity_list_title));
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(true);
  }
}

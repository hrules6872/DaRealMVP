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

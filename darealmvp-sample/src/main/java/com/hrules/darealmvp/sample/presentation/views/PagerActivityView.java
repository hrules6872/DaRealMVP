package com.hrules.darealmvp.sample.presentation.views;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.adapters.UniversalFragmentPagerAdapter;
import com.hrules.darealmvp.sample.presentation.presenters.PagerActivityPresenter;

public class PagerActivityView
    extends DRAppCompatActivity<PagerActivityPresenter, PagerActivityPresenter.Pager>
    implements PagerActivityPresenter.Pager {
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.viewPager) ViewPager viewPager;

  @Override protected int getLayoutResource() {
    return R.layout.activity_pager;
  }

  @SuppressWarnings("ConstantConditions") @Override protected void initializeViews() {
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    try {
      getSupportActionBar().setTitle(getString(R.string.activity_pagerTitle));
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(true);
    } catch (Exception ignored) {
    }

    Fragment[] fragments = new Fragment[] {
        PageFragmentView.newInstance(1), PageFragmentView.newInstance(2),
        PageFragmentView.newInstance(3)
    };
    viewPager.setAdapter(
        new UniversalFragmentPagerAdapter(getSupportFragmentManager(), null, fragments));
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

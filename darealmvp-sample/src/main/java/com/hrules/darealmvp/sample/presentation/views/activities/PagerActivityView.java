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

package com.hrules.darealmvp.sample.presentation.views.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.adapters.UniversalFragmentPagerAdapter;
import com.hrules.darealmvp.sample.presentation.presenters.activities.PagerActivityPresenter;
import com.hrules.darealmvp.sample.presentation.views.fragments.PageFragmentView;

public class PagerActivityView extends DRAppCompatActivity<PagerActivityPresenter, PagerActivityPresenter.Pager>
    implements PagerActivityPresenter.Pager {
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.viewPager) ViewPager viewPager;

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
        PageFragmentView.newInstance(1), PageFragmentView.newInstance(2), PageFragmentView.newInstance(3)
    };
    viewPager.setAdapter(new UniversalFragmentPagerAdapter(getSupportFragmentManager(), null, fragments));
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    getPresenter().onOptionsItemSelected(item);
    return true;
  }
}

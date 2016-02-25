package com.hrules.darealmvp.sample.presentation.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class UniversalFragmentPagerAdapter extends FragmentPagerAdapter {
  private final String[] tabTitles;
  private final Fragment[] tabFragments;

  public UniversalFragmentPagerAdapter(@NonNull FragmentManager fm, String[] tabTitles,
      @NonNull Fragment[] tabFragments) {
    super(fm);
    if (tabTitles != null && tabTitles.length != tabFragments.length) {
      throw new IllegalArgumentException();
    }
    this.tabTitles = tabTitles;
    this.tabFragments = tabFragments;
  }

  @Override public Fragment getItem(int position) {
    return tabFragments[position];
  }

  @Override public int getCount() {
    return tabFragments.length;
  }

  @Override public CharSequence getPageTitle(int position) {
    return tabTitles != null ? tabTitles[position] : "";
  }
}

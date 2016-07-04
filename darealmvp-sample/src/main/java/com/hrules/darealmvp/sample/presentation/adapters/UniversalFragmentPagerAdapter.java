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

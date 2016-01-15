package com.hrules.darealmvp.sample.views;

import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.fragments.ListFragment;

public class ListFragmentView extends ListFragment {
  @Override public int getLayoutResource() {
    return R.layout.fragment_list;
  }

  @Override public void initializeViews() {
    ButterKnife.bind(getActivity());
  }
}

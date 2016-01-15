package com.hrules.darealmvp.sample.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.adapters.ListFragmentAdapter;
import com.hrules.darealmvp.sample.fragments.ListFragment;

public class ListFragmentView extends ListFragment {
  @Bind(R.id.recyclerView) RecyclerView recyclerView;

  @Override public int getLayoutResource() {
    return R.layout.fragment_list;
  }

  @Override public void initializeViews(View view) {
    ButterKnife.bind(this, view);
    recyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
  }

  @Override public void setAdapter(ListFragmentAdapter adapter) {
    recyclerView.setAdapter(adapter);
  }
}

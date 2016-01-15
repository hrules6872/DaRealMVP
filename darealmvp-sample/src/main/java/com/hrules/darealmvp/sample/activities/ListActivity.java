package com.hrules.darealmvp.sample.activities;

import android.os.Bundle;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presenters.ListActivityPresenter;

public abstract class ListActivity
    extends DRAppCompatActivity<ListActivityPresenter, ListActivityPresenter.IListView>
    implements ListActivityPresenter.IListView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new ListActivityPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_list;
  }
}

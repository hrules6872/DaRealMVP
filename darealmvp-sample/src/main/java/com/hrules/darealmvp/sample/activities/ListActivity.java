package com.hrules.darealmvp.sample.activities;

import android.os.Bundle;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presenters.ListPresenter;

public abstract class ListActivity
    extends DRAppCompatActivity<ListPresenter, ListPresenter.IListView>
    implements ListPresenter.IListView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new ListPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_list;
  }
}

package com.hrules.darealmvp.sample.view.list;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.hrules.darealmvp.DRAppCompatActivity;
import com.hrules.darealmvp.sample.R;
import com.hrules.darealmvp.sample.presentation.LiztPresenter;

public class BaseLiztActivity extends DRAppCompatActivity<LiztPresenter, LiztPresenter.LiztView>
    implements LiztPresenter.LiztView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new LiztPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_lizt;
  }

  @Override public void initializeViews() {
    ButterKnife.bind(this);
  }
}

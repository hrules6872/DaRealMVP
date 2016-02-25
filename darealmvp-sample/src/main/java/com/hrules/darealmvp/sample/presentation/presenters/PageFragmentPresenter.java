package com.hrules.darealmvp.sample.presentation.presenters;

import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;

public class PageFragmentPresenter extends DRPresenter<PageFragmentPresenter.IPage> {
  @Override public void unbind() {
    view.unbind();
    super.unbind();
  }

  public interface IPage extends DRView {
    void unbind();
  }
}

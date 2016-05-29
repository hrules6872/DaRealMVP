package com.hrules.darealmvp.sample.presentation.presenters;

import android.support.annotation.NonNull;
import com.hrules.darealmvp.DRPresenter;
import com.hrules.darealmvp.DRView;

public class DialogFragmentPresenter extends DRPresenter<DialogFragmentPresenter.Dialog> {
  @Override protected void bind(@NonNull Dialog view) {
    super.bind(view);
  }

  @Override public void unbind() {
    getView().unbind();
  }

  public interface Dialog extends DRView {
    void unbind();
  }
}

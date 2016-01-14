package com.hrules.darealmvp.sample;

import com.hrules.darealmvp.DRView;

public interface SampleView extends DRView {
  void showToast();

  void changeMessage(String message);
}

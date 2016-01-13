package com.hrules.darealmvp;

interface BaseDRPresenter<V extends DRView> {
  void bind(V DRView);

  void unbind();
}

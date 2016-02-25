package com.hrules.darealmvp;

import android.support.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

class PresenterCache {
  private static PresenterCache instance = getInstance();
  private final Map<String, DRPresenter> presenters;

  private PresenterCache() {
    presenters = new HashMap<>();
  }

  public static PresenterCache getInstance() {
    if (instance == null) {
      instance = new PresenterCache();
    }
    return instance;
  }

  @SuppressWarnings("CloneDoesntCallSuperClone") public Object clone()
      throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  public <P extends DRPresenter<V>, V extends DRView> void put(@NonNull String viewTag,
      @NonNull P presenter) {
    presenters.put(viewTag, presenter);
  }

  @SuppressWarnings("unchecked")
  public <P extends DRPresenter<V>, V extends DRView> P get(String viewTag) {
    P presenter = (P) presenters.get(viewTag);
    return presenter != null ? presenter : (P) new DummyDRPresenter();
  }

  public void removePresenter(@NonNull String viewTag) {
    presenters.remove(viewTag);
  }
}
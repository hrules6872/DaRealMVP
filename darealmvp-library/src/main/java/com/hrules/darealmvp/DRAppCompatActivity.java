package com.hrules.darealmvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class DRAppCompatActivity<P extends DRPresenter<V>, V extends DRView>
    extends AppCompatActivity implements DRView {
  
  protected P presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {
      Type mySuperclass = getClass().getGenericSuperclass();
      Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
      presenter = (P) (Class.forName(tType.toString().split(" ")[1]).newInstance());
      presenter.bind((V) this);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @NonNull public P getPresenter() {
    return presenter;
  }
}

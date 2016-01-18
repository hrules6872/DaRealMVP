DaRealMVP
=====
![image](meme.jpg)

DaRealMVP is library to bring MVP pattern (under my point of view) to Android so easy and reducing the boilerplate-code.

##How to implement

1. Activity

```java
public abstract class MainActivity
    extends DRAppCompatActivity<MainActivityPresenter, MainActivityPresenter.IMainView>
    implements MainActivityPresenter.IMainView {

  @Override public void onCreate(Bundle savedInstanceState) {
    setPresenter(new MainActivityPresenter());
    super.onCreate(savedInstanceState);
  }

  @Override public int getLayoutResource() {
    return R.layout.activity_main;
  }
}
```

2. View

```java
public class MainActivityView extends MainActivity {
  @Override public void initializeViews() {   
  }
}
```

3. Presenter

```java
public class MainActivityPresenter extends DRPresenter<MainActivityPresenter.IMainView> {
    @Override public void onSaveState(Bundle outState) {
    }

    @Override public void onLoadState(Bundle savedState) {
    }

    public interface IMainView extends DRView {
    }
}
```

##Usage

Please refer to the [sample](darealmvp-sample) for seeing it in action.

##Importing to your project

Add this dependency to your build.gradle file:
```java
dependencies {
    compile 'com.hrules:darealmvp:0.1.0'
}
```

Developed by
-------
Héctor de Isidro - hrules6872 [![Twitter](http://img.shields.io/badge/contact-@h_rules-blue.svg?style=flat)](http://twitter.com/h_rules)

License
-------
    Copyright 2016 Héctor de Isidro - hrules6872

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

DaRealMVP
=====
[![image](meme.jpg)](https://www.youtube.com/watch?v=NmRJgKbibB8)

DaRealMVP is a library to bring MVP pattern (under my point of view) to your Android apps so easy and reducing the boilerplate-code.

##How to implement

Activities (extends DRActivity / DRAppCompatActivity / DRPreferenceActivity):

1. View

    ```java
    public class MainActivityView extends DRAppCompatActivity<MainActivityPresenter, MainActivityPresenter.IMainView>
        implements MainActivityPresenter.IMainView {
        
      @Override public int getLayoutResource() {
        return R.layout.activity_main;
      }
      
      @Override public void initializeViews() {   
      }
    }
    ```

2. Presenter

    ```java
    public class MainActivityPresenter extends DRPresenter<MainActivityPresenter.IMainView> {
      public interface IMainView extends DRView {
      }
    }
    ```

Fragments (extends DRFragment / DRFragmentV4):

1. View

    ```java
    public class MainFragmentView extends DRFragmentV4<MainFragmentPresenter, MainFragmentPresenter.IMainView>
        implements MainFragmentPresenter.IMainView {

        @Override public int getLayoutResource() {
          return R.layout.fragment_main;
        }

        @Override public void initializeViews() {
        }
    }
    ```

2. Presenter

    ```java
    public class MainFragmentPresenter extends DRPresenter<MainFragmentPresenter.IMainView> {
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
  compile 'com.hrules:darealmvp:0.5.0'
}
```


Developed by
-------
Héctor de Isidro - hrules6872 [![Twitter](http://img.shields.io/badge/contact-@hector6872-blue.svg?style=flat)](http://twitter.com/hector6872)

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

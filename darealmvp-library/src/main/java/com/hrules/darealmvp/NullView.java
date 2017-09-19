/*
 * Copyright (c) 2017. Héctor de Isidro - hrules6872
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hrules.darealmvp;

import android.support.annotation.NonNull;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static com.hrules.darealmvp.NullViewDefaults.defaultValue;
import static java.lang.reflect.Proxy.newProxyInstance;

class NullView {
  private static final InvocationHandler DEFAULT_VALUE = new DefaultValueInvocationHandler();

  private NullView() {
  }

  @SuppressWarnings("unchecked") static <T> T of(@NonNull Class<T> interfaceClass) {
    return (T) newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass }, DEFAULT_VALUE);
  }

  private static class DefaultValueInvocationHandler implements InvocationHandler {
    @Override public Object invoke(@NonNull Object proxy, @NonNull Method method, @NonNull Object[] args) throws Throwable {
      return defaultValue(method.getReturnType());
    }
  }
}
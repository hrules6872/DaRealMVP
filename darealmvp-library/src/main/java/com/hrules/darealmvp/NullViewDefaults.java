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
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

class NullViewDefaults {
  private static final Map<Class<?>, Object> DEFAULTS = unmodifiableMap(new HashMap<Class<?>, Object>() {
    {
      put(Boolean.TYPE, false);
      put(Byte.TYPE, (byte) 0);
      put(Character.TYPE, '\000');
      put(Double.TYPE, 0.0d);
      put(Float.TYPE, 0.0f);
      put(Integer.TYPE, 0);
      put(Long.TYPE, 0L);
      put(Short.TYPE, (short) 0);
    }
  });

  private NullViewDefaults() {
  }

  @SuppressWarnings("unchecked") static <T> T defaultValue(@NonNull Class<T> type) {
    return (T) DEFAULTS.get(type);
  }
}
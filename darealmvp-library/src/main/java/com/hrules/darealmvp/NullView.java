package com.hrules.darealmvp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static com.hrules.darealmvp.NullViewDefaults.defaultValue;
import static java.lang.reflect.Proxy.newProxyInstance;

class NullView {
  private static final InvocationHandler DEFAULT_VALUE = new DefaultValueInvocationHandler();

  private NullView() {
  }

  @SuppressWarnings("unchecked") public static <T> T of(Class<T> interfaceClass) {
    return (T) newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
        DEFAULT_VALUE);
  }

  private static class DefaultValueInvocationHandler implements InvocationHandler {
    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      return defaultValue(method.getReturnType());
    }
  }
}
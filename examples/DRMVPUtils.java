public class DRMVPUtils {
  @SuppressWarnings("unchecked") public static <P> P getDeclaredPresenter(@NonNull Class clazz) {
    Type genericSuperclass;
    for (; ; ) {
      genericSuperclass = clazz.getGenericSuperclass();
      if (genericSuperclass instanceof ParameterizedType) {
        break;
      }
      clazz = clazz.getSuperclass();
    }
    Type presenterClass = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    try {
      return (P) Class.forName(presenterClass.toString().split(" ")[1]).newInstance();
    } catch (java.lang.InstantiationException | IllegalAccessException | ClassNotFoundException e) {
      throw new IllegalArgumentException();
    }
  }
}
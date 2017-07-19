@SuppressWarnings("unchecked") public abstract class DRMVPFragmentV4<P extends DRMVPPresenter<V>, V extends DRMVPView> extends Fragment {
  private P presenter;

  @CallSuper @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(getLayoutResId(), container, false);
  }

  @CallSuper @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    presenter = MVPUtils.getDeclaredPresenter(getClass());
    presenter.bind((V) this);
  }

  @CallSuper @Override public void onDestroyView() {
    super.onDestroyView();
    presenter.unbind();
  }

  protected @NonNull P getPresenter() {
    return presenter;
  }

  protected abstract @LayoutRes int getLayoutResId();
}
@SuppressWarnings("unchecked") public abstract class DRMVPAppCompatActivity<P extends DRMVPPresenter<V>, V extends DRMVPView>
    extends AppCompatActivity {
  private P presenter;

  @CallSuper @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    presenter = MVPUtils.getDeclaredPresenter(getClass());
    presenter.bind((V) this);
  }

  @CallSuper @Override protected void onDestroy() {
    super.onDestroy();
    presenter.unbind();
  }

  protected @NonNull P getPresenter() {
    return presenter;
  }

  protected abstract @LayoutRes int getLayoutResId();
}
public abstract class DRMVPFrameLayoutView<P extends DRMVPPresenter<V>, V extends DRMVPView> extends FrameLayout {
  private P presenter;

  public DRMVPFrameLayoutView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public DRMVPFrameLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @SuppressWarnings("unchecked") private void init() {
    presenter = DRMVPUtils.getDeclaredPresenter(getClass());
    presenter.bind((V) this);

    inflate(getContext(), getLayoutResId(), this);
  }

  @CallSuper @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    presenter.unbind();
  }

  protected @NonNull P getPresenter() {
    return presenter;
  }

  protected abstract @LayoutRes int getLayoutResId();
}
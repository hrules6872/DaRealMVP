package com.hrules.darealmvp.sample.presentation.fragments;

import com.hrules.darealmvp.DRFragment;
import com.hrules.darealmvp.sample.presentation.presenters.ListFragmentPresenter;

public abstract class ListFragment
    extends DRFragment<ListFragmentPresenter, ListFragmentPresenter.IListFragmentView>
    implements ListFragmentPresenter.IListFragmentView {
}

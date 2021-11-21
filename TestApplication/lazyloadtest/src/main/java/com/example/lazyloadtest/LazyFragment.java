package com.example.lazyloadtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyFragment extends Fragment {
    private FragmentDelegater mFragmentDelegater;
    View rootView;
    boolean isViewCreated = false; //根布局是否已经创建
    boolean currentVisibleState = false; // 当前View可见状态

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        initView(rootView);
        isViewCreated = true;
        if (getUserVisibleHint()) {
            disPatchUserVisibleHint(true);
        }
        return rootView;
    }

    protected abstract void initView(View rootView);

    protected abstract int getLayoutRes();

    //
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isViewCreated) {
            return;
        }
        if (!currentVisibleState && isVisibleToUser) {
            disPatchUserVisibleHint(true);
        } else if (currentVisibleState && !isVisibleToUser) {
            disPatchUserVisibleHint(false);
        }
    }

    //用于统一控制界面数据加载
    private void disPatchUserVisibleHint(boolean visibleState) {
        if (visibleState == currentVisibleState) {
            return;
        }
        currentVisibleState = visibleState;
        if (visibleState) {
            onFragmenLoad();
        } else {
            onFragmentLoadStop();
        }
    }

    //停止数据加载
    public void onFragmentLoadStop() {


    }

    //加载数据
    public void onFragmenLoad() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!currentVisibleState && getUserVisibleHint()) {
            disPatchUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (currentVisibleState && !getUserVisibleHint()) {
            disPatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        currentVisibleState = false;
        isViewCreated = false;
    }

    public void setFragmentDelegater(FragmentDelegater fragmentDelegater) {


    }
}

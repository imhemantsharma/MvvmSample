package com.sharma.mvvmsample.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import okhttp3.ResponseBody;


/**
 * Created by Hemant Sharma on 23-02-20.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements BaseNavigator {

    private BaseActivity mActivity;
    private View mRootView;
    private T mViewDataBinding;
    private V mViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onDetach() {
        mViewDataBinding = null;
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                mActivity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) mActivity.hideKeyboard();
    }

    @Override
    public boolean isNetworkConnected() {
        return mActivity.isNetworkConnected();
    }

    @Override
    public void openActivityOnTokenExpire() {
        if (mActivity != null) mActivity.openActivityOnTokenExpire();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (mActivity != null) mActivity.requestPermissionsSafely(permissions, requestCode);
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) mActivity.hideLoading();
    }

    @Override
    public void showLoading() {
        if (mActivity != null) mActivity.showLoading();
    }

    @Override
    public void replaceFragment(@NonNull Fragment fragmentHolder, int layoutId) {
        try {
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            String fragmentName = fragmentHolder.getClass().getName();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(layoutId, fragmentHolder, fragmentName);
            //fragmentTransaction.addToBackStack(fragmentName);
            fragmentTransaction.commit();
            hideKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFragment(@NonNull Fragment fragment, int layoutId, boolean addToBackStack) {
        try {
            String fragmentName = fragment.getClass().getName();

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.add(layoutId, fragment, fragmentName);
            if (addToBackStack) fragmentTransaction.addToBackStack(fragmentName);
            fragmentTransaction.commit();

            hideKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showToast(String msg) {
        if (mActivity != null) mActivity.showToast(msg);
    }

    @Override
    public void showToast(@StringRes int msg) {
        if (mActivity != null) mActivity.showToast(msg);
    }

    @Override
    public void handleApiFailure(@NonNull Throwable t) {
        if (mActivity != null) mActivity.handleApiFailure(t);
    }

    @Override
    public void handleApiError(ResponseBody response) {
        if (mActivity != null) mActivity.handleApiError(response);
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}

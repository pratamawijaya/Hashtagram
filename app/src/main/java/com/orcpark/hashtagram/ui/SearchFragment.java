package com.orcpark.hashtagram.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.orcpark.hashtagram.R;

/**
 * Created by orcpark on 14. 11. 9..
 */
public class SearchFragment extends BaseFragment implements TextView.OnEditorActionListener{

    public interface OnSearchListener {
        public void onSearch(String search);
    }

    private OnSearchListener mOnSearchListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mActivity instanceof OnSearchListener) {
            mOnSearchListener = (OnSearchListener) mActivity;
        }
    }

    private EditText mEtSearch;
    private InputMethodManager mInputMethodManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        mEtSearch = (EditText) rootView.findViewById(R.id.et_search);
        mEtSearch.setOnEditorActionListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mEtSearch.requestFocus();
        mInputMethodManager.showSoftInput(mEtSearch, 0);
    }

    @Override
    public void onDetach() {
        mOnSearchListener = null;
        mInputMethodManager.hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0);
        mInputMethodManager = null;
        super.onDetach();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            mInputMethodManager.hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0);
            if (mOnSearchListener != null) {
                mOnSearchListener.onSearch(v.getText().toString());
            }
            return true;
        }
        return false;
    }

}

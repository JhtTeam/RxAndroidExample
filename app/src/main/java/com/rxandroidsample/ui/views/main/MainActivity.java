package com.rxandroidsample.ui.views.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rxandroidsample.R;
import com.rxandroidsample.data.model.Ribot;
import com.rxandroidsample.presentation.presenter.MainPresenter;
import com.rxandroidsample.ui.adapters.RibotAdapter;
import com.rxandroidsample.ui.base.BaseView;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends BaseView<MainView, MainPresenter> implements MainView {
    private RecyclerView mRecyclerView;
    private RibotAdapter mAdapter;

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new RibotAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }


    public void showLoading() {

    }
    public void dismissLoading() {

    }
    public void displayRibots(List<Ribot> ribots) {
        mAdapter.replace(ribots);
    }
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


}
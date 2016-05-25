package com.rxandroidsample.ui.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rxandroidsample.R;
import com.rxandroidsample.data.model.Profile;
import com.rxandroidsample.data.model.Ribot;
import com.rxandroidsample.ui.adapters.RibotAdapter;
import com.rxandroidsample.ui.fragments.MainWorkerFragment;
import com.rxandroidsample.ui.interfaces.OnObservableRetrievedListener;

import java.util.List;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements OnObservableRetrievedListener {

    //    @BindView(R.id.textView)
//    TextView mTextView;
    private RecyclerView mRecyclerView;
    private RibotAdapter mAdapter;
    private CompositeSubscription mSubscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        mTextView = (TextView) findViewById(R.id.textView);
//        mTextView.setText("");



        MainWorkerFragment.create(getSupportFragmentManager());

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mAdapter = new RibotAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        Log.d("RxAndroidSample", "onPause");
        if (mSubscriptions != null) {
            mSubscriptions.unsubscribe();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RxAndroidSample", "onResume");
        if (mSubscriptions == null || mSubscriptions.isUnsubscribed()) {
            mSubscriptions = new CompositeSubscription();
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("RxAndroidSample", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onObservableRetrieved(@NonNull Observable<List<Ribot>> observable) {
        mSubscriptions.add(observable.subscribe(new Subscriber<List<Ribot>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError " + e, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Ribot> items) {
                mAdapter.add(items);
            }
        }));
    }
}
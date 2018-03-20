package com.example.martinb.newsapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.martinb.newsapp.R;
import com.example.martinb.newsapp.model.Article;
import com.example.martinb.newsapp.model.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements DialogFragment.OnFragmentInteractionListener,HasActivityInjector ,OnSourceSelected, PresenterContract.View,HasSupportFragmentInjector {

    private ArticleAdapter mAdapter;
    private List<Article> articleList = new ArrayList<>();
    private String source;

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    public MainPresenter mainPresenter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter.attachView(this);
        source = mainPresenter.restoreSource();

        if (source == null || source.equals("fox-sports")) {
            selectSource();
        } else {
            this.setTitle(source);
        }

        View.OnClickListener itemListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(articleList.get(recyclerView.getChildAdapterPosition(view)).getUrl()));
                startActivity(browserIntent);
            }
        };
        mAdapter = new ArticleAdapter(articleList, getApplicationContext());
        mAdapter.setOnItemClick(itemListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mainPresenter.updateArticles(source);
    }

    @Override
    public void updateArticles(List<Article> articles) {
        mAdapter.update(articles);
        articleList = articles;
    }

    @Override
    public void updateSources(List<Source> sources) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem option_menu) {
        int id_back = option_menu.getItemId();

        if (id_back == R.id.change) {
            selectSource();
        }

        return true;
    }

    private void selectSource() {
        mainPresenter.detachView();
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.setListener(this);
        dialogFragment.show(getSupportFragmentManager(), "sourceFragment");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onSourceSelected(String source) {
        this.source = source;
        mainPresenter.attachView(this);
        mainPresenter.updateArticles(this.source);
        this.setTitle(source);
    }


    @Override
    public void onFragmentInteraction() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, DialogFragment.newInstance()).commit();
    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onArticleError() {
        Toast.makeText(getApplicationContext(),"Error while charging articles",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSourceError() {
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }


}

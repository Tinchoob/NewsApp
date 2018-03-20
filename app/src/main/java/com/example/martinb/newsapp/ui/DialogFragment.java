package com.example.martinb.newsapp.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;


public class DialogFragment extends android.support.v4.app.DialogFragment implements PresenterContract.View,HasActivityInjector,HasSupportFragmentInjector {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private List<Source> sourceList = new ArrayList<>();
    private SourceAdapter sAdapter;
    private String idSource;
    private int position;
    private OnSourceSelected onSelectedSource;
    private OnFragmentInteractionListener mListener;

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    public DialogPresenter dialogPresenter;

    public DialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DialogFragment newInstance() {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.recycleview) RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this,view);
        dialogPresenter.attachView(this);
        sAdapter = new SourceAdapter(sourceList);
        View.OnClickListener itemListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = recyclerView.getChildAdapterPosition(view);
                idSource = sourceList.get(position).getId();

                if (onSelectedSource != null) {
                    onSelectedSource.onSourceSelected(idSource);
                }
                dialogPresenter.saveSource(idSource);
                dialogPresenter.detachView();
                dismiss();
            }
        };
        sAdapter.setOnItemClick(itemListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(sAdapter);
        dialogPresenter.updateSources();
        return view;
    }

    @Override
    public void updateSources(List<Source> sources) {
        sAdapter.update(sources);
        sourceList = sources;
    }

    public void setListener(OnSourceSelected onSourceSelectedSource) {
        this.onSelectedSource = onSourceSelectedSource;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void openActivityOnTokenExpire() {
    }

    @Override
    public void onArticleError() {
    }

    @Override
    public void onSourceError() {
        Toast.makeText(getContext(),"Error while charging new sources, try again later",Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void onError(String message) {
        dismiss();
    }

    @Override
    public void showMessage(String message) {
    }


    @Override
    public void updateArticles(List<Article> articles) {
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

}

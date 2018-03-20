package com.example.martinb.newsapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.martinb.newsapp.model.Source;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by martinb on 2/21/2018.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {

    private List<Source> list;
    private View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.text1) TextView name;

        public View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this,view);
        }
    }

    public SourceAdapter(List<Source> list) {
        this.list = list;
    }

    public void setOnItemClick(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public SourceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new SourceAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(SourceAdapter.ViewHolder holder, int position) {
        Source source = list.get(position);
        holder.view.setTag(source);

        if (this.listener != null) {
            holder.view.setOnClickListener(this.listener);
        }

        holder.name.setText(source.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<Source> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}
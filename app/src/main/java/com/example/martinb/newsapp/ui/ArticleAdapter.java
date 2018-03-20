package com.example.martinb.newsapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinb.newsapp.R;
import com.example.martinb.newsapp.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by martinb on 2/21/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {


    private List<Article> list;
    private Context context;
    private View.OnClickListener listener;


    public ArticleAdapter(List<Article> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setOnItemClick(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = list.get(position);
        holder.view.setTag(article);

        if (this.listener != null) {
            holder.view.setOnClickListener(this.listener);
        }

        holder.title.setText(article.getTitle());
        Picasso.with(context)
                .load(article.getUrlToImage())
                .into(holder.thumbnail);
        holder.description.setText(article.getDescription());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<Article> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}

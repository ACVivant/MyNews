package com.vivant.annecharlotte.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.vivant.annecharlotte.mynews.Models.NYTTopStoriesArticles;
import com.vivant.annecharlotte.mynews.Models.ResultTopStories;
import com.vivant.annecharlotte.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anne-Charlotte Vivant on 18/12/2018.
 */
public class ListOfArticlesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.fragment_item_title) TextView titleTextView;
    @BindView(R.id.fragment_item_date) TextView dateTextView;
    @BindView(R.id.fragment_item_section) TextView sectionTextView;
    @BindView(R.id.fragment_item_image) ImageView imageView;


    public ListOfArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNYTArticles(ResultTopStories NYTArticle, RequestManager glide){
        this.titleTextView.setText(NYTArticle.getTitle());
        this.dateTextView.setText(NYTArticle.getPublishedDate());
        this.sectionTextView.setText((NYTArticle.getSection()));

        glide.load(NYTArticle.getMultimedia().get(0).getUrl()).into(imageView);
    }
}

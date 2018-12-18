package com.vivant.annecharlotte.mynews.Views;

import android.graphics.drawable.Icon;
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

    //public void updateWithNYTArticles(ResultTopStories NYTArticle){
       public void updateWithNYTArticles(ResultTopStories NYTArticle, RequestManager glide){
        this.titleTextView.setText(NYTArticle.getTitle());

        String date = NYTArticle.getPublishedDate().substring(0,9);
        this.dateTextView.setText(date);

        String section = NYTArticle.getSection() ;
        if(NYTArticle.getSubsection().length()> 0){
                section += ">" +NYTArticle.getSubsection();}
        this.sectionTextView.setText(section);

        if (NYTArticle.getMultimedia().size()>0){  // ici il faut gérer les cas où Multimedia est un tableau vide
        glide.load(NYTArticle.getMultimedia().get(0).getUrl()).into(imageView);}
        else this.imageView.setImageResource(R.drawable.ic_menu_camera);

    }
}

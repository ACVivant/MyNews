package com.vivant.annecharlotte.mynews.Views;

import android.graphics.drawable.Icon;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.vivant.annecharlotte.mynews.Models.NYTTopStoriesArticles;
import com.vivant.annecharlotte.mynews.Models.ResultArticles;
import com.vivant.annecharlotte.mynews.Models.ResultTopStories;
import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.Utils.DateConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    String apiTag;

    public ListOfArticlesViewHolder(View itemView, final ListOfArticlesAdapter.OnItemClickedListener listener, String apiTag) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) {
                    int position = getAdapterPosition();
                    if (position!= RecyclerView.NO_POSITION) {
                        listener.OnItemClicked(position);
                    }
                }
            }
        });
        this.apiTag = apiTag;
    }


       public void updateWithNYTArticles(ResultArticles NYTArticle, RequestManager glide){
        this.titleTextView.setText(NYTArticle.getTitle());

        String date = NYTArticle.getPublishedDate().substring(0,10);
        date = DateConverter.getPublished_date_converted(date);
           this.dateTextView.setText(date);

        String section = NYTArticle.getSection() ;

        if (apiTag=="TOPSTORIES") {  // ici on gère le fait qu'il n'y a pas de subsection dans MostPopular
        if(NYTArticle.getSubsection().length()> 0){
                section += ">" +NYTArticle.getSubsection();
        }
        }
        this.sectionTextView.setText(section);

           if (apiTag=="TOPSTORIES") {
        if (NYTArticle.getMultimedia().size()>0){  // ici il faut gérer les cas où Multimedia est un tableau vide
        glide.load(NYTArticle.getMultimedia().get(0).getUrl()).into(imageView);}
        else
               this.imageView.setImageResource(R.drawable.ic_menu_camera);
           }

           if (apiTag=="MOSTPOPULAR") {
               if (NYTArticle.getMedia().size()>0){  // ici il faut gérer les cas où Media est un tableau vide
                   glide.load(NYTArticle.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageView);}
               else
                   this.imageView.setImageResource(R.drawable.ic_menu_camera);
           }
    }
}

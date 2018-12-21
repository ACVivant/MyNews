package com.vivant.annecharlotte.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.Models.Response;
import com.vivant.annecharlotte.mynews.Models.ResultArticles;
import com.vivant.annecharlotte.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Anne-Charlotte Vivant on 18/12/2018.
 */
public class ListOfSearchedArticlesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.fragment_item_title) TextView titleTextView;
    @BindView(R.id.fragment_item_date) TextView dateTextView;
    @BindView(R.id.fragment_item_section) TextView sectionTextView;
    @BindView(R.id.fragment_item_image) ImageView imageView;

    String apiTag;

    public ListOfSearchedArticlesViewHolder(View itemView, final ListOfSearchedArticlesAdapter.OnItemClickedListener listener, String apiTag) {
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

    public void updateWithNYTArticles(Doc NYTArticle, RequestManager glide){
        this.titleTextView.setText(NYTArticle.getSnippet());

        String date = NYTArticle.getPubDate().substring(0,10);
        this.dateTextView.setText(date);

        String section = NYTArticle.getSectionName() ;
        this.sectionTextView.setText(section);

        if (NYTArticle.getMultimedia().get(2).getUrl().length()>0){  // ici il faut gérer les cas où Multimedia est un tableau vide
              glide.load("https://static01.nyt.com/"+NYTArticle.getMultimedia().get(2).getUrl()).into(imageView);}

        else
            this.imageView.setImageResource(R.drawable.ic_menu_camera);

    }
}

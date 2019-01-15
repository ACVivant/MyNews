package com.vivant.annecharlotte.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.vivant.annecharlotte.mynews.Models.ResultArticles;
import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.Utils.DateConverter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder linked with ListOfArticlesAdapter (TOP Stories and MostPopular API)
 */
public class ListOfArticlesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.fragment_item_title) TextView titleTextView;
    @BindView(R.id.fragment_item_date) TextView dateTextView;
    @BindView(R.id.fragment_item_section) TextView sectionTextView;
    @BindView(R.id.fragment_item_image) ImageView imageView;

    private String apiTag;

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
                    // change the color when click on item is intercepted
                    dateTextView.setTextColor(dateTextView.getResources().getColor(R.color.colorPrimaryDark));
                    sectionTextView.setTextColor(sectionTextView.getResources().getColor(R.color.colorPrimaryDark));
                    titleTextView.setTextColor(titleTextView.getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });
        this.apiTag = apiTag;
    }

    public void updateWithNYTArticles(ResultArticles NYTArticle, RequestManager glide){
        // Principal title
        this.titleTextView.setText(NYTArticle.getTitle());

        // Section title if possible
        String section = NYTArticle.getSection() ;
        // there isn't subsections in most popular articles, so we put the subseciton title only for topstories articles
        if (apiTag=="TOPSTORIES") {
            if(NYTArticle.getSubsection().length()> 0){
                section += ">" +NYTArticle.getSubsection();
            }
        }
        this.sectionTextView.setText(section);

        // Date
        String date = NYTArticle.getPublishedDate().substring(0,10);
        date = DateConverter.getPublished_date_converted(date);
        this.dateTextView.setText(date);

        // Images
        // sometimes there isn't images, so we use an icon instead
        if (apiTag.equals("TOPSTORIES")) {
            if (NYTArticle.getMultimedia().size()>0){
                glide.load(NYTArticle.getMultimedia().get(0).getUrl()).into(imageView);}
            else
                this.imageView.setImageResource(R.drawable.ic_menu_camera);
        }
        if (apiTag.equals("MOSTPOPULAR")) {
            if (NYTArticle.getMedia().size()>0){  // ici il faut gérer les cas où Media est un tableau vide
                glide.load(NYTArticle.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageView);}
            else
                this.imageView.setImageResource(R.drawable.ic_menu_camera);
        }
    }
}

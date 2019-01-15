package com.vivant.annecharlotte.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.vivant.annecharlotte.mynews.Models.Doc;
import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.Utils.DateConverter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder linked with ListOfSearchedArticlesAdapter (Search API)
 */
public class ListOfSearchedArticlesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.fragment_item_title) TextView titleTextView;
    @BindView(R.id.fragment_item_date) TextView dateTextView;
    @BindView(R.id.fragment_item_section) TextView sectionTextView;
    @BindView(R.id.fragment_item_image) ImageView imageView;

    private String apiTag;

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
                    // change the color when click on item is intercepted
                    dateTextView.setTextColor(dateTextView.getResources().getColor(R.color.colorPrimaryDark));
                    sectionTextView.setTextColor(sectionTextView.getResources().getColor(R.color.colorPrimaryDark));
                    titleTextView.setTextColor(titleTextView.getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });
        this.apiTag = apiTag;
    }

    public void updateWithNYTArticles(Doc NYTArticle, RequestManager glide){
        // Title
        this.titleTextView.setText(NYTArticle.getSnippet());
        String section = NYTArticle.getSectionName() ;
        this.sectionTextView.setText(section);

        // Date
        String date = NYTArticle.getPubDate().substring(0,10);
        date = DateConverter.getPublished_date_converted(date);
        this.dateTextView.setText(date);

        // Images
        // sometimes there isn't images, so we use an icon instead
        if (NYTArticle.getMultimedia().size()>0){
              glide.load("https://static01.nyt.com/"+NYTArticle.getMultimedia().get(2).getUrl()).into(imageView);}
        else
            this.imageView.setImageResource(R.drawable.ic_menu_camera);

    }
}

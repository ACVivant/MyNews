package com.vivant.annecharlotte.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.vivant.annecharlotte.mynews.Models.NYTTopStoriesArticles;
import com.vivant.annecharlotte.mynews.Models.ResultTopStories;
import com.vivant.annecharlotte.mynews.R;
import com.vivant.annecharlotte.mynews.TopStoriesPageFragment;

import java.util.List;

/**
 * Created by Anne-Charlotte Vivant on 18/12/2018.
 */
public class ListOfArticlesAdapter extends RecyclerView.Adapter<ListOfArticlesViewHolder> {

    private List<ResultTopStories> listOfArticles;
    private RequestManager glide;

    // CONSTRUCTOR
    //public ListOfArticlesAdapter(List<ResultTopStories> listOfArticles) {
        public ListOfArticlesAdapter(List<ResultTopStories> listOfArticles, RequestManager glide) {
        this.listOfArticles = listOfArticles;
        this.glide = glide;
    }

    @Override
    public ListOfArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);
        return new ListOfArticlesViewHolder(view);
    }

    // UPDATE VIEW HOLDER
    @Override
    public void onBindViewHolder(ListOfArticlesViewHolder viewHolder, int position) {
        viewHolder.updateWithNYTArticles(this.listOfArticles.get(position), this.glide);
        //viewHolder.updateWithNYTArticles(this.listOfArticles.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.listOfArticles.size();
    }
}
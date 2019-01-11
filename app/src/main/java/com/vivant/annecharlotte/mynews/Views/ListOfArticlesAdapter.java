package com.vivant.annecharlotte.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.vivant.annecharlotte.mynews.Models.ResultArticles;
import com.vivant.annecharlotte.mynews.R;

import java.util.List;

/**
 * Created by Anne-Charlotte Vivant on 18/12/2018.
 */
public class ListOfArticlesAdapter extends RecyclerView.Adapter<ListOfArticlesViewHolder> {

    private List<ResultArticles> listOfArticles;
    private RequestManager glide;
    private OnItemClickedListener mListener;
    private String apiTag;

    public interface OnItemClickedListener{
        void OnItemClicked(int position);
    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        mListener = listener;
    }

    // CONSTRUCTOR
        public ListOfArticlesAdapter(List<ResultArticles> listOfArticles, RequestManager glide, String apiTag) {
        this.listOfArticles = listOfArticles;
        this.glide = glide;
        this.apiTag =  apiTag;
    }

    @Override
    public ListOfArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);
        return new ListOfArticlesViewHolder(view, mListener, apiTag);
    }

    // UPDATE VIEW HOLDER
    @Override
    public void onBindViewHolder(ListOfArticlesViewHolder viewHolder, int position) {
        viewHolder.updateWithNYTArticles(this.listOfArticles.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.listOfArticles.size();
    }
}
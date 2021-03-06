package com.vivant.annecharlotte.mynews.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.vivant.annecharlotte.mynews.models.Doc;
import com.vivant.annecharlotte.mynews.R;

import java.util.List;

/**
 * Link between articles position and content for Search Articles API
 */
public class ListOfSearchedArticlesAdapter extends RecyclerView.Adapter<ListOfSearchedArticlesViewHolder> {

    private List<Doc> listOfArticles;
    private RequestManager glide;
    private OnItemClickedListener mListener;
    private String apiTag;

    public interface OnItemClickedListener{
        void OnItemClicked(int position);
    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        mListener = listener;
    }

    // constructor
    public ListOfSearchedArticlesAdapter(List<Doc> listOfArticles, RequestManager glide, String apiTag) {
        this.listOfArticles = listOfArticles;
        this.glide = glide;
        this.apiTag =  apiTag;
    }

    @Override
    public ListOfSearchedArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creates view holder and inflates its xml layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);
        return new ListOfSearchedArticlesViewHolder(view, mListener, apiTag);
    }

    // update view holder
    @Override
    public void onBindViewHolder(ListOfSearchedArticlesViewHolder viewHolder, int position) {
        viewHolder.updateWithNYTArticles(this.listOfArticles.get(position), this.glide);
    }

    // return the total count of items in the list
    @Override
    public int getItemCount() {
        return this.listOfArticles.size();
    }
}
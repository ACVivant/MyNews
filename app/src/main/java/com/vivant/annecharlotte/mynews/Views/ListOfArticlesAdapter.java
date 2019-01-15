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
 * Link between articles position and content for Top Stories and Most Popular API
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

    // Constructor
    public ListOfArticlesAdapter(List<ResultArticles> listOfArticles, RequestManager glide, String apiTag) {
        this.listOfArticles = listOfArticles;
        this.glide = glide;
        this.apiTag =  apiTag;
    }

    @Override
    public ListOfArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creates view holder and inflates its xml layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);
        return new ListOfArticlesViewHolder(view, mListener, apiTag);
    }

    // update view holder
    @Override
    public void onBindViewHolder(ListOfArticlesViewHolder viewHolder, int position) {
        viewHolder.updateWithNYTArticles(this.listOfArticles.get(position), this.glide);
    }

    // return the total count of items in the list
    @Override
    public int getItemCount() {
        return this.listOfArticles.size();
    }
}
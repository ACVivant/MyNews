package com.vivant.annecharlotte.mynews;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Anne-Charlotte Vivant on 10/01/2019.
 */
public class AsyncTaskSearch extends AsyncTask<Void, Integer, String> {
   ResultsSearchNotification mResultsSearchNotification;
    int numberArticles =0;
    String searchResponse = "";
    String TAG = "ASYNCTASK";

    @Override
    protected String doInBackground(Void... voids) {
       /* Log.d(TAG, "search: juste avant la recherche de nouveaux articles");
        mResultsSearchNotification = new ResultsSearchNotification();
        numberArticles = mResultsSearchNotification.getNumberArticles();
        Log.d(TAG, "search: numberArticles" + numberArticles);
        Log.d(TAG, "search: juste après la recherche de nouveaux articles");*/
        return searchResponse = "OK";
    }

}

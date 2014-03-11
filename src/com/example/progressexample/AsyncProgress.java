package com.example.progressexample;

import android.os.AsyncTask;
/**
 * 
 * @author Oleksandr Shpota
 * AsyncProgress is able to run heavy action in parallel thread
 */
public class AsyncProgress extends AsyncTask<Void, Void, Void> {

    private ProgressListener listener;

    static final int MIN = 0;
    static final int MAX = 100;
    static final int ERROR_CODE = -1;

    public AsyncProgress(ProgressListener listener) {
        this.listener = listener;
    }

    /**
     * All the heavy actions will be performed in this method
     */
    @Override
    protected Void doInBackground(Void... arg0) {
        int i = MIN;
        while (i <= MAX) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // send error message
                listener.onProgress(ERROR_CODE, e.getMessage());
            }
            // send current progress to View
            listener.onProgress(i++, null);
        }
        return null;
    }

}

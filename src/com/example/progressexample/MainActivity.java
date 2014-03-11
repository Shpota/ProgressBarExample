package com.example.progressexample;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import static com.example.progressexample.AsyncProgress.*;

/**
 * 
 * @author Oleksandr Shpota 
 * Main activity should view all progress changes
 */
public class MainActivity extends Activity implements ProgressListener {

    private ProgressBar progressBar;

    private Handler progressHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialization of progress bar
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onProgress(final int progress, String message) {
        if (progress == ERROR_CODE) {
            // just show error message
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)
                    .show();
        } else {
            // we should post progress to View
            progressHandler.post(new Runnable() {
                public void run() {
                    // set current progress value
                    progressBar.setProgress(progress);
                }
            });
        }
    }

    /**
     * Runs the heavy task in another thread. Posts the results to current
     * activity.
     * 
     * @param v
     */
    public void runProgress(View v) {
        Void[] params = {};
        // we send just empty array
        new AsyncProgress(this).execute(params);
    }

}

package com.example.progressexample;

/**
 * 
 * @author Oleksandr Shpota 
 * Listens the progress changes and sets they to the View
 * elements
 */
public interface ProgressListener {
    void onProgress(int progress, String message);
}

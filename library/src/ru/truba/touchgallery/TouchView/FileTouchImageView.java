/*
 Copyright (c) 2012 Roman Truba

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial
 portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package ru.truba.touchgallery.TouchView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class FileTouchImageView extends RelativeLayout {
    protected ProgressBar mProgressBar;
    protected TouchImageView mImageView;

    protected Context mContext;

    public FileTouchImageView(Context ctx)
    {
        super(ctx);
        mContext = ctx;
        init();

    }
    public FileTouchImageView(Context ctx, AttributeSet attrs)
    {
        super(ctx, attrs);
        mContext = ctx;
        init();
    }
    public TouchImageView getImageView() { return mImageView; }

    @SuppressWarnings("deprecation")
    protected void init() {
        mImageView = new TouchImageView(mContext);
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mImageView.setLayoutParams(params);
        this.addView(mImageView);
        mImageView.setVisibility(GONE);

        mProgressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleSmall);
        params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.setMargins(30, 0, 30, 0);
        mProgressBar.setLayoutParams(params);
        mProgressBar.setIndeterminate(true);
        this.addView(mProgressBar);
    }

    public void setUrl(String imagePath)
    {
        new ImageLoadTask().execute(imagePath);
    }
    //No caching load
    public class ImageLoadTask extends AsyncTask<String, Integer, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings) {
            String path = strings[0];
            Bitmap bm = null;
            try {
                
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path), 8192);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
            mImageView.setVisibility(VISIBLE);
            mProgressBar.setVisibility(GONE);
        }
    }
}

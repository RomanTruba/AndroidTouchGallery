/*
 Copyright (c) 2013 Roman Truba

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
package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ru.truba.touchgallery.TouchView.FileTouchImageView;

/**
 Class wraps file paths to adapter, then it instantiates {@link ru.truba.touchgallery.TouchView.FileTouchImageView} objects to paging up through them.
 */
public class InfinityFilePagerAdapter extends BasePagerAdapter {

    private int TOTAL_PAGES = -1;
    private int MIN_LOOPS = 1000;
    // You can choose a bigger number for LOOPS, but you know, nobody will fling
    // more than 1000 times just in order to test your "infinite" ViewPager :D
    public int FIRST_PAGE = 1;

    private ImageView.ScaleType mScaleType = null;

    public InfinityFilePagerAdapter(Context context, List<String> resources) {
		super(context, resources);
        TOTAL_PAGES = resources.size();
        FIRST_PAGE = TOTAL_PAGES * MIN_LOOPS / 2;
	}

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        ((GalleryViewPager)container).mCurrentView = ((FileTouchImageView)object).getImageView();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position){
        final FileTouchImageView iv = new FileTouchImageView(mContext);

        position = position % TOTAL_PAGES;

        iv.setUrl(mResources.get(position));
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        if(mScaleType != null)
            iv.setScaleType(mScaleType);
        collection.addView(iv, 0);
        return iv;
    }

    /**
     * Set Scaletype for ImageView
     * @param scaletype
     */
    public void setScaleTypeForImageView(ImageView.ScaleType scaletype) {
        mScaleType = scaletype;
    }

    @Override
    public int getCount() {
        return TOTAL_PAGES * MIN_LOOPS;
    }

};
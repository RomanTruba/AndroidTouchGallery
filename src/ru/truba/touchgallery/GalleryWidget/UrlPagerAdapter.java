package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import ru.truba.touchgallery.TouchView.UrlTouchImageView;

import java.util.List;

/**
 Class wraps URLs to adapter, then it instantiates <b>UrlTouchImageView</b> objects to paging up through them.
 */
public class UrlPagerAdapter extends PagerAdapter {

    private List<String> mResources;
    private Context mContext;
    public UrlPagerAdapter(Context context, List<String> resources){
        this.mResources = resources;
        this.mContext = context;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        ((GalleryViewPager)container).mCurrentView = ((UrlTouchImageView)object).getImageView();
    }

    @Override
    public Object instantiateItem(View collection, int position){
        UrlTouchImageView iv = new UrlTouchImageView(mContext);
        iv.setUrl(mResources.get(position));
        iv.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

        ((ViewPager) collection).addView(iv, 0);
        return iv;
    }

    @Override
    public void destroyItem(View collection, int position, Object view){
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public int getCount(){
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view.equals(object);
    }

    @Override
    public void finishUpdate(View arg0){
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1){
    }

    @Override
    public Parcelable saveState(){
        return null;
    }

    @Override
    public void startUpdate(View arg0){
    }

}
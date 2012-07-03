package ru.truba.touchgallery;

import android.app.Activity;
import android.os.Bundle;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GalleryActivity extends Activity {

    private GalleryViewPager mViewPager;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] urls = {
                "http://cs407831.userapi.com/v407831207/18f6/jBaVZFDhXRA.jpg",
                "http://cs407831.userapi.com/v407831207/18fe/4Tz8av5Hlvo.jpg",
                "http://cs407831.userapi.com/v407831207/1906/oxoP6URjFtA.jpg",
                "http://cs407831.userapi.com/v407831207/190e/2Sz9A774hUc.jpg",
                "http://cs407831.userapi.com/v407831207/1916/Ua52RjnKqjk.jpg",
                "http://cs407831.userapi.com/v407831207/191e/QEQE83Ok0lQ.jpg"
        };
        List<String> items = new ArrayList<String>();
        Collections.addAll(items, urls);

        UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, items);
        mViewPager = new GalleryViewPager(this);
        mViewPager.setAdapter(pagerAdapter);
        setContentView(mViewPager);
    }

}
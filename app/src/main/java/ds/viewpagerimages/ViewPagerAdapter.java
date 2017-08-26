package ds.viewpagerimages;

/**
 * Created by admin
 * Created on 11/16/2016.
 * Modified on 16,November,2016
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Wasim on 11-06-2015.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Integer> mResources;

    public ViewPagerAdapter(Context mContext, ArrayList<Integer> mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Integer gallery=mResources.get(position);
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.gallery_item, container, false);

       final ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);

        Picasso.with(mContext).load(gallery).fit().into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
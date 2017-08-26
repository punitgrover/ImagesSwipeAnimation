package ds.viewpagerimages;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPagerAdapter mAdapter;
    private ViewPager viewPager;
    private int dotsCount;
    private ImageView[] dots;
    private Handler handler;
    private Timer timer;
    private int imagePosition;
    LinearLayout llCircleIndicator;
    ArrayList<Integer> eventDetailPageResponse=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        viewPager=(ViewPager)findViewById(R.id.vw_gallery_pager);
        llCircleIndicator=(LinearLayout) findViewById(R.id.viewPagerCountDots);
        eventDetailPageResponse.add(R.drawable.index);
        eventDetailPageResponse.add(R.drawable.index2);
        eventDetailPageResponse.add(R.drawable.index3);
        eventDetailPageResponse.add(R.drawable.index4);
        eventDetailPageResponse.add(R.drawable.index5);
        if (eventDetailPageResponse!= null) {
            mAdapter = new ViewPagerAdapter(this, eventDetailPageResponse);
            viewPager.setAdapter(mAdapter);
            viewPager.setCurrentItem(0);
            viewPager.setOnPageChangeListener(this);
            setUiPageViewController();
        }
    }
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.drawable__non_selection_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 4);

            llCircleIndicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.drawable_selection_dot));
        setupAnimation();
    }

    private void setupAnimation() {
        final Runnable update = new Runnable() {
            public void run() {
                if (imagePosition == eventDetailPageResponse.size()) {
                    imagePosition = 0;
                }
                viewPager.setCurrentItem(imagePosition++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 2000);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        imagePosition = position;
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.drawable__non_selection_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.drawable_selection_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

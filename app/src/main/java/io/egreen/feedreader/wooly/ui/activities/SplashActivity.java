package io.egreen.feedreader.wooly.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.egreen.feedreader.wooly.R;
import io.egreen.feedreader.wooly.models.SettingsPreferences;
import io.egreen.feedreader.wooly.ui.adapters.SplashPagerAdapter;
import io.egreen.feedreader.wooly.utils.FadePageTransformerUtil;

public class SplashActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.container)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private SplashPagerAdapter mSplashPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        runOnce();

        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSplashPagerAdapter = new SplashPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSplashPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        //set page transformer
        viewPager.setPageTransformer(false, new FadePageTransformerUtil());

        viewPager.addOnPageChangeListener(this);
    }

    private void runOnce() {
        if (!SettingsPreferences.isNewInstall(SplashActivity.this)) {
            runIntent(HomeActivity.class);
            finish();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_arrow_forward_24dp));
//                imgBtnNext.setImageResource(R.drawable.ic_arrow_forward_24dp);
                break;
            case 1:
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_arrow_forward_24dp));
//                imgBtnNext.setImageResource(R.drawable.ic_arrow_forward_24dp);
                break;
            case 2:
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_done_24dp));
//                imgBtnNext.setImageResource(R.drawable.ic_done_24dp);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.fab)
    public void OnNext() {
        if (viewPager.getCurrentItem() == 2) {
            SettingsPreferences.setNewInstall(SplashActivity.this);
            runIntent(HomeActivity.class);
            finish();
        } else if (viewPager.getCurrentItem() < 2) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
    }

    private void runIntent(Class resultActivity) {
        Intent intent = new Intent(SplashActivity.this, resultActivity);
        startActivity(intent);
    }

}

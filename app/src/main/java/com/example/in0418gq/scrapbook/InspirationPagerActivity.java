package com.example.in0418gq.scrapbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by nappy on 10/19/2016.
 */

public class InspirationPagerActivity extends AppCompatActivity {
    private static final String EXTRA_INSPIRATION_ID= "com";

    private ViewPager mViewPager;
    private List<Inspiration> mInspirations;

    public static Intent newIntent(Context packageContext, UUID inspirationId){
        Intent intent = new Intent(packageContext, InspirationPagerActivity.class);
        intent.putExtra(EXTRA_INSPIRATION_ID,inspirationId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration_pager);

        UUID inspirationId = (UUID) getIntent().getSerializableExtra(EXTRA_INSPIRATION_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_inspiration_pager_view_pager);

        mInspirations = InspirationLab.get(this).getInspirations();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Inspiration inspiration = mInspirations.get(position);
                return InspirationFragment.newInstance(inspiration.getId());
            }

            @Override
            public int getCount() {
                return mInspirations.size();
            }
        });
        for (int i = 0;i <mInspirations.size(); i++){
            if (mInspirations.get(i).getId().equals(inspirationId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}

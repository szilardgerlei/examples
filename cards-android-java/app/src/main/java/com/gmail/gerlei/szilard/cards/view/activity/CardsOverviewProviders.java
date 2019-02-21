package com.gmail.gerlei.szilard.cards.view.activity;

import android.graphics.PorterDuff;

import com.gmail.gerlei.szilard.cards.R;
import com.google.android.material.tabs.TabLayout;

import androidx.core.content.ContextCompat;

public class CardsOverviewProviders {

    private static final int TAB_IDX_CARDS = 0;
    private static final int TAB_IDX_TRANS = 1;
    private static final int TAB_IDX_STATE = 2;
    private static final int TAB_IDX_MORE = 3;

    @SuppressWarnings("ConstantConditions")
    void initTabLayout(CardsOverviewActivity activity) {
        TabLayout navTabLayout = activity.findViewById(R.id.navigation_tl);
        navTabLayout.addTab(navTabLayout.newTab().setText(R.string.label_cards).setIcon(R.drawable.nav_cards));
        navTabLayout.addTab(navTabLayout.newTab().setText(R.string.label_trans).setIcon(R.drawable.nav_trans));
        navTabLayout.addTab(navTabLayout.newTab().setText(R.string.label_state).setIcon(R.drawable.nav_state));
        navTabLayout.addTab(navTabLayout.newTab().setText(R.string.label_more).setIcon(R.drawable.nav_more));

        final int colorPrimary = ContextCompat.getColor(activity, R.color.colorPrimary);
        final int colorPrimaryDark = ContextCompat.getColor(activity, R.color.colorPrimaryDark);

        navTabLayout.getTabAt(TAB_IDX_CARDS).getIcon().setColorFilter(colorPrimaryDark, PorterDuff.Mode.SRC_IN);
        navTabLayout.getTabAt(TAB_IDX_TRANS).getIcon().setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);
        navTabLayout.getTabAt(TAB_IDX_STATE).getIcon().setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);
        navTabLayout.getTabAt(TAB_IDX_MORE).getIcon().setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);

        navTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorPrimaryDark, PorterDuff.Mode.SRC_IN);
                if (tab.getPosition() != TAB_IDX_CARDS) {
                    activity.onButtonClick(null);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No need to implement.
            }
        });
    }

}

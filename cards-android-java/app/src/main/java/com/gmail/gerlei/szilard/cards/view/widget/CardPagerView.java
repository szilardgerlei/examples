package com.gmail.gerlei.szilard.cards.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.gerlei.szilard.cards.R;
import com.gmail.gerlei.szilard.cards.model.Card;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CardPagerView extends LinearLayout implements View.OnClickListener {

    public static final int IDX_OF_DEFAULT_IMG = 0;

    private CardPagerViewAdapter adapter;
    private ViewPager viewPager;
    private TextView cardCounterTv;
    private TextView cardNameTv;
    private ImageView unknownCardIv;

    private List<Card> cardList;
    private List<CardImage> cardImages = new ArrayList<>();

    private OnCardChangedListener onCardChangedListener;

    public CardPagerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardPagerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        inflate(getContext(), R.layout.layout_image_pager_view, this);
        cardCounterTv = findViewById(R.id.card_counter_tv);
        cardNameTv = findViewById(R.id.card_name_tv);
        viewPager = findViewById(R.id.viewPager);
        unknownCardIv = findViewById(R.id.unknown_card_iv);
        findViewById(R.id.left_btn).setOnClickListener(this);
        findViewById(R.id.right_btn).setOnClickListener(this);

        cardImages.add(new CardImage(R.drawable.cccard0, getResources().getString(R.string.card_unknown)));
        cardImages.add(new CardImage(R.drawable.cccard1, getResources().getString(R.string.card_premium)));
        cardImages.add(new CardImage(R.drawable.cccard2, getResources().getString(R.string.card_rewards)));
        cardImages.add(new CardImage(R.drawable.cccard3, getResources().getString(R.string.card_journey)));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        cardList = new ArrayList<>();

        adapter = new CardPagerViewAdapter(getContext(), cardImages, cardList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // No need to implement.
            }

            @Override
            public void onPageSelected(int position) {
                selectItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // No need to implement.
            }
        });
    }

    @Override
    public void onClick(View view) {
        int currentItemIdx = viewPager.getCurrentItem();
        if (view.getId() == R.id.left_btn && currentItemIdx > 0) {
            viewPager.setCurrentItem(currentItemIdx - 1);
        } else if (view.getId() == R.id.right_btn && currentItemIdx < cardList.size()) {
            viewPager.setCurrentItem(currentItemIdx + 1);
        }
    }

    private void selectItem(int position) {
        if (onCardChangedListener != null && !cardList.isEmpty()) {
            onCardChangedListener.onCardChanged(cardList.get(position));
            cardNameTv.setText(cardImages.get(cardList.get(position).getCardImage()).getName());
        } else {
            cardNameTv.setText(getResources().getString(R.string.card_unknown));
        }
        String counterDisp = (position + 1) + "/" + cardList.size();
        cardCounterTv.setText(counterDisp);
    }

    public void setCards(List<Card> cards, int startIdx) {
        cardList.clear();
        cardList.addAll(cards);
        unknownCardIv.setVisibility(cardList.isEmpty() ? VISIBLE : INVISIBLE);
        adapter.notifyDataSetChanged();
        if (!(startIdx >= 0 && startIdx < cardList.size())) {
            startIdx = IDX_OF_DEFAULT_IMG;
        }
        viewPager.setCurrentItem(startIdx);
        selectItem(startIdx);
    }

    public int getCurrentItem() {
        return viewPager.getCurrentItem();
    }

    public void setOnCardChangedListener(OnCardChangedListener onCardChangedListener) {
        this.onCardChangedListener = onCardChangedListener;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public interface OnCardChangedListener {
        void onCardChanged(Card actualItem);
    }

}

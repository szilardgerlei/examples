package com.gmail.gerlei.szilard.cards.view.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gmail.gerlei.szilard.cards.R;
import com.gmail.gerlei.szilard.cards.model.Card;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

class CardPagerViewAdapter extends PagerAdapter {

    private Context context;
    private List<CardImage> cardImages;
    private List<Card> cards;

    CardPagerViewAdapter(Context context, List<CardImage> cardImages, List<Card> cardList) {
        this.context = context;
        this.cardImages = cardImages;
        cards = cardList;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    @NonNull
    @SuppressWarnings("ConstantConditions")
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageDisp;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View viewLayout = inflater.inflate(R.layout.image_pager_item, container, false);
        imageDisp = viewLayout.findViewById(R.id.imgDisplay);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Card c = cards.get(position);

        if (c.getCardImage() >= 1 && c.getCardImage() <= 3) {
            imageDisp.setImageResource(cardImages.get(c.getCardImage()).getResId());
        } else {
            imageDisp.setBackgroundResource(cardImages.get(CardPagerView.IDX_OF_DEFAULT_IMG).getResId());
        }

        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

}

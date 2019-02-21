package com.gmail.gerlei.szilard.cards.view.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.gerlei.szilard.cards.R;
import com.gmail.gerlei.szilard.cards.model.Card;
import com.gmail.gerlei.szilard.cards.utils.DateUtil;
import com.gmail.gerlei.szilard.cards.utils.DecimalUtil;
import com.gmail.gerlei.szilard.cards.view.widget.CardPagerView;
import com.gmail.gerlei.szilard.cards.view.widget.ChartView;
import com.gmail.gerlei.szilard.cards.viewmodel.CardsOverviewViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import static com.gmail.gerlei.szilard.cards.view.widget.CardPagerView.IDX_OF_DEFAULT_IMG;

public class CardsOverviewActivity extends AppCompatActivity implements CardPagerView.OnCardChangedListener {

    private static final String KEY_CARDS = "cards_key";
    private static final String KEY_CURRENT_CARD_ITEM = "curr_item_key";

    private TextView currentBalanceTv;
    private TextView minPaymentTv;
    private TextView dueDateTv;
    private ChartView chartView;
    private CardPagerView cardPagerView;

    private CardsOverviewViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cards_overview);

        chartView = findViewById(R.id.chart_view);
        currentBalanceTv = findViewById(R.id.current_balance_amount_tv);
        minPaymentTv = findViewById(R.id.min_payment_amount_tv);
        dueDateTv = findViewById(R.id.due_date_tv);
        cardPagerView = findViewById(R.id.view_image_pager);
        cardPagerView.setOnCardChangedListener(this);

        new CardsOverviewProviders().initTabLayout(this);
        viewModel = ViewModelProviders.of(this).get(CardsOverviewViewModel.class);
        if (savedInstanceState != null) {
            List<Card> cards = savedInstanceState.getParcelableArrayList(KEY_CARDS);
            int currentItem = savedInstanceState.getInt(KEY_CURRENT_CARD_ITEM);
            cardPagerView.setCards(cards, currentItem);

        }

        observe();
    }

    private void observe() {
        viewModel.getCardListLiveData().observe(this, cards -> {
            if (cards != null) {
                cardPagerView.setCards(cards, IDX_OF_DEFAULT_IMG);
            } else {
                Toast.makeText(CardsOverviewActivity.this, getString(R.string.error_during_downloading_cards), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_CARDS, new ArrayList<Parcelable>(cardPagerView.getCardList()));
        outState.putInt(KEY_CURRENT_CARD_ITEM, cardPagerView.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCardChanged(Card actualItem) {
        currentBalanceTv.setText(DecimalUtil.formatByDefaultDecimalFormat(actualItem.getCurrentBalance()));
        minPaymentTv.setText(DecimalUtil.formatByDefaultDecimalFormat(actualItem.getMinPayment()));
        dueDateTv.setText(DateUtil.format(actualItem.getDueDate()));
        chartView.setBalances(actualItem.getCurrentBalance(), actualItem.getAvailableBalance());
    }

    public void onButtonClick(View v) {
        Toast.makeText(this, R.string.warn_feature_not_supported, Toast.LENGTH_SHORT).show();
    }

}

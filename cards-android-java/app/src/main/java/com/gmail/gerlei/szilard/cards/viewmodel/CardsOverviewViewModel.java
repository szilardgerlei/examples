package com.gmail.gerlei.szilard.cards.viewmodel;

import android.app.Application;

import com.gmail.gerlei.szilard.cards.model.Card;
import com.gmail.gerlei.szilard.cards.networking.CardRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CardsOverviewViewModel extends AndroidViewModel {

    private final LiveData<List<Card>> cardListLiveData;

    public CardsOverviewViewModel(@NonNull Application application) {
        super(application);
        CardRepository cardRepository = new CardRepository();

        cardListLiveData = cardRepository.getCards();
    }

    public LiveData<List<Card>> getCardListLiveData() {
        return cardListLiveData;
    }

}

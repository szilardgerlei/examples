package com.gmail.gerlei.szilard.cards.networking;

import com.gmail.gerlei.szilard.cards.model.Card;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardRepository {

    public LiveData<List<Card>> getCards() {
        final MutableLiveData<List<Card>> liveData = new MutableLiveData<>();

        RestFactory.cardService().getCards().enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(@NonNull Call<List<Card>> call, @NonNull Response<List<Card>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Card>> call, @NonNull Throwable t) {
                liveData.setValue(null);
            }
        });

        return liveData;
    }

}

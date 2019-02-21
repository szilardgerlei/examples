package com.gmail.gerlei.szilard.cards.networking;

import com.gmail.gerlei.szilard.cards.model.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CardService {

    @GET("card")
    Call<List<Card>> getCards();

}

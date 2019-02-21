package com.gmail.gerlei.szilard.cards.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final class RestFactory {

    private static <T> T builder(Class<T> endpoint) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new MultipleDateFormatJsonDeserializer())
                .create();

        return new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(endpoint);
    }

    static CardService cardService() {
        return builder(CardService.class);
    }

}

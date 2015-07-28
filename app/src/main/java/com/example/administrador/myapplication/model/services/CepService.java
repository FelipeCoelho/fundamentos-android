package com.example.administrador.myapplication.model.services;

import android.location.Address;

import com.example.administrador.myapplication.model.entities.ClientAddres;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class CepService {

    private final static String URL = "http://correiosapi.apphb.com/cep/";

    private CepService() {
        super();
    }

    public static ClientAddres getAddressBy(String cep) {
        ClientAddres address = null;
        try {
            URL url = new URL(URL + cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Error code:" + responseCode);
            }

            InputStream inputStream = conn.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(inputStream, ClientAddres.class);

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }


}

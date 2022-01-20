package com.danilorocha.pdm2_atv3_aula_10_08_21;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("all")
public class Conexao {

    public static final InputStream obterRespostaHTTP(String endereco) {
        try {
            URL url = new URL(endereco);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(
                    httpURLConnection.getInputStream()
            );
            return inputStream;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

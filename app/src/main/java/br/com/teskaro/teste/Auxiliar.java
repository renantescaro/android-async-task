package br.com.teskaro.teste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Auxiliar {
    public Bitmap baixarImagem(String link) throws IOException {
        Log.i("Auxiliar", "entrou no baixarImagem()");

        Bitmap imagem = null;

        URL url = new URL(link);
        InputStream inputStream = url.openStream();
        imagem = BitmapFactory.decodeStream(inputStream);
        inputStream.close();

        Log.i("Auxiliar", "saindo do baixarImagem()");
        return imagem;
    }
}

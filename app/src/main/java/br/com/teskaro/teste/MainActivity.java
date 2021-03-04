package br.com.teskaro.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText edtLink;
    private Button btnBaixarImagem;
    private ImageView imvImagem;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "entrou no onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLink         = findViewById(R.id.edtLink);
        btnBaixarImagem = findViewById(R.id.btnBaixarImagem);
        imvImagem       = findViewById(R.id.imvImagem);

        btnBaixarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "entrou no onClick()");

                String link = edtLink.getText().toString();

                // executa tarefa assincrona
                BaixarImagemAsyncTask baixarImagemAsyncTask = new BaixarImagemAsyncTask();
                baixarImagemAsyncTask.execute(link);

                Log.i("MainActivity", "saindo do onClick()");
            }
        });
    }


    public class BaixarImagemAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            Log.i("BaixarImagemAsyncTask", "entrando no onPreExecute()");

            // janela de loading
            progressDialog = ProgressDialog.show(MainActivity.this,
                    getResources().getText(R.string.prd_loading_titulo),
                    getResources().getText(R.string.prd_loading_mensagem));

            Log.i("BaixarImagemAsyncTask", "saindo do onPreExecute()");
        }

        @Override
        protected Bitmap doInBackground(String... link) {
            Log.i("BaixarImagemAsyncTask", "entrando no doInBackground()");

            Bitmap bitmap = null;
            Auxiliar auxiliar = new Auxiliar();
            try {
                bitmap = auxiliar.baixarImagem(link[0]);
            }catch (Exception exception){
                exception.printStackTrace();
            }

            Log.i("BaixarImagemAsyncTask", "saindo do doInBackground()");
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.i("BaixarImagemAsyncTask", "entrando no onPostExecute()");

            if(bitmap != null){
                imvImagem.setImageBitmap(bitmap);
            }
            progressDialog.dismiss();

            Log.i("BaixarImagemAsyncTask", "saindo do onPostExecute()");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i("BaixarImagemAsyncTask", "onProgressUpdate no onPostExecute()");

            super.onProgressUpdate(values);

            Log.i("BaixarImagemAsyncTask", "onProgressUpdate do onPostExecute()");
        }
    }
}
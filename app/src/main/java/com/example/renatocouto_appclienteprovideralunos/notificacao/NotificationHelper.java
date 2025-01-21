package com.example.renatocouto_appclienteprovideralunos.notificacao;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.renatocouto_appclienteprovideralunos.R;

public class NotificationHelper {
    private static final String CANAL_ID = "2";
    private static final int NOTIFICACAO_ID = 1;
    private final Context context;
    private final NotificationManagerCompat notificationManagerCompat;

    public NotificationHelper(Context context) {
        this.context = context;
        this.notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    private void criarCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nome = "canal1";
            String descricao = "descrição do canal 1";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CANAL_ID, nome, importancia);

            canal.setDescription(descricao);
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(canal);
        }//if
    }//criarCanal

    @SuppressLint("MissingPermission")
    public void gerarNotificacao(String mensagem) {
        criarCanal();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CANAL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("APP Cliente")
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManagerCompat.notify(NOTIFICACAO_ID, builder.build());
    }
}

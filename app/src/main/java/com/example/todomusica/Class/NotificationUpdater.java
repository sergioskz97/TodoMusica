package com.example.todomusica.Class;

import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.todomusica.R;

public class NotificationUpdater extends Worker {

    public NotificationUpdater(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        String artist = "artist";

        NotificationCompat.Builder mbuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.login_logo)
                .setContentTitle("Nuevas noticias disponibles")
                .setContentText("Nuevas noticias sobre" + artist);

        NotificationManager notificationManager = (NotificationManager) getSystemService();

        notificationManager.notify(0, mbuilder.build());

        return Result.success();
    }

    private Object getSystemService() {
        Object object = null;

        return object;
    }
}

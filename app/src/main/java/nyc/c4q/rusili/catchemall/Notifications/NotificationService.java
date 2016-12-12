package nyc.c4q.rusili.catchemall.Notifications;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends IntentService {

    private static final String SERVICE_NAME = "notification service";

    public NotificationService() {
        super(SERVICE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int NOTIFICATION_ID = 555;

        NotificationCompat.Builder noteBuilder = new NotificationCompat.Builder(this)
            //.setSmallIcon(R.drawable.)
            .setContentTitle("Yo")
            .setContentText("Wussup");

        NotificationManager noteMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        noteMan.notify(NOTIFICATION_ID,noteBuilder.build());
    }
}

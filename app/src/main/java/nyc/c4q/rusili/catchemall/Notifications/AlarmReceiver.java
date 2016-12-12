package nyc.c4q.rusili.catchemall.Notifications;

import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{
    public static final int REQUEST_CODE = 123;
    public static final String ACTION = "nyc.c4q.rusili.backgroundnotifications.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, NotificationService.class);
        context.startService(i);
    }
}

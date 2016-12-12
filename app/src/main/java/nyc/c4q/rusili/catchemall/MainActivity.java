package nyc.c4q.rusili.catchemall;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import nyc.c4q.rusili.catchemall.Notifications.NotificationService;
import nyc.c4q.rusili.catchemall.RecyclerView.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

   private RecyclerView rv;
   private RecyclerViewAdapter pokeAdapter = new RecyclerViewAdapter();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      rv = (RecyclerView) findViewById(R.id.id_recyclerview);
      rv.setLayoutManager(new GridLayoutManager(this, 3));
      rv.setAdapter(pokeAdapter);
      
      //TestService();
      BasicNotificationIntent();
   }

   public void BasicNotificationIntent(){
      int NOTIFICATION_ID = 555;

      int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
      int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
      PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, new Intent(this, SecondActivity.class), flags);

      Notification note = new NotificationCompat.Builder(this)
         .setSmallIcon(R.drawable.icon_circle)
         .setContentTitle("Yo")
         .setContentText("Wussup")
         .setContentIntent(pendingIntent)
         .setAutoCancel(true) // Hides the notification after its been selected
         .build();

      NotificationManager noteMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

      noteMan.notify(NOTIFICATION_ID, note);
   }

   public void TestService(){
      Intent i = new Intent(this, NotificationService.class);
      startService(i);
   }
}

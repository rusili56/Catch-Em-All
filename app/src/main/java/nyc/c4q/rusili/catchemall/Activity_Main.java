package nyc.c4q.rusili.catchemall;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.rusili.catchemall.Database.PokeDatabase;
import nyc.c4q.rusili.catchemall.Database.PokeModel;
import nyc.c4q.rusili.catchemall.Notifications.MyAlarmReceiver;
import nyc.c4q.rusili.catchemall.Notifications.MyNotificationService;
import nyc.c4q.rusili.catchemall.RecyclerView.PokeAdapter;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class Activity_Main extends AppCompatActivity {

    private TextView tv_numCaught;
    private RecyclerView rv;
    private PokeAdapter pokeAdapter;
    private PokeFragment pF = new PokeFragment();
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_numCaught = (TextView) findViewById(R.id.main_numCaught);

        PokeDatabase dbHelper = PokeDatabase.getInstance(this);
        db = dbHelper.getWritableDatabase();

        pokeAdapter = new PokeAdapter(selectAllPokemon());
        rv = (RecyclerView) findViewById(R.id.id_recyclerview);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(pokeAdapter);
        tv_numCaught.setText("" + selectAllPokemon().size());

        BasicNotificationIntent();
    }

    public void ScheduleAlarm(){
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis();

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
    }

    public void BasicNotificationIntent() {
        int NOTIFICATION_ID = 555;
        Random rand = new Random();
        int id = rand.nextInt(149) + 1;

        Intent intent = new Intent(this, Activity_Catch.class);
        intent.putExtra("id", id);

        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        Notification note = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_circle)
                .setContentTitle("Catch Em All")
                .setContentText("A wild Pokemon appears!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();

        NotificationManager noteMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        noteMan.notify(NOTIFICATION_ID, note);
    }


    public void TestService() {
        Intent i = new Intent(this, MyNotificationService.class);
        startService(i);
    }

    public Activity getActivity() {
        return this;
    }

    private List<PokeModel> selectAllPokemon() {
        List<PokeModel> pokemon = new ArrayList<>();

        try {
            // Iterate PokeModels
            QueryResultIterable<PokeModel> itr = cupboard().withDatabase(db).query(PokeModel.class).query();
            for (PokeModel pokeModel : itr) {
                pokemon.add(pokeModel);
            }
            itr.close();
        } catch (Exception e) {
            Log.e("selectAllPokeModels: ", String.valueOf(e));
        }
        Log.d("Pokemon in Database: ", String.valueOf(pokemon.size()));
        return pokemon;
    }

    public static SQLiteDatabase giveDatabase(){
        return db;
    }
}

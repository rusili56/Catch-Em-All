package nyc.c4q.rusili.catchemall;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.rusili.catchemall.Database.PokeDatabase;
import nyc.c4q.rusili.catchemall.Database.PokeModel;
import nyc.c4q.rusili.catchemall.Network.ApiModel;
import nyc.c4q.rusili.catchemall.Network.PokeRetrofit;
import nyc.c4q.rusili.catchemall.RecyclerView.PokeAdapter;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class Activity_Catch extends AppCompatActivity {
    private static TextView tv_pokemon_name;
    private static ImageView iv_pokemon_front;
    private static Context ctx;
    private PokeAdapter adapter;
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        Log.d("id", String.valueOf(id));
        PokeDatabase dbHelper = PokeDatabase.getInstance(this);
        db = dbHelper.getWritableDatabase();

        tv_pokemon_name = (TextView) findViewById(R.id.catch_tv_name);
        iv_pokemon_front = (ImageView) findViewById(R.id.catch_iv_image_front);

        PokeRetrofit pokeRetro = new PokeRetrofit();
        final ApiModel[] apiModel = pokeRetro.call(String.valueOf(id));
        ctx = this.getBaseContext();
        
    }

    private void addPokemon(PokeModel pmInput) {
        cupboard().withDatabase(db).put(pmInput);
    }

    private void refreshPokeList() {
        adapter.setData(selectAllPokemon());
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
            Log.e("selectAllPokemon: ", String.valueOf(e));
        }
        return pokemon;
    }

    public static void addtoDatabase(ApiModel apiModel) {
        long id = apiModel.getId();
        String givenName = apiModel.getName();
        String nickName = "a";
        String type = apiModel.getTypes().get(0).getType().getName();
        String image_URL1 = apiModel.getSprites().getFront_default();
        String image_URL2 = apiModel.getSprites().getBack_default();
        Long timeCaught = Calendar.getInstance().getTimeInMillis();

        System.out.println(id + givenName + type + image_URL1 + image_URL2);
        displayInfo(givenName, image_URL1);

        long id3 = cupboard().withDatabase(db).put(new PokeModel(id, givenName, nickName, type, image_URL1, image_URL2, timeCaught));
    }

    public static void displayInfo(String givenName, String image_URL1) {
        tv_pokemon_name.setText("You caught a: " + givenName);
        Picasso.with(ctx).load(image_URL1).resize(500, 500).centerCrop().into(iv_pokemon_front);
    }

    public void Exit(View view) {
        finish();
    }

    public void toApp(View view) {
        Intent toMain = new Intent(this, Activity_Main.class);
        startActivity(toMain);
    }
}

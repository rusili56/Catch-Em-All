package nyc.c4q.rusili.catchemall.RecyclerView;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.rusili.catchemall.Database.PokeModel;

public class PokeAdapter extends RecyclerView.Adapter {

    private List<PokeModel> all150 = new ArrayList<>();
    private static Activity act;

    public PokeAdapter(List<PokeModel> pokeList) {
        this.all150 = pokeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PokeViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PokeViewHolder pokeHolder = (PokeViewHolder) holder;
        pokeHolder.onBind(all150.get(position));
    }

    public void getActivity(Activity aInput) {
        this.act = aInput;
    }

    public static Activity giveActivity() {
        return act;
    }

    @Override
    public int getItemCount() {
        return all150.size();
    }

    public void setData(List<PokeModel> pokeModels) {

    }
}

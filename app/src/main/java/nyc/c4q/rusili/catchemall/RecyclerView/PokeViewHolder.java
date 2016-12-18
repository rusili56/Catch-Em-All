package nyc.c4q.rusili.catchemall.RecyclerView;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.rusili.catchemall.Database.PokeModel;
import nyc.c4q.rusili.catchemall.PokeFragment;
import nyc.c4q.rusili.catchemall.R;

public class PokeViewHolder extends RecyclerView.ViewHolder {
    private Activity act;
    private View view;
    private boolean isOpen = false;
    private PokeFragment pokeFrag = new PokeFragment();

    public PokeViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        act = PokeAdapter.giveActivity();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    act.getFragmentManager().beginTransaction().remove(pokeFrag).commit();
                    isOpen = false;
                } else {
                    act.getFragmentManager().beginTransaction().replace(R.id.id_fraglayout, pokeFrag).commit();
                    isOpen = true;
                }
            }
        });
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.viewholder_pokemon, parent, false);
        return v;
    }

    public void onBind(PokeModel pokeModel) {

    }

    public void getActivity(Activity aInput) {
        this.act = aInput;
    }
}

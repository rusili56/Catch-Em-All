package nyc.c4q.rusili.catchemall.RecyclerView;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.rusili.catchemall.Database.PokeModel;
import nyc.c4q.rusili.catchemall.PokeFragment;
import nyc.c4q.rusili.catchemall.R;

public class PokeViewHolder extends RecyclerView.ViewHolder {
    private Activity act;
    private View view;
    private boolean isOpen = false;
    private TextView tv_name;
    private TextView tv_id;
    private ImageView iv_front;
    private PokeFragment pokeFrag = new PokeFragment();

    public PokeViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        act = PokeAdapter.giveActivity();
        tv_name = (TextView) view.findViewById(R.id.main_tv_Name);
        tv_id = (TextView) view.findViewById(R.id.main_tv_number);
        iv_front = (ImageView) view.findViewById(R.id.main_iv_front);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    act.getFragmentManager().beginTransaction().remove(pokeFrag).commit();
                    isOpen = false;
                } else {
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
        tv_id.setText("" + pokeModel.get_id());
        tv_name.setText("" + pokeModel.getGivenName());
        Picasso.with(view.getContext()).load(pokeModel.getImage_url1()).resize(400, 400).centerCrop().into(iv_front);
    }

    public void getActivity(Activity aInput) {
        this.act = aInput;
    }
}

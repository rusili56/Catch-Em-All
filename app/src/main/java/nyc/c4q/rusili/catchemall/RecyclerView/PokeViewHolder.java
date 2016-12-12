package nyc.c4q.rusili.catchemall.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.rusili.catchemall.R;

public class PokeViewHolder extends RecyclerView.ViewHolder {
   private View view;

   public PokeViewHolder(ViewGroup parent) {
      super(inflateView(parent));
      view = itemView;
   }

   private static View inflateView(ViewGroup parent) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View v = inflater.inflate(R.layout.viewholder_pokemon, parent, false);
      return v;
   }
}

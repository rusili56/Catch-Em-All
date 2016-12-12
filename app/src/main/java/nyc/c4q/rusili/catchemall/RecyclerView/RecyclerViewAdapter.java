package nyc.c4q.rusili.catchemall.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import nyc.c4q.rusili.catchemall.Network.Pokemon;

public class RecyclerViewAdapter extends RecyclerView.Adapter{

   private Pokemon[] all150;

   public RecyclerViewAdapter(){
   }

   @Override
   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     return new PokeViewHolder(parent);
   }

   @Override
   public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

   }

   public void getList(Pokemon[] pInput){
      this.all150 = pInput;
   }

   @Override
   public int getItemCount() {
      return 15;
   }
}

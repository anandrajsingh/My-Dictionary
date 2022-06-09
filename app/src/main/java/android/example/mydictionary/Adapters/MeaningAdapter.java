package android.example.mydictionary.Adapters;

import android.content.Context;
import android.example.mydictionary.Models.Meanings;
import android.example.mydictionary.R;
import android.example.mydictionary.ViewHolders.MeaningViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningViewHolder> {

    private Context context;
    private List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        holder.partsOfSpeech.setText("Parts of Speech" + meaningsList.get(position).getPartOfSpeech());
        holder.recyclerViewDefinition.setHasFixedSize(true);
        holder.recyclerViewDefinition.setLayoutManager(new GridLayoutManager(context,1));

        DefinitionAdapter definitionAdapter = new DefinitionAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recyclerViewDefinition.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}

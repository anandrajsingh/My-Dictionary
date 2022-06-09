package android.example.mydictionary.ViewHolders;

import android.example.mydictionary.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MeaningViewHolder extends RecyclerView.ViewHolder {

    public TextView partsOfSpeech;
    public RecyclerView recyclerViewDefinition;

    public MeaningViewHolder(@NonNull View itemView) {
        super(itemView);

        partsOfSpeech = itemView.findViewById(R.id.textView_partsOfSpeech);
        recyclerViewDefinition = itemView.findViewById(R.id.recycler_definitions);

    }
}

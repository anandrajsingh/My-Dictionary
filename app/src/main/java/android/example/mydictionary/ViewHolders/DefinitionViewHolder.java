package android.example.mydictionary.ViewHolders;

import android.example.mydictionary.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewDefinition, textViewExample, textViewSynonyms, textViewAntonyms;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewDefinition = itemView.findViewById(R.id.textView_definitions);
        textViewExample = itemView.findViewById(R.id.textView_example);
        textViewSynonyms = itemView.findViewById(R.id.textView_synonyms);
        textViewAntonyms = itemView.findViewById(R.id.textView_antonyms);
    }
}

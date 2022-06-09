package android.example.mydictionary.ViewHolders;

import android.example.mydictionary.R;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewPhonetic;
    public ImageButton imageButtonAudio;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewPhonetic = itemView.findViewById(R.id.textView_phonetic);
        imageButtonAudio = itemView.findViewById(R.id.imageButton_audio);
    }
}

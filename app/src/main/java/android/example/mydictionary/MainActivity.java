package android.example.mydictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.example.mydictionary.Adapters.MeaningAdapter;
import android.example.mydictionary.Adapters.PhoneticsAdapter;
import android.example.mydictionary.Models.APIResponse;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView textViewWord;
    RecyclerView recyclerViewPhonetics, recyclerViewMeanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;
    Toolbar toolbar;
    private PackageInfo packageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
        textViewWord = findViewById(R.id.textView_word);
        recyclerViewPhonetics = findViewById(R.id.recycler_phonetics);
        recyclerViewMeanings = findViewById(R.id.recycler_meanings);
        toolbar = findViewById(R.id.toolbar);
        progressDialog = new ProgressDialog(this);

        try {
            packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            setupVersionInfo();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        progressDialog.setTitle("Loading... ");
        progressDialog.show();
        Request request = new Request(MainActivity.this);
        request.getWordMeaning(listener, "Hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                progressDialog.setTitle("Fetching response for "+ s);
                progressDialog.show();
                Request request = new Request(MainActivity.this);
                request.getWordMeaning(listener, s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse == null){
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        textViewWord.setText("Word: " + apiResponse.getWord());
        recyclerViewPhonetics.setHasFixedSize(true);
        recyclerViewPhonetics.setLayoutManager(new GridLayoutManager(this,1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        recyclerViewPhonetics.setAdapter(phoneticsAdapter);

        recyclerViewMeanings.setHasFixedSize(true);
        recyclerViewMeanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        recyclerViewMeanings.setAdapter(meaningAdapter);
    }

    private void setupVersionInfo() {

        if (packageInfo != null) {
            String vinfo = String.format("V: %s", packageInfo.versionName);
            toolbar.setSubtitle(vinfo);
        }

    }
}
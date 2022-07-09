package com.example.quranv01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomSheetDialog.Mlistener {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<Page> pages;
    ArrayList<Aya> ayas;
    ArrayList<String> surahsArrayList;
    int pageNumber,oldpage,i;
    LinearLayout title;
    TextView textView;
    JSONObject mainNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        pages = new ArrayList<>();
        ayas = new ArrayList<>();
        textView = findViewById(R.id.surahName);


        getSurahs();
        onItemClicked(0);

        title = findViewById(R.id.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(surahsArrayList);
                bottomSheetDialog.show(getSupportFragmentManager(),"list");
            }
        });

    }

    private String loadJSONFromAsset() {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = getAssets().open("quran-uthmani.json");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            Log.d("X","Response Ready:"+stringBuilder.toString());

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onItemClicked(int x) {

        try {
            // Main Node
            JSONObject mainNode = new JSONObject(loadJSONFromAsset());
            JSONObject data = mainNode.getJSONObject("data");
            JSONArray surahs = data.getJSONArray("surahs");
            JSONObject Surah = surahs.getJSONObject(x);
            JSONArray ayahs = Surah.getJSONArray("ayahs");

            textView.setText(Surah.getString("name"));

            pageNumber = ayahs.getJSONObject(0).getInt("page");
            for (i=0;i<ayahs.length();i++) {

                if (pageNumber != ayahs.getJSONObject(i).getInt("page")) {

                    pages.add(new Page((ArrayList<Aya>) ayas.clone(), pageNumber));
                    pageNumber = ayahs.getJSONObject(i).getInt("page");

                    ayas.clear();
                }
                ayas.add(new Aya(ayahs.getJSONObject(i).getString("text"), i, pageNumber));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pages.add(new Page((ArrayList<Aya>) ayas.clone(), pageNumber));
        adapter = new PageAdapter(this,(ArrayList<Page>) pages.clone());
        recyclerView.setAdapter(adapter);
        ayas.clear();
        pages.clear();



    }

    public void getSurahs(){
        try {
            mainNode = null;
            surahsArrayList = new ArrayList<String>();
            mainNode = new JSONObject(loadJSONFromAsset());
            JSONObject data = mainNode.getJSONObject("data");
            JSONArray surahs = data.getJSONArray("surahs");
            for (i = 0; i < surahs.length(); i++)
                surahsArrayList.add(surahs.getJSONObject(i).getString("name"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
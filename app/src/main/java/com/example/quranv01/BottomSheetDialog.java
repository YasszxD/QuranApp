package com.example.quranv01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BottomSheetDialog extends BottomSheetDialogFragment  {
    ArrayList<String> surahsArrayList;
    public BottomSheetDialog(ArrayList<String> surahsArrayList) {
        this.surahsArrayList = surahsArrayList;
    }

    Mlistener mlistener;

    public interface Mlistener{
        void onItemClicked(int i);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.bottom_sheet_layout,container,false);
        ListView listView = v.findViewById(R.id.listView);







        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mlistener.onItemClicked(i);
                dismiss();
            }
        });


        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,surahsArrayList));
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mlistener = (Mlistener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must be implemented");
        }

    }





}



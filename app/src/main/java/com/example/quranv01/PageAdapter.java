package com.example.quranv01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {
    String Pagetext;
    int i=0 , j=0;


    private ArrayList<Page> pages;
    public PageAdapter(Context C, ArrayList<Page> list){
        pages=list;
        //activity = (ItemClicked) C;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{ // 3melna View holder mte3na

        TextView page;
        TextView pageNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            page = itemView.findViewById(R.id.page);
            pageNumber = itemView.findViewById(R.id.pageNumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //activity.onItemClicked(people.indexOf(itemView.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pagetext = "";
        int start;
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
            for (j = 0 ; j<pages.get(position).ayas.size();j++) {
                spannableString.append(pages.get(position).ayas.get(j).text );
                start = spannableString.length();
                spannableString.append("  "+ (pages.get(position).ayas.get(j).ayaNumber + 1) +"  ");
                spannableString.setSpan(new BackgroundColorSpan(R.color.black), start+1, spannableString.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                // Pagetext += pages.get(position).ayas.get(j).text + " " + (pages.get(position).ayas.get(j).ayaNumber + 1) + " ";
            }
        holder.page.setText(spannableString);
        holder.pageNumber.setText(Integer.toString(pages.get(position).getPageNumber()));
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }
}

package edu.huflit.truyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.huflit.truyentranh.MainActivity;
import edu.huflit.truyentranh.MainChapter;
import edu.huflit.truyentranh.R;
import edu.huflit.truyentranh.model.Chapter;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Chapter> mChapters;
    private IActionRecycler iActionRecycler;

    public ChapterAdapter(Context mContext, IActionRecycler iActionRecycler) {
        this.mContext = mContext;
        this.iActionRecycler = iActionRecycler;
    }

    public void setData(ArrayList<Chapter> mChapters) {
        this.mChapters = mChapters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View chapterView = inflater.inflate(R.layout.item_chapter_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(chapterView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chapter = mChapters.get(position);
        holder.txtName.setText(chapter.getNameChapter());
        holder.setOnClick(chapter);
    }

    @Override
    public int getItemCount() {
        return mChapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageButton btnEdit, btnDelte;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameChapter);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelte = itemView.findViewById(R.id.btnDelte);

            if(MainActivity.idquyen != 2) {
                btnEdit.setVisibility(View.INVISIBLE);
                btnDelte.setVisibility(View.INVISIBLE);
            }
        }

        public void setOnClick(Chapter chapter) {
            itemView.setOnClickListener(view ->{
                iActionRecycler.sendActivity(chapter.getUrl());
            });

            btnEdit.setOnClickListener(view -> {
                iActionRecycler.editChapter(chapter);
            });

            btnDelte.setOnClickListener(view -> {
                iActionRecycler.deleteChapter(chapter.getId()); //click
            });
        }
    }
}

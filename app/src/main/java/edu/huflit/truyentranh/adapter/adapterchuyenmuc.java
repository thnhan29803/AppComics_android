package edu.huflit.truyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.huflit.truyentranh.R;
import edu.huflit.truyentranh.model.chuyenmuc;

public class adapterchuyenmuc extends BaseAdapter {

    private Context context;
    private int layout;
    private List<chuyenmuc> chuyenmucList;

    public adapterchuyenmuc(Context context, int layout, List<chuyenmuc> chuyenmucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucList = chuyenmucList;
    }

    @Override
    public int getCount() {
        return chuyenmucList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layout, null);

        ImageView img = (ImageView) view.findViewById(R.id.imgchuyenmuc);

        TextView txt = (TextView) view.findViewById(R.id.textviewTenchuyenmuc);

        chuyenmuc cm = chuyenmucList.get(i);

        txt.setText(cm.getTenchuyenmuc());

        //Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);

        img.setImageResource(cm.getHinhanhchuyenmuc());

        return view;
    }
}

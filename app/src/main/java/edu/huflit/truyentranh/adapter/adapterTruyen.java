package edu.huflit.truyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.huflit.truyentranh.R;
import edu.huflit.truyentranh.model.Truyen;

public class adapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int i) {
        return listTruyen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    //Filter
    public void fiterList(ArrayList<Truyen> filteredList) {
        listTruyen = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView txtTenTruyen;
        ImageView imgTruyen;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen, null);

        viewHolder.txtTenTruyen = convertView.findViewById(R.id.textviewTenTruyenNew);
        viewHolder.imgTruyen = convertView.findViewById(R.id.imgNewTruyen);
        convertView.setTag(viewHolder);

        Truyen truyen = (Truyen) getItem(i);
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHolder.imgTruyen);
        notifyDataSetChanged();


        return convertView;
    }


}

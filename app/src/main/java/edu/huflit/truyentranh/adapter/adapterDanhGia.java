package edu.huflit.truyentranh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.huflit.truyentranh.R;
import edu.huflit.truyentranh.model.DanhGia;

public class adapterDanhGia extends BaseAdapter {

    private Context contex;
    private  ArrayList<DanhGia> listDG;
    public adapterDanhGia(Context context, ArrayList<DanhGia> listdg) {
        this.contex = context;
        this.listDG = listdg;

    }


    public int getCount() {
        return listDG.size();
    }


    public Object getItem(int position) {
        return listDG.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) contex.getSystemService(contex.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_danhgia,null);



        DanhGia danhGia = listDG.get(position);


        TextView txtnoidung =   convertView.findViewById(R.id.txt_noidung);
        TextView txt_tentk = convertView.findViewById(R.id.txttentk);
        TextView txt_tentruyen = convertView.findViewById(R.id.txt_tentruyen);


        txtnoidung.setText(danhGia.getNoiDungDanhGia());
        txt_tentk.setText(danhGia.getTenTK());
        txt_tentruyen.setText("Truyện: "+danhGia.getTenTruyen());

        return convertView;
    }
}

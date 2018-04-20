package com.example.amanda.personaltrainerbusiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptor extends ArrayAdapter<Database> implements View.OnClickListener{

    private ArrayList<Database> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView KEY_ID_COLUMN;
        TextView ADDRESS_COLUMN;
        TextView PHONE_COLUMN;
        TextView EMAIL_COLUMN;
        ImageView PICTURE;
    }

    public Adaptor(ArrayList<Database> data, Context context) {
        super(context, R.layout.activity_customer_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Database dataBase=(Database)object;

     //   switch (v.getId())
       // {
         //   case R.id.gridL:
           //     Snackbar.make(v, "Release date " +dataBase.getFeature(), Snackbar.LENGTH_LONG)
             //           .setAction("No action", null).show();
            //    break;
        //}
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Database dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_customer_list, parent, false);
            viewHolder.KEY_ID_COLUMN = (TextView) convertView.findViewById(R.id.name);
            viewHolder.ADDRESS_COLUMN = (TextView) convertView.findViewById(R.id.address);
            viewHolder.PHONE_COLUMN = (TextView) convertView.findViewById(R.id.phone);
            viewHolder.EMAIL_COLUMN = (TextView) convertView.findViewById(R.id.email);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

      //  Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
       // result.startAnimation(animation);
       // lastPosition = position;

        viewHolder.KEY_ID_COLUMN.setText(dataModel.getName());
        viewHolder.ADDRESS_COLUMN.setText(dataModel.getAddress());
        viewHolder.PHONE_COLUMN.setText(dataModel.getPhone_number());
        viewHolder.EMAIL_COLUMN.setText(dataModel.getEmail_number());
       // viewHolder.info.setOnClickListener(this);
       // viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}

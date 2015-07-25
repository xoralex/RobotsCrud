package com.dorofeyev.robotscrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xor on 7/23/15.
 */
public class RobotsListViewAdapter extends BaseAdapter {

    public List<Robot> robots = new ArrayList<Robot>();
    LayoutInflater inflater;
    Context context;


    public RobotsListViewAdapter(Context context, List<Robot> robots) {
        this.robots = robots;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return robots.size();
    }

    @Override
    public Robot getItem(int position) {
        return robots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_robot, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        Robot currentListData = getItem(position);

        mViewHolder.textViewName.setText(currentListData.getName());

        return convertView;
    }

    private class MyViewHolder {
        TextView textViewName;

        public MyViewHolder(View item) {
            textViewName = (TextView) item.findViewById(R.id.textViewName);
        }
    }
}

package com.chromeinfo.stint.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chromeinfo.stint.R;
import com.chromeinfo.stint.models.NavListModel;

import java.util.ArrayList;
import java.util.List;

public class NavListAdaptor extends BaseAdapter {

    private List<NavListModel> navListModels = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public NavListAdaptor(Context context, List<NavListModel> navListModels) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.navListModels = navListModels;
    }

    @Override
    public int getCount() {
        return navListModels.size();
    }

    @Override
    public NavListModel getItem(int position) {
        return navListModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_fragment, null);
            viewHolder = new NavListAdaptor.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tvListNav.setText(navListModels.get(position).getName());
        viewHolder.imvListNav.setImageResource(navListModels.get(position).getDrawable());
        return convertView;
    }

    protected class ViewHolder {
        private TextView tvListNav;
        private ImageView imvListNav;

        public ViewHolder(View view) {
            tvListNav = (TextView) view.findViewById(R.id.tvListNav);
            imvListNav = (ImageView) view.findViewById(R.id.imvListNav);
        }
    }
}
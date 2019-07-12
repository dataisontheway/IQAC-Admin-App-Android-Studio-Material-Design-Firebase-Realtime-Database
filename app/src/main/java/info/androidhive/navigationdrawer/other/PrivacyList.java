package info.androidhive.navigationdrawer.other;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import info.androidhive.navigationdrawer.R;

public class PrivacyList extends ArrayAdapter<Privacy> {

    private Activity context;
    private List<Privacy> privacytList;

    public PrivacyList(Activity context,List<Privacy> privacyList){
        super(context, R.layout.privacy_list_layout,privacyList);
        this.context = context;
        this.privacytList = privacyList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.privacy_list_layout,null,true);

        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

        Privacy privacy = privacytList.get(position);

        textViewDescription.setText(privacy.getPrivacyDescription());

        return listViewItem;

    }
}
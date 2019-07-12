package info.androidhive.navigationdrawer.other;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import info.androidhive.navigationdrawer.R;

public class AboutList extends ArrayAdapter<About> {

    private Activity context;
    private List<About> aboutList;

    public AboutList(Activity context,List<About> aboutList){
        super(context, R.layout.privacy_list_layout,aboutList);
        this.context = context;
        this.aboutList = aboutList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.privacy_list_layout,null,true);

        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

        About about = aboutList.get(position);

        textViewDescription.setText(about.getAboutDescription());

        return listViewItem;

    }

}

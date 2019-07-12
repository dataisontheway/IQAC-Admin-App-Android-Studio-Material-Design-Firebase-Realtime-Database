package info.androidhive.navigationdrawer.other;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import info.androidhive.navigationdrawer.R;

public class NewsList extends ArrayAdapter<News> {

    private Activity context;
    private List<News> newsList;

    public NewsList(Activity context, List<News> newsList) {
        super(context, R.layout.news_list_layout, newsList);
        this.context = context;
        this.newsList = newsList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.news_list_layout,null,true);

        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

        News news = newsList.get(position);

        textViewTitle.setText(news.getNewsTitle());
        textViewDescription.setText(news.getNewsDescription());

        return listViewItem;

    }

}

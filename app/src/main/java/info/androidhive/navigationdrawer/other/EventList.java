package info.androidhive.navigationdrawer.other;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import info.androidhive.navigationdrawer.R;

public class EventList extends ArrayAdapter<Event> {

    private Activity context;
    private List<Event> eventList;

    public EventList(Activity context,List<Event> eventList){
        super(context, R.layout.list_layout,eventList);
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewProgramme = (TextView) listViewItem.findViewById(R.id.textViewProgramme);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);
        TextView textViewVenue = (TextView) listViewItem.findViewById(R.id.textViewVenue);

        Event event = eventList.get(position);

        textViewProgramme.setText(event.getEventProgramme());
        textViewTime.setText(event.getEventTime());
        textViewVenue.setText(event.getEventVenue());

        return listViewItem;

    }
}

package info.androidhive.navigationdrawer.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.activity.AboutUsActivity;
import info.androidhive.navigationdrawer.activity.ChatStartActivity;
import info.androidhive.navigationdrawer.activity.EventsActivity;
import info.androidhive.navigationdrawer.activity.NewsActivity;
import info.androidhive.navigationdrawer.activity.NotificationsActivity;
import info.androidhive.navigationdrawer.activity.PdfActivity;
import info.androidhive.navigationdrawer.activity.PrivacyPolicyActivity;

public class HomeFragment extends Fragment {

    ImageView events,pdf,news,about,privacy,chat,notifications;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        events = (ImageView) getView().findViewById(R.id.imgevents);
        pdf = (ImageView) getView().findViewById(R.id.imgpdf);
        news = (ImageView) getView().findViewById(R.id.imgnews);
        about = (ImageView) getView().findViewById(R.id.imgabout);
        privacy = (ImageView) getView().findViewById(R.id.imgprivacy);
        chat = (ImageView) getView().findViewById(R.id.imgchat);
        notifications = (ImageView) getView().findViewById(R.id.imgnotifications);

        events.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        EventsActivity.class);
                startActivity(intent);
            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        PdfActivity.class);
                startActivity(intent);
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        NewsActivity.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        AboutUsActivity.class);
                startActivity(intent);
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        ChatStartActivity.class);
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        NotificationsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
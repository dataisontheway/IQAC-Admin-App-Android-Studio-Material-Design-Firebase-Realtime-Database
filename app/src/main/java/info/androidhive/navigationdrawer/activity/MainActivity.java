package info.androidhive.navigationdrawer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.fragment.HomeFragment;
import info.androidhive.navigationdrawer.other.CircleTransform;

public class MainActivity extends AppCompatActivity {

    public static final String Database_Path = "All_Image_Uploads_Database";

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "https://educrib.sirv.com/uploads/cc518737bb9a8370e455c8ca801fd621St.AloysiusCollege_Cover.jpg";
    private static final String urlProfileImg = "data:image/png;base64,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";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //fab = (FloatingActionButton) findViewById(R.id.fab);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        //txtName.setText("Ravi Tamada");
        //txtWebsite.setText("www.androidhive.info");

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {

        return new HomeFragment();

    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_pdf:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, PdfActivity.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_chat:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, ChatStartActivity.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_news:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, NewsActivity.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_events:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, EventsActivity.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_notifications:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
                        drawer.closeDrawers();
                        return true;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}

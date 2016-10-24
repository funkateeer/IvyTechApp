package net.androidbootcamp.ivytechapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import net.androidbootcamp.ivytechapp.CustomListAdapter.CustomListAdapter;


//public class MainActivity extends ListActivity {
public class MainActivity extends Activity {

    ListView list;
    String[] itemname = {
            "Campus Map",
            "Instructor Info",
            "Calendar of Events",
            "Dining",
            "FAQs"
    };

    Integer[] imgid = {
            R.drawable.googlemapsicon,
            R.drawable.contactinfo,
            R.drawable.calendaricon,
            R.drawable.restauranticon,
            R.drawable.faqimage
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                switch(position) {
                    case 0: startActivity(new Intent(MainActivity.this, MapsActivity.class));
                        break;
                    case 1: Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stackoverflow.com"));
                            startActivity(browserIntent2);
                        break;
                    case 2: Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://calendar.google.com/calendar/embed?showTitle=0&showTabs=0&showCalendars=0&mode=AGENDA&height=600&wkst=1&bgcolor=%23FFFFFF&src=ivytech.calendars@gmail.com&color=%23125A12&src=a650fjdiufg9dkgho15tvalgso@group.calendar.google.com&color=%23125A12&ctz=America/New_York"));
                            startActivity(browserIntent3);
                        break;
                    case 3: startActivity(new Intent(MainActivity.this, DiningActivityTake2.class));
                        break;
                    case 4: Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ivytechfaq.com/index.html?utm_source=IvyTech.edu/FAQ&utm_medium=Redirect&utm_campaign=FAQ"));
                            startActivity(browserIntent4);
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}

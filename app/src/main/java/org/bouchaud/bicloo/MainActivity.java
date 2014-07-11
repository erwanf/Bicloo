package org.bouchaud.bicloo;


import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

import org.bouchaud.bicloo.Session.BikeStationsListener;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{

  public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

  private MainActivity self = this;

  BikeStationListAdapter bikesAdapter;
  TownContractListAdapter townsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    bikesAdapter = new BikeStationListAdapter(self, new ArrayList<BikeStation>());
    townsAdapter = new TownContractListAdapter(self, new ArrayList<TownContract>());

    ListView listView = (ListView) findViewById(R.id.listView1);

    listView.setAdapter(bikesAdapter);

    this.askRefreshBike();

    Button refreshButton = (Button) findViewById(R.id.refreshButton);
    refreshButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        self.askRefreshBike();
      }
    });
  }

  private void askRefreshBike(){
    Session.getInstance(self).getBikeStations(new BikeStationsListener() {

      @Override
      public void bikeStationsUpdated(ArrayList<BikeStation> bikeStation) {
        bikesAdapter.setBikes(bikeStation);
        bikesAdapter.notifyDataSetChanged();
      }
    });


  /*  Session.getInstance(self).getTownContracts(new Session.TowncontractsListener() {
      @Override
      public void TownContractsUpdated(ArrayList<TownContract> townContracts) {
        townsAdapter.setTowns(townContracts);
        townsAdapter.notifyDataSetChanged();
      }
    });
    */
  }

  private void askRefreshtown(){

      Session.getInstance(self).getTownContracts(new Session.TowncontractsListener() {
        @Override
        public void TownContractsUpdated(ArrayList<TownContract> townContracts) {
          townsAdapter.setTowns(townContracts);
          townsAdapter.notifyDataSetChanged();
        }
      });
    }


      public boolean onCreateOptionsMenu(Menu menu){

    getMenuInflater().inflate(R.menu.main, menu);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      ActionBar actionBar = getActionBar();
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
      case R.id.town_choice:

        ListView listView2 = (ListView) findViewById(R.id.listView1);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(MainActivity.this, TownContractListAdapter.class);
            //String message = ;
            //i.putExtra(EXTRA_MESSAGE, message);
            startActivity(i);

          }
        });
        listView2.setAdapter(townsAdapter);

        this.askRefreshtown();

        // Comportement du bouton "town"
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }



}

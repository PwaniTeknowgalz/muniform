package ke.co.muniform.muniform;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import ke.co.muniform.muniform.data.Supplies;
import ke.co.muniform.muniform.db.MuniformDb;
import ke.co.muniform.muniform.utils.MainListHolder;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

/**
 * Created by  on 4/6/2016.
 */
public class SearchActivity extends BaseActivity{
    String url;
    RecyclerView list ;
    RelativeLayout error;
    TextView errortxt,time;
    private Menu menu;

    RecyclerView.LayoutManager m=null;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.searchactionactivity_layout;
    }

    @Override
    protected String getTitleName() {
        return "Search Supplies List";
    }

    @Override
    protected int getToolbarResourceId() {
        return R.id.holder;
    }

    @Override
    protected void activityOutput() {

        url=getResources().getString(R.string.baseurl)+getResources().getString(R.string.api);
        list=(RecyclerView)findViewById(R.id.list);
        error=(RelativeLayout) findViewById(R.id.error);
        errortxt=(TextView) findViewById(R.id.errortext);
        time=(TextView) findViewById(R.id.time);
        m= new LinearLayoutManager(getApplicationContext());

       




        if(MuniformDb.getAll().isEmpty()){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);

        }else{
            List<MuniformDb> all2 = MuniformDb.getAll();
            ArrayList<Supplies> ps = fetchAll(all2.get(0).getData());

            ArrayList<Supplies> sch= null;
            try {
                sch = search(ps,getIntent().getStringExtra("searchterm"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(sch.isEmpty()){
                list.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                errortxt.setText("Your search query doesn't match any data in our database. Please search again.");
            }else{
            EasyRecyclerAdapter e = new EasyRecyclerAdapter(SearchActivity.this, MainListHolder.class, sch);
            list.setLayoutManager(m);

            list.setAdapter(e);
        }}

    }
    public ArrayList<Supplies> search(ArrayList<Supplies> s,String content) throws ParseException {
        //ArrayList<ProductsSupermarkets> newlist= new ArrayList<>();

        ArrayList<Supplies> retain = new ArrayList<Supplies>(s.size());
        for (Supplies dealer : s) {
            if (dealer.getName().contains(content)) {
                retain.add(dealer);
            }else if (dealer.getLocation().contains(content)) {
                retain.add(dealer);
            }else if (dealer.getDetails().contains(content)) {
                retain.add(dealer);
            }else if (dealer.getPrice().contains(content)) {
                retain.add(dealer);
            }else if (dealer.getSupply_type().contains(content)) {
                retain.add(dealer);
            }

        }

        // either assign 'retain' to 'wsResponse.Dealers' or ...
        s.clear();
        s.addAll(retain);
        return s;
    }
    public ArrayList<Supplies> fetchAll(String dataset){
        JsonElement jelements = new JsonParser().parse(dataset);
        ArrayList<Supplies > rowItems=new ArrayList<>();
        JsonArray j=jelements.getAsJsonArray();
        for (int i=0;i<j.size();i++) {
            Supplies dta = new Gson().fromJson(j.get(i).getAsJsonObject(),Supplies .class);
            rowItems.add(dta);
        }
        return rowItems;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        menu.getItem(0).setIcon(new IconicsDrawable(this).icon(Ionicons.Icon.ion_search).color(Color.WHITE).sizeDp(24));
        menu.getItem(1).setIcon(new IconicsDrawable(this).icon(Ionicons.Icon.ion_android_add).color(Color.WHITE).sizeDp(24));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            SearchPopup p = new SearchPopup();
            p.show(getSupportFragmentManager(),"SearchPopup");
            return true;
        }else if (id == R.id.action_add) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@muniform.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Request to post my supply");
            if (intent.resolveActivity(this.getPackageManager()) != null) {
                startActivity(intent);
            }

            Toast.makeText(this, "Creating Email Message", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

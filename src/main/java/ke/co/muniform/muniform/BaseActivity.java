package ke.co.muniform.muniform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


/**
 * Created by  on 4/4/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Drawer result = null;

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResourceId());
        activityOutput();

        toolbar = (Toolbar) findViewById(getToolbarResourceId());
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitleName());
        // Handle Toolbar

        refresh(bundle);
    }

    //Methods that are abbstracted
    public void refresh(Bundle bundle) {



        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSavedInstance(bundle)
                .addDrawerItems(


                        new PrimaryDrawerItem().withName("All Supplies").withIcon(Ionicons.Icon.ion_android_list).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("Search").withIcon(Ionicons.Icon.ion_search).withIdentifier(2).withSelectable(false),

                        new SectionDrawerItem().withName("Categories"),
                        new PrimaryDrawerItem().withName("New Uniforms").withIcon(Ionicons.Icon.ion_tshirt_outline).withIdentifier(3).withSelectable(false),
                        new PrimaryDrawerItem().withName("New Books").withIcon(Ionicons.Icon.ion_ios_book_outline).withIdentifier(4).withSelectable(false),
                        new PrimaryDrawerItem().withName("Second-Hand Books").withIcon(Ionicons.Icon.ion_ios_book).withIdentifier(5).withSelectable(false),
                        new PrimaryDrawerItem().withName("Second-Hand Uniforms").withIcon(Ionicons.Icon.ion_tshirt).withIdentifier(6).withSelectable(false)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {

                           if (drawerItem.getIdentifier() == 2) {
                                SearchPopup p = new SearchPopup();
                                p.show(getSupportFragmentManager(), "SearchPopup");
                            } else if (drawerItem.getIdentifier() == 3) {
                               Intent y = new Intent(getApplicationContext(), SearchActivity.class);
                               y.putExtra("searchterm","NEW UNIFORM" );
                               startActivity(y);

                            }else if (drawerItem.getIdentifier() == 4) {
                               Intent y = new Intent(getApplicationContext(), SearchActivity.class);
                               y.putExtra("searchterm","NEW BOOKS" );
                               startActivity(y);

                           }else if (drawerItem.getIdentifier() == 6) {
                               Intent y = new Intent(getApplicationContext(), SearchActivity.class);
                               y.putExtra("searchterm","SECOND-HAND UNIFORM" );
                               startActivity(y);


                           }else if (drawerItem.getIdentifier() == 5) {
                               Intent y = new Intent(getApplicationContext(), SearchActivity.class);
                               y.putExtra("searchterm","SECOND-HAND BOOKS" );
                               startActivity(y);

                           } else if (drawerItem.getIdentifier() == 1) {
                                Intent x = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(x);
                            }
                        }

                        return false;
                    }
                })
                .build();

    }

    protected abstract int getLayoutResourceId();

    protected abstract String getTitleName();

    protected abstract int getToolbarResourceId();

    protected abstract void activityOutput();
}
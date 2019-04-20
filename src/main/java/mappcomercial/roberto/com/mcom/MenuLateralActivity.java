package mappcomercial.roberto.com.mcom;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MenuLateralActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);

        if (id == R.id.icliente) {
            // Handle the camera action
            ClienteFragment fragment = new ClienteFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.flcontainer, fragment).commit();
            transaction.replace(R.id.flcontainer, fragment).commit();
        } else if (id == R.id.ifornecedor) {
            // Handle the camera action
            FornecedorFragment fragment = new FornecedorFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.flcontainer, fragment).commit();
            transaction.replace(R.id.flcontainer, fragment).commit();
        } else if (id == R.id.icategoria) {
            // Handle the camera action
            CategoriaFragment fragment = new CategoriaFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.flcontainer, fragment).commit();
            transaction.replace(R.id.flcontainer, fragment).commit();
        } else if (id == R.id.iproduto) {
            ProdutoFragment fragment = new ProdutoFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.flcontainer, fragment).commit();
            transaction.replace(R.id.flcontainer, fragment).commit();
        } else if (id == R.id.ivenda) {
            VendaFragment fragment = new VendaFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.flcontainer, fragment).commit();
            transaction.replace(R.id.flcontainer, fragment).commit();
        } else if (id == R.id.icompras) {
            CompraFragment fragment = new CompraFragment();
            //getSupportFragmentManager().beginTransaction().replace(R.id.flcontainer, fragment).commit();
            transaction.replace(R.id.flcontainer, fragment).commit();
        } else if (id == R.id.icpagar) {

        } else if (id == R.id.icreceber) {

        } else if (id == R.id.icaixa) {

        } else if (id == R.id.ivisita) {

        } else if (id == R.id.isair) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

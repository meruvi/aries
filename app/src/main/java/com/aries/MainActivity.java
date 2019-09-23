package com.aries;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aries.controller.MenuController;
import com.aries.controller.PersonalController;
import com.aries.entity.Personal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Personal personal;
    private TextView nombrePersonal, cargoPersonal;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        nombrePersonal = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nombrePersonal);
        cargoPersonal = (TextView) navigationView.getHeaderView(0).findViewById(R.id.cargoPersonal);

        if (getIntent().getExtras() != null) {
            personal = (Personal) getIntent().getExtras().getSerializable("personal");
            cargarMenuItems(personal.getCodCargo());

            PersonalController personalController = new PersonalController(this);
            nombrePersonal.setText(personal.getNombresPersonal() + " " + personal.getApPaternoPersonal() + " " + personal.getApMaternoPersonal());
            cargoPersonal.setText(personalController.getNombreCargo(personal.getCodCargo()));
        }

    }

    public void cargarMenuItems(int codCargo){
        MenuController cMenu = new MenuController(this);
        ArrayList<Integer> listMenuSegunCargo = cMenu.getListMenuByCargo(codCargo);
        Menu menu = navigationView.getMenu();
        for(int i = 0; i < listMenuSegunCargo.size(); i++){
            int cod = listMenuSegunCargo.get(i) - 1;
            menu.getItem(cod).setVisible(true);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        Fragment fragment = null;

        Bundle args = new Bundle();
        switch (item.getItemId()) {
            case R.id.nav_pedidos:
                //fragment = TabbedActivityPedido.newInstance();
                //args.putSerializable("personal", personal);
                break;
            case R.id.nav_cuentas_cobrar:
                break;
            case R.id.nav_georef_clientes:
                break;
            case R.id.nav_georef_medicos:
                break;
            case R.id.nav_cobranzas:
                break;
            case R.id.nav_boleta_visita:
                break;
            case R.id.nav_visitas:
                break;
            case R.id.nav_presupuesto:
                break;
            case R.id.nav_entrega_pedidos:
                break;
            case R.id.nav_ajustes:
                fragment = new SincronizacionConfigFragment();
                args.putSerializable("personal", personal);
                break;
            case R.id.nav_salir:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                break;
        }

        if(fragment != null) {
            fragment.setArguments(args);
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }else if(intent != null){
            startActivity(intent);
            finish();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

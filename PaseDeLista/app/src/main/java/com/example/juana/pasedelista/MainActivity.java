 package com.example.juana.pasedelista;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Campeon> SHURIMA;
    private ArrayList<Campeon> JONIA;
    private ArrayList<String> matriculas1;
    private ArrayList<String> matriculas2;
    private ArrayList<Integer> asistencia1;
    private ArrayList<Integer> asistencia2;
    private int grupo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navegacion);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending email Wait...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                switch (grupo){
                    case 1 : enviarCorreo(matriculas1, asistencia1); break;
                    case 2 : enviarCorreo(matriculas2, asistencia2); break;
                }

            }
        });
        SHURIMA = new ArrayList<>();
        SHURIMA.add(new Campeon(R.drawable.amumusquare, "Amumu", "16130128", true));
        SHURIMA.add(new Campeon(R.drawable.azirsquare, "Azir", "16130258", true));
        SHURIMA.add(new Campeon(R.drawable.nasussquare, "Nasus", "16130213", true));
        SHURIMA.add(new Campeon(R.drawable.rammussquare, "Rammus", "16130223", true));
        SHURIMA.add(new Campeon(R.drawable.renektonsquare, "Renekton", "16130168", true));
        SHURIMA.add(new Campeon(R.drawable.rengarsquare, "Rengar", "16130201", true));
        SHURIMA.add(new Campeon(R.drawable.sivirsquare, "Sivir", "16130019", true));
        SHURIMA.add(new Campeon(R.drawable.skarnersquare, "Skarner", "16130256", true));
        SHURIMA.add(new Campeon(R.drawable.taliyahsquare, "Taliyah", "16130253", true));
        SHURIMA.add(new Campeon(R.drawable.xerathsquare, "Xerath", "16130199", true));

        JONIA = new ArrayList<>();
        JONIA.add(new Campeon(R.drawable.ahrisquare, "Ahri", "15130128", true));
        JONIA.add(new Campeon(R.drawable.akalisquare, "Akali", "15130147", true));
        JONIA.add(new Campeon(R.drawable.ireliasquare, "Irelia", "15130666", true));
        JONIA.add(new Campeon(R.drawable.jhinsquare, "Jhin", "15130158", true));
        JONIA.add(new Campeon(R.drawable.kaynsquare, "Kayn", "15130234", true));
        JONIA.add(new Campeon(R.drawable.lee_sinsquare, "Lee sin", "15130236", true));
        JONIA.add(new Campeon(R.drawable.master_yisquare, "Master Yi", "15130237", true));
        JONIA.add(new Campeon(R.drawable.xayahsquare, "Xayah", "15130156", true));
        JONIA.add(new Campeon(R.drawable.yasuosquare, "Yasuo", "15130238", true));
        JONIA.add(new Campeon(R.drawable.zedsquare, "Zed", "15130527", true));

        agregarMatriculas();
        agregarAsistencias();

        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView listaAlumnos = findViewById(R.id.rv_listaGrupos);
        listaAlumnos.setLayoutManager(llm);
        AdaptadorAlumnos alumnoAdaptador = new AdaptadorAlumnos(SHURIMA, asistencia1);
        listaAlumnos.setAdapter(alumnoAdaptador);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.agregar_correo) {
            obtenerEmail();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navegacion_grupo1:
                    grupo = 1;
                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    RecyclerView listaAlumnos = findViewById(R.id.rv_listaGrupos);
                    listaAlumnos.setLayoutManager(llm);
                    AdaptadorAlumnos alumnoAdaptador = new AdaptadorAlumnos(SHURIMA, asistencia1);
                    listaAlumnos.setAdapter(alumnoAdaptador);
                    Log.d("GRUPO ", "1");
                    return true;
                case R.id.navegacion_grupo2:
                    grupo = 2;
                    LinearLayoutManager llm2 = new LinearLayoutManager(MainActivity.this);
                    llm2.setOrientation(LinearLayoutManager.VERTICAL);
                    RecyclerView listaAlumnos2 = findViewById(R.id.rv_listaGrupos);
                    listaAlumnos2.setLayoutManager(llm2);
                    AdaptadorAlumnos alumnoAdaptador2 = new AdaptadorAlumnos(JONIA, asistencia2);
                    listaAlumnos2.setAdapter(alumnoAdaptador2);
                    Log.d("GRUPO ", "2");
                    return true;
            }
            return false;
        }
    };

    public void obtenerEmail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setTitle("Ingrese email:");
        final View dialoglayout = inflater.inflate(R.layout.mandar_email, null);
        builder.setView(dialoglayout)
                // Add action buttons
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etEmail = dialoglayout.findViewById(R.id.tidt_email);
                        String email = etEmail.getText().toString();
                        Log.d("EMAIL: ", ""+email);
                        SharedPreferences preferences  = getSharedPreferences("Email", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", email);
                        editor.commit();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void agregarMatriculas(){
        matriculas1 = new ArrayList<>();
        matriculas2 = new ArrayList<>();
        matriculas1.add("16130128");
        matriculas1.add("16130258");
        matriculas1.add("16130213");
        matriculas1.add("16130223");
        matriculas1.add("16130168");
        matriculas1.add("16130201");
        matriculas1.add("16130019");
        matriculas1.add("16130256");
        matriculas1.add("16130253");
        matriculas1.add("16130199");
        matriculas2.add("15130128");
        matriculas2.add("15130147");
        matriculas2.add("15130666");
        matriculas2.add("15130158");
        matriculas2.add("15130234");
        matriculas2.add("15130236");
        matriculas2.add("15130237");
        matriculas2.add("15130156");
        matriculas2.add("15130238");
        matriculas2.add("15130527");

    }

    public void agregarAsistencias(){
        asistencia1 = new ArrayList<>();
        asistencia2 = new ArrayList<>();
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);

    }

    public void enviarCorreo(ArrayList<String> matriculas, ArrayList<Integer> asistecias){
        Intent intent = new Intent(Intent.ACTION_SEND);

        SharedPreferences preferences  = getSharedPreferences("Email", Context.MODE_PRIVATE);
        String emailGuardado = preferences.getString("email", "");

        String[] to = {emailGuardado};
        String[] cc = {""};
        String contenido = "";

        for (int i = 0; i < 10; i++) {
            contenido = contenido.concat(matriculas.get(i) + "-"+ asistecias.get(i)+"\n");
        }

        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, "PASE DE LISTA LAZCANO-15130210");
        intent.putExtra(Intent.EXTRA_TEXT,"Lista asistencia: \n"+contenido);
        try {
            startActivity(Intent.createChooser(intent, "are you mad?"));
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

}

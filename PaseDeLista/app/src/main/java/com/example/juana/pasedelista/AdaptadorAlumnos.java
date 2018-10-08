package com.example.juana.pasedelista;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorAlumnos extends RecyclerView.Adapter<AdaptadorAlumnos.AdaptadorAlumnosViewHold>{

    private ArrayList<Campeon> campeons;
    private ArrayList<Integer> asistencias;




    public AdaptadorAlumnos(ArrayList<Campeon> campeons, ArrayList<Integer> asistencias) {
        this.campeons = campeons;
        this.asistencias = asistencias;
    }

    @NonNull
    @Override
    public AdaptadorAlumnos.AdaptadorAlumnosViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modelo_alumno, viewGroup, false);
        return new AdaptadorAlumnosViewHold(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorAlumnos.AdaptadorAlumnosViewHold adaptadorAlumosViewHold, final int i) {
        final Campeon campeon = campeons.get(i);
        adaptadorAlumosViewHold.foto.setImageResource(campeon.getFoto());
        adaptadorAlumosViewHold.nombre.setText(campeon.getNombre());
        adaptadorAlumosViewHold.matricula.setText(campeon.getMatricula());
        adaptadorAlumosViewHold.asistencia.setChecked(campeon.isAsistencia());

        adaptadorAlumosViewHold.asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                campeon.setAsistencia(adaptadorAlumosViewHold.asistencia.isChecked());
                if(campeon.isAsistencia())
                    asistencias.set(i, 1);
                else {
                    asistencias.set(i, 0);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return campeons.size();
    }

    public class AdaptadorAlumnosViewHold extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView nombre;
        private TextView matricula;
        private CheckBox asistencia;


        public AdaptadorAlumnosViewHold(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.fotoAlumno);
            nombre = itemView.findViewById(R.id.nombre);
            matricula = itemView.findViewById(R.id.matricula);
            asistencia = itemView.findViewById(R.id.estrella);

        }
    }
}

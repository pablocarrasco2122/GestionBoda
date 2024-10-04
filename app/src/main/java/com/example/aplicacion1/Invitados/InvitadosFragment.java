package com.example.aplicacion1.Invitados;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion1.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class InvitadosFragment extends Fragment {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invitados, container, false);

        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("invitados"), MainModel.class)
                        .build();


        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}

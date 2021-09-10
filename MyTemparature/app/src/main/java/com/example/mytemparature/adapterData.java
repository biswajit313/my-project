package com.example.mytemparature;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class adapterData  extends FirestoreRecyclerAdapter<model,adapterData.viewHolder> {
    public adapterData(@NonNull FirestoreRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull model model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.temperature.setText(model.getTemperature());
        holder.phone.setText(model.getPhone());
        holder.time.setText(model.getTime());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentSnapshot snapshot=getSnapshots().getSnapshot(holder.getAdapterPosition());
                final String id=snapshot.getId();
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.delete.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Delete....?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseFirestore f=FirebaseFirestore.getInstance();
                        f.collection("temperature").document(id).delete();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.show();

            }
        });
    }




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new viewHolder(v);
    }


    class viewHolder extends RecyclerView.ViewHolder{
        TextView name,email,temperature,phone,time;
        Button delete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView4);
            email=itemView.findViewById(R.id.textView6);
            temperature=itemView.findViewById(R.id.textView7);
            phone=itemView.findViewById(R.id.textView8);
            time=itemView.findViewById(R.id.textView9);
            delete= delete.findViewById(R.id.button3);





        }
    }
}


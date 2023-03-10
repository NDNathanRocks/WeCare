package com.example.wecare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.invCount.setText(model.getInvCount());
        holder.nametext.setText(model.getName());
//        Glide.with(holder.img1.getContext()).load(model.getPurl()).into(holder.img1);

            holder.nametext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    // set Fragment class Arguments
                    descfragment fragobj = new descfragment(model.getName(), model.getInvCount());
                    fragobj.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, fragobj).addToBackStack(null).commit();
                }
            });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }


    public class myviewholder extends RecyclerView.ViewHolder {
        //        ImageView img1;
        TextView invCount, nametext;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

//            img1=itemView.findViewById(R.id.img1);
            invCount = itemView.findViewById(R.id.invCount);
            nametext = itemView.findViewById(R.id.nametext);
        }
    }
}
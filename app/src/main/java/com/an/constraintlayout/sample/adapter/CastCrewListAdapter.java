package com.an.constraintlayout.sample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.an.constraintlayout.sample.R;
import com.an.constraintlayout.sample.databinding.CastCrewItemBinding;
import com.an.constraintlayout.sample.model.Cast;
import com.an.constraintlayout.sample.model.Crew;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastCrewListAdapter extends RecyclerView.Adapter<CastCrewListAdapter.CustomViewHolder> {

    private String type;
    private Context context;
    private List<Cast> casts;
    private List<Crew> crews;
    public CastCrewListAdapter(Context context, String type, List<Cast> casts, List<Crew> crews) {
        this.context = context;
        this.type = type;
        this.casts = casts;
        this.crews = crews;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CastCrewItemBinding itemBinding = CastCrewItemBinding.inflate(layoutInflater, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(itemBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if(isCast()) {
            Cast cast = getCastItem(position);
            Picasso.get().load(cast.getProfilePath()).error(R.drawable.profile_placeholder).into(holder.binding.profileImage);
            holder.binding.txtName.setText(cast.getName());
            holder.binding.txtInfo.setText(cast.getCharacter());

        } else {
            Crew crew = getCrewItem(position);
            Picasso.get().load(crew.getProfilePath()).error(R.drawable.profile_placeholder).into(holder.binding.profileImage);
            holder.binding.txtName.setText(crew.getName());
            holder.binding.txtInfo.setText(crew.getJob());
        }
    }

    @Override
    public int getItemCount() {
        if(isCast()) return casts.size();
        return crews.size();
    }

    public Boolean isCast() {
        if(type.equalsIgnoreCase("cast"))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Cast getCastItem(int position) {
        return casts.get(position);
    }

    public Crew getCrewItem(int position) {
        return crews.get(position);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private CastCrewItemBinding binding;

        public CustomViewHolder(CastCrewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

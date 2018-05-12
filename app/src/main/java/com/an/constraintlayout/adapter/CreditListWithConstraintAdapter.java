package com.an.constraintlayout.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.an.constraintlayout.AppConstants;
import com.an.constraintlayout.R;
import com.an.constraintlayout.databinding.CreditListWithConstraintItemBinding;
import com.an.constraintlayout.model.Cast;
import com.an.constraintlayout.model.Crew;
import com.squareup.picasso.Picasso;

import java.util.List;

    public class CreditListWithConstraintAdapter extends RecyclerView.Adapter<CreditListWithConstraintAdapter.CustomViewHolder> {

        private String type;
        private Context context;
        private List<Cast> casts;
        private List<Crew> crews;
        public CreditListWithConstraintAdapter(Context context, String type, List<Cast> casts, List<Crew> crews) {
            this.context = context;
            this.type = type;
            this.casts = casts;
            this.crews = crews;
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            CreditListWithConstraintItemBinding itemBinding = CreditListWithConstraintItemBinding.inflate(layoutInflater, parent, false);
            CustomViewHolder viewHolder = new CustomViewHolder(itemBinding);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            if(isCast()) {
                Cast cast = getCastItem(position);
                Picasso.get().load(String.format(AppConstants.IMAGE_URL, cast.getProfilePath())).error(R.drawable.profile_placeholder).into(holder.binding.profileImage);
                holder.binding.txtName.setText(cast.getName());
                holder.binding.txtInfo.setText(cast.getCharacter());

            } else {
                Crew crew = getCrewItem(position);
                Picasso.get().load(String.format(AppConstants.IMAGE_URL, crew.getProfilePath())).error(R.drawable.profile_placeholder).into(holder.binding.profileImage);
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
            if(type.equalsIgnoreCase(AppConstants.CREDIT_CAST))
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
            private CreditListWithConstraintItemBinding binding;

            public CustomViewHolder(CreditListWithConstraintItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }

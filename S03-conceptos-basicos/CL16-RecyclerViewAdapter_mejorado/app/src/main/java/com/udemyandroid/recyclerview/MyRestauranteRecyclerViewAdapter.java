package com.udemyandroid.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udemyandroid.recyclerview.RestauranteFragment.OnListFragmentInteractionListener;
import java.util.List;

public class MyRestauranteRecyclerViewAdapter extends RecyclerView.Adapter<MyRestauranteRecyclerViewAdapter.ViewHolder> {

    private final List<Restaurante> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyRestauranteRecyclerViewAdapter(List<Restaurante> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_restaurante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Rescatamos los datos del elemento que ocupa la posición "position"
        holder.mItem = mValues.get(position);

        holder.textViewNombreRestaurante.setText(holder.mItem.getNombre());
        holder.textViewDireccionRestaurante.setText(holder.mItem.getDireccion());
        holder.ratingBarValoracionRestaurante.setRating(holder.mItem.getValoracion());

        Picasso.get()
                .load(holder.mItem.getUrlPhoto())
                .resize(400, 150)
                .centerCrop()
                .into(holder.imageViewPhotoRestaurante);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewNombreRestaurante;
        public final TextView textViewDireccionRestaurante;
        public final ImageView imageViewPhotoRestaurante;
        public final RatingBar ratingBarValoracionRestaurante;
        public Restaurante mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewNombreRestaurante = view.findViewById(R.id.textViewNombre);
            textViewDireccionRestaurante = view.findViewById(R.id.textViewDireccion);
            imageViewPhotoRestaurante = view.findViewById(R.id.imageViewPhoto);
            ratingBarValoracionRestaurante = view.findViewById(R.id.ratingBarValoracion);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewNombreRestaurante.getText() + "'";
        }
    }
}

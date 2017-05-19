package com.apmv1.webservices;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Adaptador del recycler view
 */
public class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.MetaViewHolder>
        implements ItemClickListener {

    /**
     * Lista de objetos {@link Meta} que representan la fuente de datos
     * de inflado
     */
    private List<Meta> items;

    /*
    Contexto donde actua el recycler view
     */
    private Context context;


    public MetaAdapter(List<Meta> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MetaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);
        return new MetaViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(MetaViewHolder viewHolder, int i) {
        viewHolder.titulo.setText(items.get(i).getTitulo());
        viewHolder.prioridad.setText(items.get(i).getPrioridad());
        viewHolder.fechaLim.setText(items.get(i).getFechaLim());
        viewHolder.categoria.setText(items.get(i).getCategoria());

        final ImageView avatarImage = viewHolder.imageView;

        Glide
                .with(context)
                .load(Uri.parse("file:///android_asset/" + items.get(i).getImage()))
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(avatarImage){
                    @Override
                    protected void setResource(Bitmap resource){
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                        drawable.setCircular(true);
                        avatarImage.setImageDrawable(drawable);
                    }
                });

    }

    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {
        DetailActivity.launch(
                (Activity) context, items.get(position).getIdMeta());


    }


    public static class MetaViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView titulo;
        public TextView prioridad;
        public TextView fechaLim;
        public TextView categoria;

        // TODO: 19/05/2017 update for image
        public ImageView imageView;

        public ItemClickListener listener;

        public MetaViewHolder(View v, ItemClickListener listener) {
            super(v);
            titulo = (TextView) v.findViewById(R.id.titulo);
            prioridad = (TextView) v.findViewById(R.id.prioridad);
            fechaLim = (TextView) v.findViewById(R.id.fecha);
            categoria = (TextView) v.findViewById(R.id.categoria);

            // TODO: 19/05/2017 update for image
            imageView = (ImageView) v.findViewById(R.id.imageView);


            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}


interface ItemClickListener {
    void onItemClick(View view, int position);
}

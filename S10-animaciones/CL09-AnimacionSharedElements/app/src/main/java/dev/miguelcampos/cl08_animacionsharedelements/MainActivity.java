package dev.miguelcampos.cl08_animacionsharedelements;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private GridView mGridView;
    private GridAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the GridView and set the adapter
        mGridView = findViewById(R.id.grid);
        mGridView.setOnItemClickListener(this);
        mAdapter = new GridAdapter();
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Item item = (Item) adapterView.getItemAtPosition(position);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constantes.EXTRA_ID, item.getId());


        // Obtenemos una referencia a los elementos visuales que queremos transicionar
        ImageView imageViewPhoto = view.findViewById(R.id.imageview_item);
        TextView textViewTituloPhoto = view.findViewById(R.id.textview_name);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                new Pair<View, String>(imageViewPhoto, Constantes.SHARED_VIEW_PHOTO),
                new Pair<View, String>(textViewTituloPhoto, Constantes.SHARED_VIEW_TITLE)
        );

        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());

    }


    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Item.ITEMS.length;
        }

        @Override
        public Item getItem(int position) {
            return Item.ITEMS[position];
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.grid_item, viewGroup, false);
            }

            final Item item = getItem(position);

            // Cargar thumbnail
            ImageView image = view.findViewById(R.id.imageview_item);
            Picasso.with(image.getContext())
                    .load(item.getThumbnailUrl())
                    .into(image);

            // Setear el texto
            TextView name = view.findViewById(R.id.textview_name);
            name.setText(item.getName());

            return view;
        }
    }
}

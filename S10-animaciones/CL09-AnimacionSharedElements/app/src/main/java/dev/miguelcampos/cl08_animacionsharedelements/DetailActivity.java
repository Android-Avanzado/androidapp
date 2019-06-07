package dev.miguelcampos.cl08_animacionsharedelements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivHeader;
    private TextView tvTitle;

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt(Constantes.EXTRA_ID, 0);
        item = Item.getItem(id);

        ivHeader = findViewById(R.id.imageViewHeader);
        tvTitle = findViewById(R.id.textViewTitle);

        loadItem();
    }

    private void loadItem() {
        tvTitle.setText(item.getName());

        Picasso.with(this)
                .load(item.getPhotoUrl())
                .noPlaceholder()
                .noFade()
                .into(ivHeader);
    }

}

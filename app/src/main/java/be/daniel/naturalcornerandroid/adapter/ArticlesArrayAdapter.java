package be.daniel.naturalcornerandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.daniel.naturalcornerandroid.R;
import be.daniel.naturalcornerandroid.model.Article;

/**
 * Created by ivymike on 12/09/16.
 */
public class ArticlesArrayAdapter extends ArrayAdapter<Article> {
    private final Context context;
    private final List<Article> values;
    public ArticlesArrayAdapter(Context context, List<Article> objects) {
        super(context, android.R.layout.simple_list_item_activated_1, objects);
        this.context=context;
        this.values= objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_layout, parent, false);
        TextView tv = (TextView) rowView.findViewById(R.id.tv_item_row);
        TextView tvPrice = (TextView) rowView.findViewById(R.id.tv_item_price_row);
        ImageView iv = (ImageView) rowView.findViewById(R.id.image_article);
        tv.setText(values.get(position).getDenomination());
        tvPrice.setText(values.get(position).getPrixUnitaire() + " €" );
        return rowView;
    }
}

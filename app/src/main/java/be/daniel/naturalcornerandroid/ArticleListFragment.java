package be.daniel.naturalcornerandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.daniel.naturalcornerandroid.adapter.ArticleCursorAdapter;
import be.daniel.naturalcornerandroid.adapter.ArticlesArrayAdapter;
import be.daniel.naturalcornerandroid.db.ArticleDAO;
import be.daniel.naturalcornerandroid.model.Article;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListFragment extends Fragment {

    private ListView listView;

    public ArticleListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.article_list_item, container, false);
        listView = (ListView)v.findViewById(R.id.list_listview);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            Log.i("BUNDLE", bundle.toString());
            MySerializableList articlesByCategories = (MySerializableList) bundle.getSerializable("list_articles");
            if (articlesByCategories != null) {
                Log.i("ARTICLES", articlesByCategories.toString());
                ArticlesArrayAdapter adapter = new ArticlesArrayAdapter(
                        getActivity(),
                        articlesByCategories.toArrayList()
                );
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Article article = (Article)parent.getItemAtPosition(position);
                        //Toast.makeText(getActivity(), article.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), ShowArticleActivity.class);
                        intent.putExtra("article", article);
                        startActivity(intent);
                    }
                });
            }
        }
        return v;
    }

}

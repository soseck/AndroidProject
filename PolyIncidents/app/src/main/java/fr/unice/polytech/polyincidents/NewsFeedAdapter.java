package fr.unice.polytech.polyincidents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 13/05/2018.
 */

public class NewsFeedAdapter extends ArrayAdapter<Declaration> {
    public NewsFeedAdapter(@NonNull Context context, List<Declaration> resource) {
        super(context, android.R.layout.simple_list_item_1, resource);
    }

    @Override @NonNull
    public View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_declaration_item, null);
        }
        Declaration declaration = getItem(position);
        ((TextView)convertView.findViewById(R.id.titleInfo)).setText(declaration.getTitle());
        //((TextView)convertView.findViewById(R.id.authorInfo)).setText(declaration.getAuthor().getSurname() + declaration.getAuthor().getName());
        //((TextView)convertView.findViewById(R.id.location)).setText(declaration.getLocation());
        ((TextView)convertView.findViewById(R.id.dateInfo)).setText(declaration.getDateValue());
        //((TextView)convertView.findViewById(R.id.urgenceInfo)).setText(declaration.getUrgence());
        //((TextView)convertView.findViewById(R.id.importanceInfo)).setText(declaration.getImportance());
        ((TextView)convertView.findViewById(R.id.contentInfo)).setText(declaration.getContent());
        //((TextView)convertView.findViewById(R.id.tagInfo)).setText(declaration.getTag());

        if(declaration.isWithImage()){
            //String url = declaration.getImageURL();
            //Log.i("ADAPTER", url);
            //new BackGroundImageLoader((ImageView)convertView.findViewById(R.id.Image)).execute(url);
        }


        return convertView;
    }


}

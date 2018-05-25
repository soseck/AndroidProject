package fr.unice.polytech.polyincidents;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 13/05/2018.
 */

public class NewsFeedAdapter extends ArrayAdapter<Declaration> {

    Dialog myDialog;
    Context context;
    Declaration declaration;

    public NewsFeedAdapter(@NonNull Context context, List<Declaration> resource) {
        super(context, android.R.layout.simple_list_item_1, resource);
        this.context = context;
        declaration = new Declaration();
    }

    @Override @NonNull
    public View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_declaration_item, null);
        }
        declaration = getItem(position);
        ((ImageView)convertView.findViewById(R.id.imageInfo)).setImageBitmap(declaration.getImage());
        ((TextView)convertView.findViewById(R.id.titleInfo)).setText(declaration.getTitle());
        //((TextView)convertView.findViewById(R.id.authorInfo)).setText(declaration.getAuthor().getSurname() + declaration.getAuthor().getName());
        //((TextView)convertView.findViewById(R.id.location)).setText(declaration.getLocation());
        ((TextView)convertView.findViewById(R.id.dateInfo)).setText(declaration.getDateValue());
        //((TextView)convertView.findViewById(R.id.urgenceInfo)).setText(declaration.getUrgence());
        //((TextView)convertView.findViewById(R.id.importanceInfo)).setText(declaration.getImportance());
        ((TextView)convertView.findViewById(R.id.contentInfo)).setText(declaration.getContent());
        //((TextView)convertView.findViewById(R.id.tagInfo)).setText(declaration.getTag());


        return convertView;
    }

    public void showPopup(View v) {
        TextView txtclose;
        Button btnChange;
        myDialog.setContentView(R.layout.change_state);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        btnChange = (Button) myDialog.findViewById(R.id.btnChange);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void showPopUp(View view) {
        TextView txtclose;
        Button btnChange;
        myDialog.setContentView(R.layout.change_state);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.addView((myDialog.findViewById(R.id.radioButton_undone)));
        radioGroup.addView((myDialog.findViewById(R.id.radioButton_done)));
        radioGroup.addView((myDialog.findViewById(R.id.radioButton_gettingDone)));
        btnChange = (Button) myDialog.findViewById(R.id.btnChange);
        btnChange.setOnClickListener((i) -> {
            myDialog.dismiss();
            changeStatus(radioGroup.getCheckedRadioButtonId());
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        System.out.println(radioGroup.getCheckedRadioButtonId());
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void changeStatus(Integer checked){
        new StatusChanger(context).execute(declaration.getID(), checked);
    }
}

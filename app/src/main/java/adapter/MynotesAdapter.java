package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;

import java.util.ArrayList;

import models.Notes;

public class MynotesAdapter extends BaseAdapter {
    ArrayList<Notes> note_ArrayList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;


    public MynotesAdapter(ArrayList<Notes> notes_ArrayList,Context context) {
        this.note_ArrayList = notes_ArrayList;
        this.context = context;
        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return note_ArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return note_ArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return note_ArrayList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root= layoutInflater.inflate(R.layout.mynote_item,null);
        ImageView img_note = root.findViewById(R.id.img_note);
        TextView text_note = root.findViewById(R.id.note_name);

        img_note.setImageResource(note_ArrayList.get(i).getImage());
        text_note.setText(note_ArrayList.get(i).getName().toString());

        return root;
    }
}

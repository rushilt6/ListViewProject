package com.example.listviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<Shows> places;
    TextView textviewE;
    TextView textviewM;
    TextView textviewD;
    VideoView videoView;
    int i ;
    int m;
    String d;
    public static final String KEY = "save";
    public static final String KEY2 = "save2";
    public static final String KEY3 = "save3";
    public static final String KEY4 = "save4";
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY,i);
        outState.putInt(KEY2,m);
        outState.putString(KEY3,d);
        outState.putSerializable(KEY4,places);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listview);
        videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.fantasmagorie;
        Uri uri = Uri.parse(videoPath);
        MediaController mediaController = new MediaController(this);
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
            videoView.setVideoURI(uri);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
        }
        places = new ArrayList<>();
        places.add(new Shows(R.drawable.looneytoones,"Looney Tunes",1041,50,"Looney Tunes originated in 1930, and has continued for a while. The show is about a lot of different adventures that certain animals have involving each other and other animals."));
        places.add(new Shows(R.drawable.pf,"Phineas and Ferb",189,5,"Phineas and Ferb originated in 2007 and ended in 2015. The show is about two brothers named Phineas and Ferb who create and go on a lot of different adventures during their summer break"));
        places.add(new Shows(R.drawable.scoobydo,"Scooby Doo",41,40,"Scooby Doo originated in 1969 and has continued for a long time since then. The show is about a group of people who go and solve different mysteries with their dog named Scooby"));
        places.add(new Shows(R.drawable.simpsons,"The Simpsons",639,1,"The Simpsons originated in 1989 and it still continues to this day. It is about a comedy about a family named the Simpsons and the events that surround their daily lives"));
        places.add(new Shows(R.drawable.spongebob,"Spongebob",248,3,"Spongebob orginated in 1999 and still continues to this day. The show is about a sponge who lives underwater and the adventures that surround his daily life."));
        places.add(new Shows(R.drawable.tj,"Tom and Jerry",162,13,"Tom and Jerry originated in 1940 and there are still episodes/movies coming out to this day. The show is about a cat who is trying to get rid of a mouse and fails to do so many times"));
        textviewE = findViewById(R.id.textviewE);
        textviewM = findViewById(R.id.textviewM);
        textviewD = findViewById(R.id.textviewD);
        if(savedInstanceState != null){
            i = savedInstanceState.getInt(KEY);
            m = savedInstanceState.getInt(KEY2);
            d = savedInstanceState.getString(KEY3);
            places = (ArrayList<Shows>) savedInstanceState.getSerializable(KEY4);
            textviewE.setText("Episodes: "+i);
            textviewM.setText("Movies: "+m);
            if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
                textviewD.setText(d);

        }
        ListViewAdapter adapter = new ListViewAdapter(this,R.layout.adapter_listview,places);
        listview.setAdapter(adapter);
    }
    public class ListViewAdapter extends ArrayAdapter<Shows> {
        Context myContext;
        int xml;
        List<Shows> listy;

        public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Shows> objects) {
            super(context, resource, objects);
            myContext = context;
            xml = resource;
            listy = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = inflater.inflate(xml,null);
            final TextView textview = adapterView.findViewById(R.id.adapter_textView);
            ImageView imageview = adapterView.findViewById(R.id.adapter_imageView);
            Button b = adapterView.findViewById(R.id.button);
            textview.setText(listy.get(position).getName());
            imageview.setImageResource(listy.get(position).getImage());
            adapterView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = listy.get(position).getEpisodes();
                    m = listy.get(position).getMovies();
                    d = listy.get(position).getDescription();
                    textviewE.setText("Episodes: " +listy.get(position).getEpisodes());
                    textviewM.setText("Movies: "+listy.get(position).getMovies());
                    if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
                        textviewD.setText(listy.get(position).getDescription());
                }
            });
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    places.remove(position);
                    notifyDataSetChanged();
                }
            });

            return adapterView;

        }
    }
    public class Shows{
        int i;
        String n;
        int e;
        int m;
        String d;
        public Shows(int image, String name,int episodes, int movies,String descrip){
            i = image;
            n = name;
            e = episodes;
            m = movies;
            d = descrip;
        }
        public String getName(){
            return n;
        }
        public int getImage(){
            return i;
        }
        public int getMovies(){
            return m;
        }
        public int getEpisodes(){
            return e;
        }
        public String getDescription() {
            return d;
        }
        }
    }

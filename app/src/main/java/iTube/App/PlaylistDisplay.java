package iTube.App;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iTube.App.data.DBHelper;
import iTube.App.model.Link;
import iTube.App.util.RVAdapter;
import iTube.App.util.Util;

public class PlaylistDisplay extends AppCompatActivity {

    RecyclerView RV;
    RVAdapter adapter;
    List<Link> linkList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_display);

        dbHelper = new DBHelper(this);
        RV = findViewById(R.id.playlistRV);

        linkList = new ArrayList<>();
        adapter = new RVAdapter(linkList, this);
        RV.setAdapter(adapter);
        RV.setLayoutManager(new LinearLayoutManager(this));

        fetchLinksFromDB();

    }

    private void fetchLinksFromDB() {
        Cursor cursor = dbHelper.getAllLinks();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String youtubeLink = cursor.getString(cursor.getColumnIndex(Util.YOUTUBE_LINK));
                linkList.add(new Link(youtubeLink));

            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "No data found in database", Toast.LENGTH_SHORT).show();
        }
    }
}

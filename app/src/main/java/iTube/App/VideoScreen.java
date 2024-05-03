package iTube.App;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class VideoScreen extends AppCompatActivity {

    private WebView videoView;
    private ImageButton Play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);
        videoView = findViewById(R.id.videoView);
        Intent Recieve = getIntent();
        String Link = Recieve.getStringExtra("Video_Link");
        Toast.makeText(VideoScreen.this,  Link, Toast.LENGTH_SHORT).show();
        String video = "<iframe width=\"560\" height=\"315\" " +
                "src=\"" + Link + "\" " + // Concatenate the link variable here
                "title=\"YouTube video player\" " +
                "frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; " +
                "encrypted-media; gyroscope; picture-in-picture; web-share\" " +
                "referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";

        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.getSettings().setDomStorageEnabled(true);
        videoView.setWebChromeClient(new WebChromeClient());
        videoView.loadData(video, "text/html", "utf-8");




    }

}
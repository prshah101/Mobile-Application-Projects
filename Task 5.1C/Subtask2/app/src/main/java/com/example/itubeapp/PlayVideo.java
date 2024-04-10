package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayVideo extends AppCompatActivity {

//    private String stringJavaScript = "<!DOCTYPE html>\n" +
//            "<html>\n" +
//            "  <body>\n" +
//            "    <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->\n" +
//            "    <div id=\"player\"></div>\n" +
//            "\n" +
//            "    <script>\n" +
//            "      // 2. This code loads the IFrame Player API code asynchronously.\n" +
//            "      var tag = document.createElement('script');\n" +
//            "\n" +
//            "      tag.src = \"https://www.youtube.com/iframe_api\";\n" +
//            "      var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
//            "      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
//            "\n" +
//            "      // 3. This function creates an <iframe> (and YouTube player)\n" +
//            "      //    after the API code downloads.\n" +
//            "      var player;\n" +
//            "      function onYouTubeIframeAPIReady() {\n" +
//            "        player = new YT.Player('player', {\n" +
//            "          height: '195',\n" +
//            "          width: '300',\n" +
//            "          videoId: 'M7lc1UVf-VE',\n" +
//            "          playerVars: {\n" +
//            "            'playsinline': 1\n" +
//            "          },\n" +
//            "          events: {\n" +
//            "            'onReady': onPlayerReady,\n" +
//            "            'onStateChange': onPlayerStateChange\n" +
//            "          }\n" +
//            "        });\n" +
//            "      }\n" +
//            "\n" +
//            "      // 4. The API will call this function when the video player is ready.\n" +
//            "      function onPlayerReady(event) {\n" +
//            "        event.target.playVideo();\n" +
//            "      }\n" +
//            "\n" +
//            "      // 5. The API calls this function when the player's state changes.\n" +
//            "      //    The function indicates that when playing a video (state=1),\n" +
//            "      //    the player should play for six seconds and then stop.\n" +
//            "      var done = false;\n" +
//            "      function onPlayerStateChange(event) {\n" +
//            "        if (event.data == YT.PlayerState.PLAYING && !done) {\n" +
//            "          setTimeout(stopVideo, 6000);\n" +
//            "          done = true;\n" +
//            "        }\n" +
//            "      }\n" +
//            "      function stopVideo() {\n" +
//            "        player.stopVideo();\n" +
//            "      }\n" +
//            "    </script>\n" +
//            "  </body>\n" +
//            "</html>";

    private WebView webView;
    Button backHomebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video);
        backHomebtn = findViewById(R.id.backHomebtn);
        String url = getIntent().getStringExtra("url");


        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = getYoutubeId(url);
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        backHomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate back to home
                Intent intent = new Intent(PlayVideo.this, Home.class);
                startActivity(intent);
            }
        });
    }

    public static String getYoutubeId(String url) {
        String pattern = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";

        Pattern compiledPattern = Pattern.compile(pattern,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }/*from w  w  w.  j a  va  2 s .c om*/
        return null;
    }



}
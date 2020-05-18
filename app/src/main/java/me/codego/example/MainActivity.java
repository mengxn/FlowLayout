package me.codego.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import me.codego.view.FlowLayout;

public class MainActivity extends AppCompatActivity {

    private FlowLayout flowLayout;
    private String[] tags = new String[]{
            "hello world !",
            "welcome to my world !",
            "Hi, I'm Rock",
            "Let's do it !",
            "beautiful girl",
            "shot box",
            "android developer",
            "stay foolish, stay hungry!",
            "Mac book",
            "iPhone",
            "Android",
            "Android Studio",
            "Chrome",
            "Nothing is impossible"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flowLayout = findViewById(R.id.flow_layout);
        flowLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        for (String tag : tags) {
            flowLayout.addView(initTag(tag));
        }
    }

    private View initTag(String tag) {
        View view = getLayoutInflater().inflate(R.layout.item_tag, null);
        TextView tagView = view.findViewById(R.id.tag);
        tagView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tagView.setText(tag);
        return view;
    }
}

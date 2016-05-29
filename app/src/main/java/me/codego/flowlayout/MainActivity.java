package me.codego.flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        flowLayout.setDivideHeight(30);

        flowLayout.addView(initTag("hello world !"));
        flowLayout.addView(initTag("welcome to my world !"));
        flowLayout.addView(initTag("Hi, I'm Rock"));
        flowLayout.addView(initTag("Let's do it !"));
        flowLayout.addView(initTag("beautiful girl"));
        flowLayout.addView(initTag("shot box"));
        flowLayout.addView(initTag("android developer"));
        flowLayout.addView(initTag("stay foolish, stay hungry!"));
        flowLayout.addView(initTag("Mac book"));
        flowLayout.addView(initTag("iPhone"));
    }

    private View initTag(String tag) {
        View view = getLayoutInflater().inflate(R.layout.item_tag, null);
        TextView tagView = (TextView) view.findViewById(R.id.tag);
        tagView.setText(tag);
        return view;
    }
}

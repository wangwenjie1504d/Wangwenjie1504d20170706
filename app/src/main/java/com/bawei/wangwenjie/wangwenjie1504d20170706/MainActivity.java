package com.bawei.wangwenjie.wangwenjie1504d20170706;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button1);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                 switch (event.getAction()) {
                 	case MotionEvent.ACTION_DOWN:
                        Toast.makeText(MainActivity.this,"你一按下按钮",Toast.LENGTH_SHORT).show();
                         break;
                     case MotionEvent.ACTION_UP:
                         Toast.makeText(MainActivity.this,"用户手以松开",Toast.LENGTH_SHORT).show();
                         break;

                 	default:
                 		break;
                 	}
                return false;
            }
        });
    }
}

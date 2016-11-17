package com.shabbirdhangot.nougatnotification;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class AndroidLowerNReply extends AppCompatActivity {

    PopupWindow  popupWindow;
    LinearLayout mainLayout;
    View         popUpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_back_ground_layout);

        mainLayout = new LinearLayout(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popUpView  = inflater.inflate(R.layout.activity_android_lower_nreply,null,false);

        popupWindow = new PopupWindow(popUpView,400,580,true);

        popupWindow.setContentView(popUpView);
        popupWindow.setOverlapAnchor(true);

        findViewById(R.id.popup_back_ground_layout_id).post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(mainLayout,Gravity.CENTER_HORIZONTAL,10,10);
            }
        });
    }

}

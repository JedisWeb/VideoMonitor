package cn.edu.hbuas.remotevideomonitoringsystem.fragment;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.dou361.dialogui.listener.DialogUIListener;
import com.suke.widget.SwitchButton;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import cn.edu.hbuas.remotevideomonitoringsystem.R;
import cn.edu.hbuas.remotevideomonitoringsystem.activity.MainActivity;

public class SettingFragment extends Fragment implements SwitchButton.OnCheckedChangeListener, View.OnTouchListener {

    private LinearLayout deviceName_linearLayout, storage_linearLayout, camera_linearLayout, horizontal_screen_linearlayout;
    private TextView deviceName_tv, deviceName_right_tv, storage_tv, camera_tv, horizontal_screen_tv;
    private SwitchButton horizontal_screen_btn;
    private Context context;
    private View view;
    private ImageView camera_iv, storage_iv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting_page, null);

        horizontal_screen_btn = view.findViewById(R.id.horizontal_screen_btn);

        deviceName_linearLayout = view.findViewById(R.id.deviceName_linearlayout);
        storage_linearLayout = view.findViewById(R.id.storage_linearLayout);
        camera_linearLayout = view.findViewById(R.id.camera_linearLayout);
        horizontal_screen_linearlayout = view.findViewById(R.id.horizontal_screen_linearlayout);

        deviceName_tv = view.findViewById(R.id.deviceName_tv);
        deviceName_right_tv = view.findViewById(R.id.deviceName_right_tv);
        storage_tv = view.findViewById(R.id.storage_tv);
        camera_tv = view.findViewById(R.id.camera_tv);
        horizontal_screen_tv = view.findViewById(R.id.horizontal_screen_tv);

        camera_iv = view.findViewById(R.id.camera_iv);
        storage_iv = view.findViewById(R.id.storage_iv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        horizontal_screen_btn.setOnCheckedChangeListener(this);

        deviceName_tv.setOnTouchListener(this);
        deviceName_right_tv.setOnTouchListener(this);
        storage_tv.setOnTouchListener(this);
        camera_tv.setOnTouchListener(this);
        horizontal_screen_tv.setOnTouchListener(this);

        camera_iv.setOnTouchListener(this);
        storage_iv.setOnTouchListener(this);
    }


    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.horizontal_screen_btn:
                VideoFragment.screenRotationToggle();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            //设备名称
            case R.id.storage_iv:
            case R.id.storage_tv: {
                v = storage_linearLayout;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundColor(getResources().getColor(R.color.transparent));
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent, 1);
                        break;
                }
                break;
            }
            //镜头
            case R.id.deviceName_right_tv:
            case R.id.deviceName_tv: {
                v = deviceName_linearLayout;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundColor(getResources().getColor(R.color.transparent));
                        DialogUIUtils.showAlert(getActivity(), "", "", "设备名称", "", "确定", "", false, true, true, new DialogUIListener() {
                            @Override
                            public void onPositive() {
//                                Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNegative() {
//                                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onGetInput(CharSequence input1, CharSequence input2) {
                                super.onGetInput(input1, input2);
                                deviceName_right_tv.setText(input1);
//                                Toast.makeText(context, "(" + input1 + "," + input2 + ")", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                        break;
                }
                break;
            }
            //存储
            case R.id.camera_tv:
            case R.id.camera_iv: {
                v = camera_linearLayout;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundColor(getResources().getColor(R.color.transparent));
                        Log.i("Information", "memory3 onclick");
                        List<TieBean> strings = new ArrayList<TieBean>();
                        strings.add(new TieBean("Video1"));
                        strings.add(new TieBean("Video2"));
                        strings.add(new TieBean("Video3"));
                        strings.add(new TieBean("Video4"));
                        DialogUIUtils.showSheet(context, strings, "取消", Gravity.BOTTOM, true, true, new DialogUIItemListener() {
                            @Override
                            public void onItemClick(CharSequence text, int position) {
                                Toast.makeText(context, "" + text, Toast.LENGTH_SHORT).show();
                                VideoFragment.loadWeb(text + "");
                            }

                            @Override
                            public void onBottomBtnClick() {
//                                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                        break;
                }
                break;
            }
            //  横屏/竖屏切换
            case R.id.horizontal_screen_tv: {
                v = horizontal_screen_linearlayout;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundColor(getResources().getColor(R.color.transparent));
                        horizontal_screen_btn.setChecked(!horizontal_screen_btn.isChecked());
                        break;
                }
            }
            break;
        }
        return false;
    }
}
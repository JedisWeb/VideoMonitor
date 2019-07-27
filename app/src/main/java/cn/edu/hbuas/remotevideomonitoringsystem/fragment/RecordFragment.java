package cn.edu.hbuas.remotevideomonitoringsystem.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.hbuas.remotevideomonitoringsystem.R;
import cn.edu.hbuas.remotevideomonitoringsystem.activity.MainActivity;
import cn.edu.hbuas.remotevideomonitoringsystem.bean.Car;
import cn.edu.hbuas.remotevideomonitoringsystem.database.DBService;

public class RecordFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Context context;

    private TableLayout tableLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Information", "onCreate()");
        context = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Information", "onCreateView()");
        view = inflater.inflate(R.layout.fragment_record_page, null);
        tableLayout = view.findViewById(R.id.table_layout);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Information", "onActivityCreated()");
        tableLayout.setOnClickListener(this);
        initTable();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tableLayout.addView((View) msg.obj);
        }
    };

    public void initTable() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                DBService service = DBService.getDbService();
                List<Car> list = service.getUserData();
                Log.i("Information:", "list.size:" + list.size());
                int row = 0;
                for (Car c : list) {
                    ++row;
                    String[] arr = new String[]{c.getPlate_number(), c.getName(), c.getMessage(), c.getCreateTime()};
                    TableRow tableRow = new TableRow(context);
                    for (int i = 0; i < 4; i++) {
                        TextView textView = new TextView(context);
                        textView.setText(arr[i]);
                        if (row % 2 == 0) {
                            textView.setBackgroundColor(getResources().getColor(R.color.table_row));
                        } else {
                            textView.setBackgroundColor(getResources().getColor(R.color.white));
                        }
                        textView.setTextSize(20);
                        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(0, 0, 2, 2);
                        tableRow.addView(textView, layoutParams);
                    }
                    Message msg = new Message();
                    msg.obj = tableRow;
                    handler.sendMessage(msg);
                }
                handler.postDelayed(this, 3000);
            }
        }).start();
//        thread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.table_layout:
                MainActivity.hideBottomNavigation();
                break;
        }
    }
}

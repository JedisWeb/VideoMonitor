package cn.edu.hbuas.remotevideomonitoringsystem.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import cn.edu.hbuas.remotevideomonitoringsystem.R;
import cn.edu.hbuas.remotevideomonitoringsystem.bean.Constant;
import cn.edu.hbuas.remotevideomonitoringsystem.socketio.ChatApplication;

public class VideoFragment extends Fragment {

//    private static final int MSG_SUCCESS = 1;// 获取图片成功的标识
//    private static final int MSG_FAILURE = 0;// 获取图片失败的标识

    private static WebView webView;
//    private static ImageView videoImg;
    private View view;
    private Context context;
//    private Boolean isConnected = true;
//    private DisplayMetrics dm = new DisplayMetrics();
//    private int screenWidth;
//    private int screenHeight;

//    private Socket socket;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SocketSocket", "onCreate");
        context = getActivity();
//        ChatApplication app = (ChatApplication) getActivity().getApplication();
//        socket = app.getSocket();
//        socket.on(Socket.EVENT_CONNECT, onConnect);
//        socket.on(Socket.EVENT_DISCONNECT, onDisconnect);
//        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
//        socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
//        socket.on("c1", getData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("SocketSocket", "onCreateView");
        view = inflater.inflate(R.layout.fragment_video_page, null);
//        videoImg = view.findViewById(R.id.video_imageview);
        webView = view.findViewById(R.id.wv_webview);
        return webView;
    }

    public static void loadWeb(String camera) {
//        http://192.168.43.163:8080/video/video1.html
//        http://192.168.43.91:3000/camera01.html
        String url = "http://" + Constant.URL + ":" + Constant.PORT + "/";
        switch (camera) {
            case "Video1":
                url += "camera01.html";
                break;
            case "Video2":
                url += "camera02.html";
                break;
            case "Video3":
                url += "camera03.html";
                break;
            case "Video4":
                url += "camera04.html";
                break;
            default:
                url += "camera01.html";
        }
        webView.getSettings().setJavaScriptEnabled(true);//开启JavaScript支持
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("SocketSocket", "onActivityCreated");
//        loadWeb("Video1");
//        MyThread myThread = new MyThread();
//        Thread thread = new Thread(myThread);
//        thread.start();
    }

    public static void screenRotationToggle() {
        if (webView.getRotation() == 0) {
            webView.animate().rotation(90);
        } else {
            webView.animate().rotation(0);
        }
    }

//    public int getScreenWidth() {
//        screenWidth = dm.widthPixels;
//        return screenWidth;
//    }
//
//    public int getScreenHeight() {
//        screenHeight = dm.heightPixels;
//        return screenHeight;
//    }
//
//    public int px2dip(float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (pxValue / scale + 0.5f);
//    }

//    private Emitter.Listener onConnect = new Emitter.Listener() {
//        @Override
//        public void call(Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.i("SocketSocket:", "Connected");
//                    isConnected = true;
//                }
//            });
//        }
//    };
//
//    private Emitter.Listener onDisconnect = new Emitter.Listener() {
//        @Override
//        public void call(Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.i("SocketSocket:", "Disconnected, Please check your internet connection");
//                    isConnected = false;
//                }
//            });
//        }
//    };
//
//    private Emitter.Listener onConnectError = new Emitter.Listener() {
//        @Override
//        public void call(Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.i("SocketSocket:", "Failed to connect");
//                }
//            });
//        }
//    };

//    private Emitter.Listener getData = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.i("SocketSocket:", "getData");
//                    Log.i("SocketSocket:", "getData：" + args.toString());
//                    JSONObject data = (JSONObject) args[0];
//                    String username;
//                    String message;
//                    try {
//                        username = data.getString("username");
//                        message = data.getString("message");
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.getMessage());
//                        return;
//                    }
//
//                    removeTyping(username);
//                    addMessage(username, message);
//                }
//            });
//        }
//    };
//}

//class MyThread implements Runnable {
//    @Override
//    public void run() {
//        HttpURLConnection connection = null;
//        URL url = null;
//        try {
//            url = new URL(Constant.URL);
//
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setConnectTimeout(8000);
//            connection.setReadTimeout(8000);
////            connection.setRequestProperty("Content-Type", "application/json");
////
////            OutputStream outputStream = connection.getOutputStream();
////            BufferedWriter requestBody = new BufferedWriter(new OutputStreamWriter(outputStream));
////
////            requestBody.write(jsonBody);
////
////            requestBody.flush();
////
////            requestBody.close();
//
//            InputStream inputStream = connection.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuffer response = new StringBuffer();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
////            listener.onError(e);
//        }
//    }


    //用handler通知UI线程显示图片
    // 当有消息发送出来的时候就执行Handler的这个方法
    // 重写handleMessage()方法，此方法在UI线程运行
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                // 如果成功，则显示从网络获取到的图片
//                case MSG_SUCCESS:
//                    videoImg.setImageBitmap((Bitmap) msg.obj);
//                    Log.i("SocketSocket", "MSG_SUCCESS");
//                    break;
//                // 否则提示失败
//                case MSG_FAILURE:
//                    Log.i("SocketSocket", "MSG_FAILURE");
//                    break;
//            }
//        }
//    };
//
//    class MyThread implements Runnable {
//        @Override
//        public void run() {
//            try {
//                Socket socket = null;
//                DataInputStream dataInputStream = null;
//                socket = new Socket();
//                socket.connect(new InetSocketAddress(Constant.URL, Constant.PORT), 10 * 1000);
//                Log.i("SocketSocket", "" + socket.isConnected());
//                int i = 0;
//                int size;
//                while (true) {
//                    dataInputStream = new DataInputStream(socket.getInputStream());
//                    size = dataInputStream.readInt();
//                    Log.i("SocketSocket", "" + size);
//                    int length = 0;
//                    //begin 图片接收
//                    byte[] data = new byte[size];
//                    Log.i("SocketSocket", "开始接收");
//                    while (length < size) {
//                        length += dataInputStream.read(data, length, size - length);
//                        Log.i("SocketSocket:", "length:" + length);
//                    }
//                    Log.i("SocketSocket:", "开始解码");
//                    //开始图片解码
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, size);
//                    Log.i("SocketSocket", "BitMap" + i);
//                    Message message = handler.obtainMessage(MSG_SUCCESS, bitmap);
//                    handler.sendMessage(message);
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

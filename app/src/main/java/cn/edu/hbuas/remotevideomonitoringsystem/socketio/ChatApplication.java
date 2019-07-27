package cn.edu.hbuas.remotevideomonitoringsystem.socketio;

import android.app.Application;

import java.net.URISyntaxException;

import cn.edu.hbuas.remotevideomonitoringsystem.bean.Constant;
import io.socket.client.IO;
import io.socket.client.Socket;

public class ChatApplication extends Application {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(Constant.URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}

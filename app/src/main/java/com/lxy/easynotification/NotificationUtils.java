package com.lxy.easynotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationUtils {

    private static NotificationUtils instance;

    private NotificationUtils(){
    }

    public static NotificationUtils getInstance(){
        if (instance == null) {
            synchronized (NotificationUtils.class) {
                if (instance == null){
                    instance = new NotificationUtils();
                }
            }
        }
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification(Builder builder){
        NotificationManager manager = (NotificationManager) builder.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        // 创建NotificationChannel
        int importance = builder.importance == -1 ? NotificationManager.IMPORTANCE_DEFAULT : builder.importance;
        NotificationChannel channel = new NotificationChannel(builder.channelId,builder.channelName,importance);
        Notification.Builder b = new Notification.Builder(builder.mContext,builder.channelId);
        b.setContentText(builder.content)
                .setContentTitle(builder.title)
                .setSmallIcon(builder.smallIconId)
                .setLargeIcon(builder.largeIcon)
                .setWhen(builder.when == 0 ? System.currentTimeMillis() : builder.when);
        manager.createNotificationChannel(channel);
        manager.notify(builder.notifyId,b.build());
    }

    public class Builder{
        protected AppCompatActivity mContext;
        private String title;
        private String content;
        private int smallIconId;
        private Bitmap largeIcon;
        private int soundsId;
        private String channelName;
        private String channelId;
        private long when;
        // 通知的重要程度
        private int importance = -1;

        // 通知ID
        private int notifyId;


        public Builder setContext(AppCompatActivity mContext) {
            this.mContext = mContext;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setSmallIconId(int smallIconId) {
            this.smallIconId = smallIconId;
            return this;
        }

        public Builder setSoundsId(int soundsId) {
            this.soundsId = soundsId;
            return this;
        }

        public Builder setChannelName(String channelName) {
            this.channelName = channelName;
            return this;
        }

        public Builder setChannelId(String channelId) {
            this.channelId = channelId;
            return this;
        }

        public Builder setWhen(long when) {
            this.when = when;
            return this;
        }

        public Builder setImportant(int importance){
            this.importance = importance;
            return this;
        }

        public Builder setLagerIcon(Bitmap bitmap){
            this.largeIcon = bitmap;
            return this;
        }

        public Builder setNotifyId(int notifyId){
            this.notifyId = notifyId;
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void show(){
            showNotification(this);
        }
    }

}

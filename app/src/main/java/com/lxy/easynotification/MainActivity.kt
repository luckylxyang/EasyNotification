package com.lxy.easynotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                showNotification()
            };
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification() {
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "notification_channel"
        val channel by lazy {
            NotificationChannel(channelId,"name",NotificationManager.IMPORTANCE_HIGH)
        }
        val build = Notification.Builder(this, channelId)
        build.setContentTitle("contentTitle").setSmallIcon(R.mipmap.ic_launcher)
        service.createNotificationChannel(channel)
        service.notify(3,build.build())
    }
}

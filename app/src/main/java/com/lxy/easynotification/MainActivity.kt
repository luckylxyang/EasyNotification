package com.lxy.easynotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var index = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                showNotification()
            };
        }
        button2.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                index = index + 1
                showNotificationUtil(index)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification() {
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "notification_channel"
        val channel by lazy {
            NotificationChannel(channelId,"name",NotificationManager.IMPORTANCE_DEFAULT)
        }
        val build = Notification.Builder(this, channelId)
        build.setContentTitle("contentTitle")
            .setContentText("contentText")
            .setWhen(System.currentTimeMillis())  //设置时间
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(resources.getDrawable(R.mipmap.ic_launcher).toBitmap())
        val pendingIntent = PendingIntent.getActivity(this,5, Intent(this,SecondActivity::class.java),0)
        build.setContentIntent(pendingIntent)
        service.createNotificationChannel(channel)
        service.notify(3,build.build())
    }

    /**
     * 设置channelId channel渠道名
     * 设置 通知等级
     * 设置 title,content,icon,sound,是否震动，图标
     * 设置 合并通知，取消通知，跳转页面
     *
     *
     * 设置的 notifyId 相同，后到的通知会覆盖前面的通知
     * notifyId 不同，两个不同的通知
     * 通知数4个会自动合并显示
     *
     */

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotificationUtil(notifyId : Int){
        NotificationUtils.getInstance().Builder()
            .setContext(this)
            .setChannelId("notification_channel$notifyId")
            .setChannelName("name")
            .setContent("utils content")
            .setTitle("utils title")
            .setLagerIcon(resources.getDrawable(R.mipmap.ic_launcher).toBitmap())
            .setSmallIconId(R.mipmap.ic_launcher)
            .setNotifyId(notifyId)
            .show()


    }
}

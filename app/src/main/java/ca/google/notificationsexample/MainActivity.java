package ca.google.notificationsexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNotification;

    // --- NOTIFICATION VARIABLES ---
    NotificationCompat.Builder notification;
    private static final int uniqueNotificationID = 112233; // Unique ID for the notification we're making

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //In this example, the notification is triggered by pressing a button
        btnNotification = findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btnNotification) {

                    // --- BUILDING THE NOTIFICATION ---

                    //Sets the icon (located in app/src/main/res/drawable)
                    notification.setSmallIcon(R.drawable.quill);

                    //Setting the content of the notification
                    notification.setTicker("Re:Driver - The quest have been updated!");   //For use with Android versions before Lollipop/5.0
                    notification.setContentTitle("Quest Updated!");
                    notification.setContentText("'Websec, Protector of Sites' has been slain! Collect your rewards!");
                    notification.setWhen(System.currentTimeMillis());                     //Time which shows in the notification
                    notification.setDefaults(Notification.DEFAULT_ALL);                   //Default sound, vibrate pattern, etc.
                    notification.setPriority(NotificationCompat.PRIORITY_HIGH);           //PRIORITY_HIGH pops up a message, vibrates, sound, etc. LOW doesn't pop up a message

                    //The intent to go to when clicking the notification
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);

                    //Allows intent to be accessed from wherever you are in your phone
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    notification.setContentIntent(pendingIntent);

                    //Building the notification
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    //Issuing the notification (i.e. actually making it appear)
                    notificationManager.notify(uniqueNotificationID, notification.build());
                }
            }
        });

        // --- CREATING THE NOTIFICATION ---

        //The .builder class with 1 argument is deprecated (pre-Oreo); must use the 2 argument one
        notification = new NotificationCompat.Builder(this, "notificationTest");
        //Notification will disappear when tapped
        notification.setAutoCancel(true);

    }



}

package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class SubbedCourseTopics extends AppCompatActivity {
    TextView heading;
    ArrayList<String> StringNames = new ArrayList<String>();
    ListView myListTopics;
    ListView Notifications;
    private DatabaseReference coursesRef, getNotifRef;
    ArrayList<String> n = new ArrayList<String>();
    public static  String topicsClicked,topicNameClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_topics);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ArrayAdapter<String> myArray_topic_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, StringNames);

        myListTopics = findViewById(R.id.topicView);
        heading = findViewById(R.id.txtHeading);
        String name1 = StudentSubscriptionsFragment.courseNameClicked;
        heading.setText(name1);
        System.out.println(name1);
        coursesRef= FirebaseDatabase.getInstance().getReference("Topics/" + name1);
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<String> topicIDs = new ArrayList<>();
                ArrayList<String> topicNames = new ArrayList<>();
                for(DataSnapshot child:snapshot.getChildren()){
                    String id = child.getKey();
                    String name = child.getValue().toString();
                    StringNames.add(name);

                    topicIDs.add(id);
                    topicNames.add(name);
                }
                myArray_topic_Adapter.notifyDataSetChanged();
                myListTopics.setAdapter(myArray_topic_Adapter);

                myListTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        topicsClicked = topicIDs.get(i);
                        topicNameClicked = topicNames.get(i);

                        Bundle extras = new Bundle();
                        extras.putString("courseName", name1);
                        extras.putString("topic", topicNameClicked);

                        Intent intent = new Intent(getApplicationContext(), StudentTopicDashboard.class);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Notifications = findViewById(R.id.NotificationsView);

        //displaying all notifications
        //LecturerNotifications.MyAdapter adapter1 = new LecturerNotifications.MyAdapter(this, n);
        SubbedCourseTopics.MyAdapter adapter1 = new SubbedCourseTopics.MyAdapter(this, n);
        getNotifRef = FirebaseDatabase.getInstance().getReference("Notifications/" + name1);
        getNotifRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                n.clear();
                for(DataSnapshot child:snapshot.getChildren()){
                    String h = child.child("heading").getValue().toString();
                    String m = child.child("message").getValue().toString();


                    String notification = h + "\n" + m;
                    n.add(notification);
                }
                //Notifications.setAdapter(adapter1);
                Notifications.setAdapter(adapter1);
                //myArray_topic_Adapter.notifyDataSetChanged();
                //Notifications.setAdapter(myArray_topic_Adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> displayN = new ArrayList<String>();

        public MyAdapter(@NonNull Context context, ArrayList displayN) {
            super(context, R.layout.notification_row, R.id.notif_heading,displayN);
            this.context=context;
            this.displayN = displayN;
        }

        @NotNull
        @Override
        public View getView (int position,
                             @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.notification_row, parent, false) ;

            TextView heading = row.findViewById(R.id.notif_heading);
            TextView message = row.findViewById(R.id.notif_message);

            String dCourse = displayN.get(position);
            String[] splitDC = dCourse.split("\n");

            String courseName = splitDC[0];
            String lecName = splitDC[1];

            heading.setText(courseName);
            message.setText(lecName);

            return row;
        }

    }
}

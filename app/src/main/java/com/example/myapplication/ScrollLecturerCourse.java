package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ScrollLecturerCourse extends AppCompatActivity {
    TextView CourseName;
    Button addTopicBtn, notifBtn;
    EditText topic;
    int count = -1;
    ArrayList<String> StringNames = new ArrayList<String>();
    ListView myListTopics;
    private DatabaseReference coursesRef;
    public static  String topicsClicked,topicNameClicked;
    ListView Notifications;
    EditText subject, message;
    ArrayList<String> n = new ArrayList<String>();

    private DatabaseReference topicsRef, databaseReference;
    private DatabaseReference ref, notifRef, courseRef, getNotifRef;
    TopicData tData;
    TextView viewTopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_lecturer_course);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        String name = LecturerCoursesFragment.courseNameClicked;
        CourseName = findViewById(R.id.SVcourseName);

        topicsRef = FirebaseDatabase.getInstance().getReference("Topics/" + name + "/");
        CourseName.setText(name);

        addTopicBtn = findViewById(R.id.AddTopicBtn);

        addTopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Alert Dialog init
                AlertDialog.Builder topic_dialog = new AlertDialog.Builder(view.getContext());
                // Title of Alert Dialog
                topic_dialog.setTitle("New Topic for " + name);
                // Message of Dialog
                topic_dialog.setMessage("Create a new topic by filling out the field below and clicking 'create'.");

                topic = new EditText(view.getContext());

                topic.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                topic.setHint("Enter New Topic Here");
                LinearLayout parent = new LinearLayout(view.getContext());
                parent.setGravity(Gravity.CENTER);
                parent.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                parent.setGravity(Gravity.CENTER);
                parent.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                parent.addView(topic);
                topic_dialog.setView(parent);

                // creating yes and no buttons for pop up
                topic_dialog.setPositiveButton("Create Topic", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UploadtoFirebase();
                    }
                });

                topic_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Close pop up and go back to subscriptions page
                    }
                });

                topic_dialog.create().show(); // Show the pop up
            }
        });

        ArrayAdapter<String> myArray_topic_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, StringNames);
        //test = findViewById(R.id.textView2);
        myListTopics = findViewById(R.id.topicView);
        coursesRef= FirebaseDatabase.getInstance().getReference("Topics/" + name);
        coursesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringNames.clear();
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

                        startActivity(new Intent(getApplicationContext(), LecturerResourceView.class));
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Notifications = findViewById(R.id.notifications);

        notifBtn = findViewById(R.id.btnNotifications);

        // setting on Click Listener
        notifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert Dialog init
                AlertDialog.Builder notif_dialog = new AlertDialog.Builder(view.getContext());
                // Title of Alert Dialog
                notif_dialog.setTitle("New Notification for " + name);
                // Message of Dialog
                notif_dialog.setMessage("Add a notification heading and message in the relevant text boxes below:");

                subject = new EditText(view.getContext());
                message = new EditText(view.getContext());

                subject.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                message.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                subject.setHint("Notification Subject");
                message.setHint("Notification Message");
                LinearLayout parent = new LinearLayout(view.getContext());
                parent.setGravity(Gravity.CENTER);
                parent.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                parent.setGravity(Gravity.CENTER);
                parent.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                parent.addView(subject);
                parent.addView(message);

                notif_dialog.setView(parent);

                // creating yes and no buttons for pop up
                notif_dialog.setPositiveButton("Notify Students", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String username = "witsacademynotifications@yahoo.com";
                        final String password = "otjxoxxodmeqgjlm";

                        String heading = subject.getText().toString();
                        String body = message.getText().toString();

                        Notification notif = new Notification(heading,body);

                        notifRef = FirebaseDatabase.getInstance().getReference("Notifications/" + name + "/");

                        courseRef = FirebaseDatabase.getInstance().getReference("courses/" + name);
                        courseRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                // below line is for checking weather the
                                // edittext fields are empty or not.
                                if (TextUtils.isEmpty(heading)||TextUtils.isEmpty(body)) {
                                    // if the text fields are empty
                                    // then show the below message.
                                    Toast.makeText(ScrollLecturerCourse.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    notifRef.child(databaseReference.push().getKey()).setValue(notif);
                                    // after adding this data we are showing toast message.
                                    Toast.makeText(ScrollLecturerCourse.this, "data added", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(ScrollLecturerCourse.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                            }
                        });

                        Properties props = new Properties();

                        props.put("mail.smtp.auth","true");
                        props.put("mail.smtp.ssl.enable","true");
                        props.put("mail.smtp.host","smtp.mail.yahoo.com");
                        props.put("mail.smtp.port","465");

                        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication(){
                                System.out.println("true");
                                return new PasswordAuthentication(username, password);
                            }
                        });

                        databaseReference = FirebaseDatabase.getInstance().getReference("Subscribers/" + name);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                ArrayList<String> email = new ArrayList<>();
                                for(DataSnapshot child:snapshot.getChildren()){
                                    email.add(child.getValue().toString());
                                }

                                System.out.println(email);

                                if(email.isEmpty()){
                                    Toast.makeText(getApplicationContext(),"No Subscribers to Notify as yet", Toast.LENGTH_LONG).show();
                                }else{
                                    try{
                                        InternetAddress[] addresses = new  InternetAddress[email.size()];
                                        for (int i = 0; i < email.size(); i++) {
                                            addresses[i] = new InternetAddress(email.get(i));
                                        }
                                        MimeMessage message = new MimeMessage(session);
                                        message.setFrom(new InternetAddress(username));
                                        //message.addRecipient(Message.RecipientType.TO, new InternetAddress("mzaidally0512@gmail.com"));
                                        message.addRecipients(Message.RecipientType.TO, addresses);
                                        message.setSubject("NOTIFICATION FOR " + name + ": " + heading);
                                        message.setText(body);
                                        Thread thread = new Thread(new Runnable() {
                                            @Override
                                            public void run(){
                                                try{
                                                    Transport.send(message);
                                                    System.out.println(message);
                                                } catch (MessagingException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        thread.start();

                                        Toast.makeText(getApplicationContext(),"Notification email sent succesfully", Toast.LENGTH_LONG).show();
                                    }catch (MessagingException e){
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

                notif_dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Close pop up and go back to subscriptions page
                    }
                });

                notif_dialog.create().show(); // Show the pop up
            }
        });

        //displaying all notifications
        //LecturerNotifications.MyAdapter adapter1 = new LecturerNotifications.MyAdapter(this, n);
        ScrollLecturerCourse.MyAdapter adapter1 = new ScrollLecturerCourse.MyAdapter(this, n);
        getNotifRef = FirebaseDatabase.getInstance().getReference("Notifications/" + name);
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

    private void UploadtoFirebase(){
        String t_one = topic.getText().toString();

        databaseReference = FirebaseDatabase.getInstance().getReference("courses/");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(t_one)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(ScrollLecturerCourse.this, "Please add a topic", Toast.LENGTH_SHORT).show();
                }

                else {
                    topicsRef.child(databaseReference.push().getKey()).setValue(t_one);
                    topic.setText("");
                    // after adding this data we are showing toast message.
                    Toast.makeText(ScrollLecturerCourse.this, "data added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ScrollLecturerCourse.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
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
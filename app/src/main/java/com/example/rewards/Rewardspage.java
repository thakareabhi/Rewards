package com.example.rewards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rewardspage extends AppCompatActivity {
    TextView t1,t2;
    private DatabaseReference mDatabase;
    FirebaseDatabase firebase;
    //Firebase rewardref;
    String crcnt,odno;
    int cred,flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewardspage);
       // Firebase.setAndroidContext(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebase=FirebaseDatabase.getInstance();

        t1=(TextView) findViewById(R.id.textView5);
        t2=(TextView) findViewById(R.id.textView6);
        crcnt="ehyfegfu";

        final String s1=Transfertext.emailidtransfer;
        t1.setText(s1);
        t2.setText(crcnt);

        final String s2 = s1.substring(0, s1.length() - 10);



        DatabaseReference data = firebase.getReference("USERS");
        DatabaseReference mRef = data.child(s2);


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("kuh","in data3");
                crcnt = dataSnapshot.child("Credit").getValue().toString();

                odno=dataSnapshot.child("Ordercount").getValue().toString();
                Log.d("vifhvfi","in data4");
                int a=Integer.parseInt(odno);

                if(a==1)
                {
                    Log.d("fdvdf","in data5");
                    final String cpnused=dataSnapshot.child("RefCodeUsed").getValue().toString();

                    if(cpnused!="")
                    {
                        DatabaseReference datam = firebase.getReference("USERS");
                        final DatabaseReference datax=datam.child(cpnused);
                           /*rewardref =new Firebase("https://rewards-13f5f.firebaseio.com/USERS/");

                           rewardref.addChildEventListener(new ChildEventListener() {
                               @Override
                               public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                                   String ban=dataSnapshot.child(cpnused).child("Credit").getValue().toString();
                                   int cred=Integer.parseInt(ban);
                                   cred=cred+10;
                                   String credstr=Integer.toString(cred);
                                   rewardref.child(cpnused).child("Credit").setValue(credstr);


                               }

                               @Override
                               public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                               }

                               @Override
                               public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

                               }

                               @Override
                               public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                               }

                               @Override
                               public void onCancelled(FirebaseError firebaseError) {

                               }
                           });*/

                        datax.addValueEventListener(new ValueEventListener() {
                            @Override

                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                Log.d("dk","in data");
                                String crcntx = dataSnapshot1.child("Credit").getValue().toString();
                                Log.d("dk","in data "+crcntx);
                                int cred1=Integer.parseInt(crcntx);
                                Log.d("dvs","in data "+cred1);

                                if(flag==0)
                                {
                                    cred=cred1+10;
                                    flag=1;
                                    String upcrd=Integer.toString(cred);
                                    datax.child("Credit").setValue(upcrd);

                                }



                                Log.d("co","in data "+cred);



                                Log.d("co11","in data "+cred);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError1) { }
                        });


                    }

                }

                t2.setText(crcnt);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("scj1","dhbdc5");
                Log.w("Problem", "Failed to read value.", error.toException());
            }
        });

        Log.d("scj1","dhbdc6");



    }
}

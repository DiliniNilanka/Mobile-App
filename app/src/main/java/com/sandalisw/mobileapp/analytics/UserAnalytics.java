package com.sandalisw.mobileapp.analytics;

import android.support.annotation.NonNull;
import android.support.v4.media.MediaMetadataCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.sandalisw.mobileapp.analytics.models.UserHistry;
import com.sandalisw.mobileapp.analytics.models.UserSongPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserAnalytics {

    private static ArrayList<UserSongPreference> lst = new ArrayList<>();

    public static void userHistry(MediaMetadataCompat mediaItem){

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        final CollectionReference userref = db.collection("UserHistry");
        int user_id = 1;
        int song_id = Integer.parseInt(mediaItem.getDescription().getMediaId());
        Long timeStamp = System.currentTimeMillis()/1000;

        UserHistry userHistry = new UserHistry(user_id,song_id,timeStamp);

        userref.add(userHistry);

    }

    public static void userSongPreferences(MediaMetadataCompat mediaIte){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        final CollectionReference songpreferencesref = db.collection("UserSongPreferences");
        int user_id = 1;
        int song_id = Integer.parseInt(mediaIte.getDescription().getMediaId());
        int count = 0;//getCount(user_id,song_id);

        if(count == 0){
            count = count +1;
            UserSongPreference userSongPreference = new UserSongPreference(user_id,song_id,count);
            songpreferencesref.add(userSongPreference);
        }
        else if(count >= 1){
            songpreferencesref.whereEqualTo("userid",user_id).whereEqualTo("songid",song_id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot document: task.getResult()){
                            UserSongPreference userpref = document.toObject(UserSongPreference.class);
                            Map<Object, Integer> map = new HashMap<>();
                            map.put("count",userpref.getCount()+1);
                            songpreferencesref.document(document.getId()).set(map, SetOptions.merge());
                        }
                    }
                }
            });
        }


    }

    public static int getCount(int user_id, int song_id){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        final CollectionReference ref = db.collection("UserSongPreferences");

        Query query = ref
                .whereEqualTo("userid",user_id)
                .whereEqualTo("songid",song_id);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document: task.getResult()){
                        UserSongPreference ref = document.toObject(UserSongPreference.class);
                        lst.add(ref);
                    }
                }
            }
        });

        if(lst.isEmpty()){
            return 0;
        }
        else{
            return lst.get(0).getCount();
        }
    }
}

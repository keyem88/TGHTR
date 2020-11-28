package com.meisterk.tghtr;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Songtext {

    final private String collectionPath = "songtexts";
    final String title;
    final FirebaseUser creator;
    FirebaseFirestore db;
    final UUID id;
    final String tag = getClass().getName();
    boolean saved;

    private Map<String,String> components;

    Songtext(String title, FirebaseUser creator){
        this.id = UUID.randomUUID();
        this.title = title;
        this.creator = creator;
        this.saved = false;
        components = new HashMap<>();
        db = FirebaseFirestore.getInstance();
    }

    public String getTitle() {
        return title;
    }

    public FirebaseUser getCreator() {
        return creator;
    }

    public Map<String, String> getComponents() {
        return components;
    }

    public void addComponent(String text){
        if(components.isEmpty()){
            components.put("0",text);
            Log.d(tag, "new Compontent added");
        }else{
            components.put(Integer.toString(components.size()),text);
            Log.d(tag, "Component added with index: " + components.size());
        }
    }

    @Override
    public String toString() {
        return "Songtext{" +
                "title='" + title + '\'' +
                ", creator=" + creator.getUid() +
                ", id=" + id +
                ", components=" + components +
                '}';
    }


    public void save(){
        Map<String, Object> toSave = new HashMap<>();
        Log.d("Songtext", "new Hashmap");
        toSave.put("title", this.getTitle());

        Log.d("Songtext", "title" + this.getTitle());
        toSave.put("creator", this.getCreator().getUid());
        Log.d("Songtext", "creator" + this.getCreator());

        toSave.put("components", this.getComponents());
        Log.d("Songtext", "components" + this.getComponents());


        db.collection(collectionPath).document(this.id.toString()).set(toSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(tag, "Songtext successfully saved");
            }
        });
    }


}

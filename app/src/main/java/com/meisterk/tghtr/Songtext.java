package com.meisterk.tghtr;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Songtext {

    final String collectionPath = "songtexts";

    final String title;
    FirebaseUser creator;
    final boolean approved;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();;
    final String id;
    final String tag = getClass().getName();
    boolean saved;

    final private Map<String,String> components;

    Songtext(String title, FirebaseUser creator){
        this.id = "";
        this.title = title;
        this.creator = creator;
        this.saved = false;
        this.approved = false;
        components = new HashMap<>();
        db = FirebaseFirestore.getInstance();
    }

    Songtext(String id){
        DocumentReference docRef = db.collection(collectionPath).document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isComplete()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){

                    }
                }
            }
        });
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

        Date now = new Date();

        //Map, welche die Daten zum Speichern aufnimmt
        Map<String, Object> toSave = new HashMap<>();
        Log.d("Songtext", "new Hashmap");

        //Titel des Songtexts
        toSave.put("title", this.getTitle());

        Log.d("Songtext", "title" + this.getTitle());

        //Firebase-ID des Erstellers
        toSave.put("creator", this.getCreator().getUid());
        Log.d("Songtext", "creator" + this.getCreator());

        //Array mit den Komponenten
        toSave.put("components", this.getComponents());
        Log.d("Songtext", "components" + this.getComponents());

        //aktuelles Datum und Uhrzeit der Erstellung
        toSave.put("creationDate", now);

        //beim Speichern ist der Songtext noch nicht freigegeben
        toSave.put("approved", this.approved);

        //Map in Firebase speichern

        db.collection(collectionPath).document(this.id.toString()).set(toSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(tag, "Songtext successfully saved");
            }
        });
    }


}

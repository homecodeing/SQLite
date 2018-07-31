package ithome.am.ithomenotsss.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ithome.am.ithomenotsss.Engine.Engine;
import ithome.am.ithomenotsss.Engine.Model;
import ithome.am.ithomenotsss.R;

public class AddActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private EditText mOccupationEditText;
    private EditText mImageEditText;
    private Button mAddBtn;

    private Engine dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mNameEditText = (EditText)findViewById(R.id.userName);
        mOccupationEditText = (EditText)findViewById(R.id.userOccupation);
        mImageEditText = (EditText)findViewById(R.id.userProfileImageLink);
        mAddBtn = (Button)findViewById(R.id.addNewUserButton);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               savePerson();

            }
        });

    }
    private void savePerson(){
        String name = mNameEditText.getText().toString().trim();
        String occupation = mOccupationEditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();
        //dbHelper = new PersonDBHelper(this);
        dbHelper=Engine.getInstance();

        if(name.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }
        if(occupation.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an occupation", Toast.LENGTH_SHORT).show();
        }

        if(image.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an image link", Toast.LENGTH_SHORT).show();
        }

        //create new person
        Model person = new Model(name, occupation, image);
       // dbHelper.saveNewPerson(person);
        dbHelper.getDbServices(this).save(person);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }
    private void goBackHome(){
        startActivity(new Intent(AddActivity.this, MainActivity.class));
    }
}

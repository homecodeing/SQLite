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

public class ProfileActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private EditText mAgeEditText;
    private EditText mOccupationEditText;
    private EditText mImageEditText;
    private Button mUpdateBtn;

    private Engine dbHelper;
    private long receivedPersonId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //init
        mNameEditText = (EditText)findViewById(R.id.userNameUpdate);
        mOccupationEditText = (EditText)findViewById(R.id.userOccupationUpdate);
        mImageEditText = (EditText)findViewById(R.id.userProfileImageLinkUpdate);
        mUpdateBtn = (Button)findViewById(R.id.updateUserButton);

        dbHelper=Engine.getInstance();
        try {
            receivedPersonId=getIntent().getLongExtra("USER_ID",1);

        }catch (Exception e){
            e.printStackTrace();
        }

        Model model=dbHelper.getDbServices(this).getData(receivedPersonId);
        mNameEditText.setText(model.getName());
        mOccupationEditText.setText(model.getOccupation());
        mImageEditText.setText(model.getImage());
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateModel();
            }
        });


    }

    private void updateModel() {
        String name = mNameEditText.getText().toString().trim();
        String occupation = mOccupationEditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();


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

        Model Update_Model=new Model(name,occupation,image);
        dbHelper.getDbServices(this).UpdateModel(receivedPersonId,this,Update_Model);
        goBackHome();
    }
    private void goBackHome(){
        startActivity(new Intent(this, MainActivity.class));
    }
}

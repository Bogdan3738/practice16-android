package ru.practice.users;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends Activity {
    private User activeUser;
    private EditText nameTextView;
    private EditText stateTextView;
    private EditText ageTextView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        new UserStaticInfo();
        int position = getIntent().getIntExtra(UserStaticInfo.POSITION_KEY, -1);
        if (position < 0 || position >= UserStaticInfo.users.size()) {
            finish();
            return;
        }
        activeUser = UserStaticInfo.users.get(position);
        Init();
        if (savedInstanceState == null) setUserInfo();
    }
    private void Init() {
        nameTextView = findViewById(R.id.NameTextView);
        stateTextView = findViewById(R.id.StateTextView);
        ageTextView = findViewById(R.id.AgeTextView);
    }
    private void setUserInfo() {
        nameTextView.setText(activeUser.getName());
        stateTextView.setText(activeUser.getState());
        ageTextView.setText(Integer.toString(activeUser.getAge()));
    }
    public void Back(View view) { finish(); }

    public void Save(View view) {
        activeUser.setName(nameTextView.getText().toString());
        activeUser.setState(stateTextView.getText().toString());
        String age = ageTextView.getText().toString();
        int previousAge = activeUser.getAge();
        int parsedAge = Transform.parseIntOrDefault(age, previousAge);
        activeUser.setAge(parsedAge);
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
        // MainActivity обновит список и карточку в onResume после finish().
        finish();
    }
}

package ru.practice.users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private View userPanel;
    private TextView nameTextView;
    private TextView stateTextView;
    private TextView ageTextView;
    private UserListAdapter userListAdapter;
    private int positionActiveUser = -1;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new UserStaticInfo();
        Init();
        if (savedInstanceState != null) {
            positionActiveUser = savedInstanceState.getInt("selected", -1);
            if (positionActiveUser >= 0) InitPanel(UserStaticInfo.users.get(positionActiveUser));
        }
    }
    private void Init() {
        userPanel = findViewById(R.id.userPanel);
        nameTextView = userPanel.findViewById(R.id.NameTextView);
        stateTextView = userPanel.findViewById(R.id.StateTextView);
        ageTextView = userPanel.findViewById(R.id.AgeTextView);
        ListView listView = findViewById(R.id.listView);
        userListAdapter = new UserListAdapter(this, UserStaticInfo.users);
        listView.setAdapter(userListAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            positionActiveUser = position;
            InitPanel(UserStaticInfo.users.get(position));
        });
    }
    private void InitPanel(User user) {
        nameTextView.setText(user.getName());
        stateTextView.setText(user.getState());
        ageTextView.setText(getString(R.string.age_value, user.getAge()));
        UserVisibility(true);
    }
    private void UserVisibility(boolean visible) {
        userPanel.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    public void BackToList(View view) {
        positionActiveUser = -1;
        UserVisibility(false);
    }
    public void OpenUserActivity(int position) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(UserStaticInfo.POSITION_KEY, position);
        startActivity(intent);
    }
    public void EditUser(View view) {
        if (positionActiveUser >= 0) OpenUserActivity(positionActiveUser);
    }
    private void UpdateListAndUserPanel() {
        userListAdapter.notifyDataSetChanged();
        if (positionActiveUser >= 0) InitPanel(UserStaticInfo.users.get(positionActiveUser));
    }
    @Override protected void onResume() {
        super.onResume();
        // Activity сама обновляет свои View: статических ссылок на экран нет.
        if (userListAdapter != null) UpdateListAndUserPanel();
    }
    @Override protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("selected", positionActiveUser);
        super.onSaveInstanceState(outState);
    }
    @Override public void onBackPressed() {
        if (positionActiveUser >= 0) BackToList(null);
        else super.onBackPressed();
    }
}

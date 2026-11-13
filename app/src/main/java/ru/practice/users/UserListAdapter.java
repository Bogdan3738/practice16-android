package ru.practice.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/** Адаптер переиспользует строки ListView через convertView. */
public final class UserListAdapter extends BaseAdapter {
    private final List<User> users;
    private final LayoutInflater inflater;
    public UserListAdapter(Context context, List<User> users) {
        this.users = users;
        inflater = LayoutInflater.from(context);
    }
    @Override public int getCount() { return users.size(); }
    @Override public User getItem(int position) { return users.get(position); }
    @Override public long getItemId(int position) { return position; }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) row = inflater.inflate(R.layout.item_user, parent, false);
        User user = getItem(position);
        ((TextView) row.findViewById(R.id.NameTextView)).setText(user.getName());
        ((TextView) row.findViewById(R.id.StateTextView)).setText(user.getState());
        int background;
        int label;
        switch (user.getStateSignal()) {
            case 1: background = R.drawable.back_online; label = R.string.online; break;
            case 2: background = R.drawable.back_departed; label = R.string.departed; break;
            default: background = R.drawable.back_offline; label = R.string.offline;
        }
        View signal = row.findViewById(R.id.StateRound);
        signal.setBackgroundResource(background);
        signal.setContentDescription(row.getContext().getString(label));
        return row;
    }
}

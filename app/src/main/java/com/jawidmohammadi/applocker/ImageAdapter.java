package com.jawidmohammadi.applocker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class ImageAdapter extends ArrayAdapter<ApplicationInfo> {

  private final PackageManager manager;

  public ImageAdapter(@NonNull Context context, int resource,
      @NonNull List<ApplicationInfo> objects){
    super(context, resource, objects);
    manager = context.getPackageManager();
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view = (convertView != null) ? convertView
        : LayoutInflater.from(getContext()).inflate(R.layout.icon_item, parent, false);
    ApplicationInfo info = getItem(position);
    ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(info.loadIcon(manager));
    ((TextView) view.findViewById(R.id.label)).setText(manager.getApplicationLabel(info));
    return view;
  }
}

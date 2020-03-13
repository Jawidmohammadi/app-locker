package com.jawidmohammadi.applocker.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.model.entity.App;
import com.jawidmohammadi.applocker.view.AppRecyclerAdapter.Holder;
import java.util.List;
//created the recycler adapter to view list of apps in recycler view
public class AppRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final PackageManager manager;
  private final List<App> apps;

  public AppRecyclerAdapter(Context context, List<App> apps) {
    this.context = context;
    manager = context.getPackageManager();
    this.apps = apps;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new Holder(LayoutInflater.from(context).inflate(R.layout.icon_item, parent, false));
  }

  @Override
  public int getItemCount() {
    return apps.size();
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position, apps.get(position), manager);
  }

  static class Holder extends RecyclerView.ViewHolder {

    private final ImageView icon;
    private final TextView label;

    private Holder(@NonNull View itemView) {
      super(itemView);
      icon = itemView.findViewById(R.id.icon);
      label = itemView.findViewById(R.id.label);
    }

    private void bind(int position, App app, PackageManager manager) {
      label.setText(app.getLabel());
      icon.setContentDescription(app.getLabel());
      try {
        icon.setImageDrawable(manager.getApplicationIcon(app.getPkg()));
      } catch (NameNotFoundException e) {
        icon.setImageResource(R.drawable.ic_launcher_foreground);
      }
    }

  }

}

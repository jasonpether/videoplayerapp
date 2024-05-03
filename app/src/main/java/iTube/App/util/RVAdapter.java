package iTube.App.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import iTube.App.R;
import iTube.App.model.Link;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private List<Link> linkList;
    private Context context;


    public RVAdapter(List<Link> linkList, Context context) {
        this.linkList = linkList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Link link = linkList.get(position);
        holder.rowName.setText(link.getUrl());
    }

    @Override
    public int getItemCount() {
        return linkList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.rowName);
        }
    }
}

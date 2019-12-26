package com.educational.nsutresources.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.educational.nsutresources.Class.Notice;
import com.educational.nsutresources.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {


    private ArrayList<Notice> mNotices;
    private Context mContext;

    public NoticeAdapter(ArrayList<Notice> notices, Context context) {
        mNotices = notices;
        mContext = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notices_item, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoticeViewHolder holder, int position) {

        final Notice notice = mNotices.get(position);

        holder.subject.setText(notice.getTitle());
        holder.pubDate.setText(notice.getPubDate());
        holder.pubBy.setText(notice.getPubBy());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext,"Download feature will be available in our next update", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(notice.getDownloadLink()));
                // mContext.startActivity(intent);

                /*
                DownloadManager downloadmanager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(notice.getDownloadLink()));
                request.setTitle(notice.getTitle());
                request.setDescription("Downloading");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setVisibleInDownloadsUi(false);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,notice.getTitle());

                downloadmanager.enqueue(request);
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotices.size();
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {

        TextView subject;
        TextView pubDate;
        TextView pubBy;

        View rootView;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            subject = itemView.findViewById(R.id.subject);
            pubDate = itemView.findViewById(R.id.published_on);
            pubBy = itemView.findViewById(R.id.published_by);
        }
    }
}

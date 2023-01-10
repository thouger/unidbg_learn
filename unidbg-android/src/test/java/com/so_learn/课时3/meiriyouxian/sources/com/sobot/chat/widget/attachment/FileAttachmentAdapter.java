package com.sobot.chat.widget.attachment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.api.model.SobotFileModel;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.r;
import com.sobot.chat.widget.attachment.AttachmentView;
import java.util.ArrayList;

public class FileAttachmentAdapter extends RecyclerView.Adapter<a> {
    private Context a;
    private ArrayList<SobotFileModel> b;
    private int c;
    private AttachmentView.a d;

    public FileAttachmentAdapter(Context context, ArrayList<SobotFileModel> arrayList, int i, AttachmentView.a aVar) {
        this.a = context;
        this.b = arrayList;
        this.c = i;
        this.d = aVar;
    }

    /* renamed from: a */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        AttachmentView attachmentView = new AttachmentView(this.a);
        attachmentView.setLayoutParams(new FrameLayout.LayoutParams((r.a(this.a)[0] - r.a(this.a, 60.0f)) / 3, r.a(this.a, 85.0f)));
        return new a(attachmentView);
    }

    /* renamed from: a */
    public void onBindViewHolder(a aVar, int i) {
        SobotFileModel sobotFileModel = this.b.get(i);
        aVar.a.setFileName(sobotFileModel.getFileName());
        m.c(i + "\t" + sobotFileModel.getFileType() + "\t" + sobotFileModel.getFileUrl());
        aVar.a.setFileUrl(sobotFileModel.getFileUrl());
        aVar.a.setFileTypeIcon(a.a(sobotFileModel.getFileType()));
        aVar.a.setFileNameColor(this.c);
        aVar.a.setPosition(i);
        aVar.a.setListener(this.d);
        aVar.a.setFileModel(sobotFileModel);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<SobotFileModel> arrayList = this.b;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    /* access modifiers changed from: package-private */
    public static class a extends RecyclerView.ViewHolder {
        private AttachmentView a;

        public a(View view) {
            super(view);
            this.a = (AttachmentView) view;
        }
    }
}

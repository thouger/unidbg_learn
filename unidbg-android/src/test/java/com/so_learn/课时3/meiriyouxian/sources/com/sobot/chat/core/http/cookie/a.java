package com.sobot.chat.core.http.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

/* compiled from: SerializableHttpCookie */
public class a implements Serializable {
    private static final long a = 6374381323722046732L;
    private final transient HttpCookie b;
    private transient HttpCookie c;

    public a(HttpCookie httpCookie) {
        this.b = httpCookie;
    }

    public HttpCookie a() {
        HttpCookie httpCookie = this.b;
        HttpCookie httpCookie2 = this.c;
        return httpCookie2 != null ? httpCookie2 : httpCookie;
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.b.getName());
        objectOutputStream.writeObject(this.b.getValue());
        objectOutputStream.writeObject(this.b.getComment());
        objectOutputStream.writeObject(this.b.getCommentURL());
        objectOutputStream.writeObject(this.b.getDomain());
        objectOutputStream.writeLong(this.b.getMaxAge());
        objectOutputStream.writeObject(this.b.getPath());
        objectOutputStream.writeObject(this.b.getPortlist());
        objectOutputStream.writeInt(this.b.getVersion());
        objectOutputStream.writeBoolean(this.b.getSecure());
        objectOutputStream.writeBoolean(this.b.getDiscard());
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.c = new HttpCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        this.c.setComment((String) objectInputStream.readObject());
        this.c.setCommentURL((String) objectInputStream.readObject());
        this.c.setDomain((String) objectInputStream.readObject());
        this.c.setMaxAge(objectInputStream.readLong());
        this.c.setPath((String) objectInputStream.readObject());
        this.c.setPortlist((String) objectInputStream.readObject());
        this.c.setVersion(objectInputStream.readInt());
        this.c.setSecure(objectInputStream.readBoolean());
        this.c.setDiscard(objectInputStream.readBoolean());
    }
}

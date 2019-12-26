package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;


public class User {

	private PrintStream streamOut;
    private InputStream streamIn;
    private String nickname;
    private Socket client;

    public User(Socket client, String name) throws IOException {
        this.streamOut = new PrintStream(client.getOutputStream());
        this.streamIn = client.getInputStream();
        this.client = client;
        this.nickname = name;
    }


    public PrintStream getOutStream() {
        return this.streamOut;
    }

    public InputStream getInputStream() {
        return this.streamIn;
    }

    public String getNickname() {
        return this.nickname;
    }
}

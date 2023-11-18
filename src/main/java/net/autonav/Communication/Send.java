package net.autonav.Communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Send {
    
    public static void send(Targets target, Serializable object) {
        try (ServerSocketChannel ssChannel = ServerSocketChannel.open()) {
            ssChannel.configureBlocking(true);
            int port = 4234; //TODO: Change this to an actual port 
            ssChannel.socket().bind(new InetSocketAddress(port));
            
            while (true) {
                SocketChannel sChannel = ssChannel.accept();
                
                ObjectOutputStream oos = new ObjectOutputStream(sChannel.socket().getOutputStream());
                oos.writeObject(object);
                oos.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    enum Targets {
        ROBOT, 
        SERVER,
        PLAYER
    }
}

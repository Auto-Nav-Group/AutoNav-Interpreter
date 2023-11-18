package net.autonav.Communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import net.autonav.Data.MovementCommand;

public class Recieve {
    public static BlockingQueue<MovementCommand> movementQueue = new LinkedBlockingDeque<>();

    public static void recieve() throws IOException, ClassNotFoundException {
        SocketChannel sChannel = SocketChannel.open();
        sChannel.configureBlocking(true);
        if (sChannel.connect(new InetSocketAddress("localhost", 4234))) {
            ObjectInputStream ois = new ObjectInputStream(sChannel.socket().getInputStream());
            MovementCommand command = (MovementCommand) ois.readObject();
            ois.close();
            movementQueue.add(command);
        }
    }
}

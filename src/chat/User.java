package chat;

import org.eclipse.jetty.websocket.api.Session;

/**
 * Created by gubarev-mv on 14.05.2018.
 */
public class User {
    Session session;
    String status;

    public User(Session session) {
        this.session = session;
        this.status = "OPEN";
    }
}

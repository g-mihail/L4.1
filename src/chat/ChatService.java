package chat;

import org.eclipse.jetty.websocket.api.Session;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class ChatService {
    private Set<Session> webSockets; //cписок участников в команте

    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }


    public void sendMessage(String data) { //отправка сообщений всем участкикам
        for (Session user : webSockets) {
            try {
              user.getRemote().sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void add(Session webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(Session webSocket) {
        webSockets.remove(webSocket);
    }

    public int getSize() {
        return webSockets.size();
    } //количество участников в комнате



}

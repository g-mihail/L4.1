package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author v.chibrikov
 *         <p/>
 *         Пример кода для курса на https://stepic.org/
 *         <p/>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
 //  private Session session;
   // private String status;
  //  private int id;
    //private List<Session> webSockets; //cписок активных подключений в команте
    private Map<Session,String> sessionStatus; //cессия и ее статус






    public ChatWebSocket() {
       // this.webSockets =new ArrayList<>();
       sessionStatus = new HashMap<>();

    }


  @OnWebSocketConnect
    public void onOpen(Session session) {

         System.out.println("Успешно подключены");
        sessionStatus.put(session,"Connect");
    }

  /* @OnWebSocketMessage
    public void onMessage(String data) {


      /*  if ((data.equals("1")) && (!status.equals("вочередьнапоиск")))//если нажата кнопа старт
        {
            try {
              session.getRemote().sendString("Добавлены в очередь на поиск");
                webSockets.add(session);
                status ="вочередьнапоиск";
              if (serch(session)==null) System.out.println("Чат не создан");
            }
            catch (IOException e)
            {
                System.out.println("IOException"+e);
            }

            return;
        }

        if (data.equals("2")) //если нажата кнопка стоп -прервать разговор, но не отключаться
        {
            webSockets.remove(session);

            return;
        }
        if (status.equals("вбеседе"))
        {

        }

    }
*/

 @OnWebSocketMessage
    public void onMessage(Session session, String data) {

        System.out.println(session.isOpen());

        if ((data.equals("1")) && sessionStatus.get(session).equals("Connect") )//если нажата кнопа старт и статус коннект
        {
            try {
                session.getRemote().sendString("Добавлены в очередь на поиск");

                //webSockets.add(session);
                sessionStatus.replace(session,"Connect","Вочередьнапоиск");
              //  if (serch(session)==null) System.out.println("Чат не создан");
            }
            catch (IOException e)
            {
                System.out.println("IOException"+e);
            }

            return;
        }

        if ((data.equals("1")) && sessionStatus.get(session).equals("Вочередьнапоиск") )//если нажата кнопа старт и статус коннект
        {
            try {
                session.getRemote().sendString("Подождите, вы в очереди на поиск");

                //webSockets.add(session);
            //    sessionStatus.replace(session,"Connect","Вочередьнапоиск");
              //  if (serch(session)==null) System.out.println("Чат не создан");
            }
            catch (IOException e)
            {
                System.out.println("IOException"+e);
            }

            return;
        }


        if (data.equals("2")) //если нажата кнопка стоп -прервать разговор, но не отключаться
        {


            return;
        }
        if (sessionStatus.get(session).equals("Вбеседе"))
        {

        }

    }

    public ChatService serch(Session session)
    {
        int size=0;
        for (Map.Entry<Session, String> sessionStringEntry : sessionStatus.entrySet()) {
            if (sessionStringEntry.getValue().equals("Вочередьнапоиск"))
                size++;
        }
       System.out.println("Количество пользователей для поиска" + size);
    if (size>=2)  {
        ChatService chatService = new ChatService();
        for (int i = 0; i <2 ; i++) {
           // chatService.add(sessionStatus.);
           // webSockets.remove(0);
            System.out.println("Чат создан");
        }
        return chatService;
    }


        return null;
    }

    @OnWebSocketClose
    public void onClose(Session session,int statusCode, String reason) {
       // sessionStatus.remove(session);
    }


}

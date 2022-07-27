package hac.ex4.listeners;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * here we can count the number of sessions with listener
 *
 */
@Component
@WebListener
public class SessionListenerCounter implements HttpSessionListener {
    /**
     * atomic integer to count the number of sessions
     */
    private final AtomicInteger activeSessions;

    /**
     * constructor
     */
    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }

    /**
     * to check how much sessions are active
     * @return number of active sessions
     */
    public int getTotalActiveSession() {
        return activeSessions.get();
    }

    /**
     * when session is created we increment the counter and print the number of active sessions
     * @param event session event
     */
    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("+++ Total active users are: " + activeSessions.get());;
    }

    /**
     * when session is destroyed we decrement the counter and print the number of active sessions
     * @param event session event
     */
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("--- Total active users are: " + activeSessions.get());
    }
}

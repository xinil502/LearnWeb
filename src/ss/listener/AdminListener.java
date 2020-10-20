package ss.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AdminListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        AdminOnline.raise();
        event.getSession().getServletContext().setAttribute("online", AdminOnline.getOnline());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        AdminOnline.reduce();
        event.getSession().getServletContext().setAttribute("online", AdminOnline.getOnline());
    }
}
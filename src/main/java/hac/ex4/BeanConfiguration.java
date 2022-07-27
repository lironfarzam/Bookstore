package hac.ex4;

import hac.ex4.beans.Cart;
import hac.ex4.listeners.SessionListenerCounter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

/**
 * configuration for beans
 */
@Configuration
public class BeanConfiguration {

    /**
     * bean for session listener counter
     * @return session listener counter
     */
    @Bean
    public ServletListenerRegistrationBean<SessionListenerCounter> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<SessionListenerCounter> listenerRegBean = new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new SessionListenerCounter());
        return listenerRegBean;
    }
    /**
     * function to cart session
     * @return cart session
     */
    @Bean
    @SessionScope
    public Cart sessionScopeBeanCart() {
        return new Cart();
    }
}

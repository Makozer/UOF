

package database.utils.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter implementation fuer die gesamte seite
 * @author cedri
 *
 */
@WebFilter("/*")
public class XSSFilter implements Filter {
	protected FilterConfig config;
	public XSSFilter() {}	

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	this.config = filterConfig;
    }

    @Override
    public void destroy() {
    	config = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    	System.out.println("Filter ist aufgerufen");
    	//eigentliche filterung im wrapper
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }

}
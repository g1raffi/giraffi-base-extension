package ch.mobi.gluon.base;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class AppInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write(getAppInfoService().getAppInfo().asHumanReadableString());
    }

    private AppInfoService getAppInfoService() {
        return CDI.current().select(AppInfoService.class).get();
    }
}

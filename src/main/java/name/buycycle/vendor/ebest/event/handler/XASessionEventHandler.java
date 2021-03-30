package name.buycycle.vendor.ebest.event.handler;

import name.buycycle.vendor.ebest.event.vo.res.Response;
import name.buycycle.vendor.ebest.session.com4j._IXASessionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * xa session 로그인 이벤트 핸들러
 * @author : ijyoon
 * @date : 2021/03/24
 */
public class XASessionEventHandler extends _IXASessionEvents {

    private Logger logger = LoggerFactory.getLogger(XASessionEventHandler.class);
    private Response response;

    public XASessionEventHandler() {
        this.response = new Response(UUID.randomUUID().toString());
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public void login(String szCode, String szMsg) {
        logger.info("["+ Thread.currentThread().getName() + " " + Thread.currentThread().isDaemon() + "] szCode : " + szCode + " , szMsg : " + szMsg);

        response.putHeader("szCode", szCode);
        response.putHeader("szMsg", szMsg);

        synchronized (this){
            this.notify();
        }
    }

    @Override
    public void logout() {
        logger.warn("logout event receive");
    }

    @Override
    public void disconnect() {
        logger.warn("disconnect event receive");
    }
}

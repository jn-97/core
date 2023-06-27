package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}

/*
[3dc81f1c-ee66-4ccb-9d46-27f67072757f] request scope bean create:hello.core.common.MyLogger@331f9bc1
[3dc81f1c-ee66-4ccb-9d46-27f67072757f][http://localhost:8080/log-demo] controller test
[3dc81f1c-ee66-4ccb-9d46-27f67072757f][http://localhost:8080/log-demo] service id = testId
[3dc81f1c-ee66-4ccb-9d46-27f67072757f] request scope bean close:hello.core.common.MyLogger@331f9bc1
*/


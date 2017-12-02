package hht.weixin.study.controller;

import hht.weixin.study.model.TextMessage;
import hht.weixin.study.utils.CheckUtils;
import hht.weixin.study.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * Description.
 *
 * @author: huang
 * Date: 17-12-2
 */
@RestController
@Slf4j
public class IndexController {

    /**
     * 微信token校验.
     * @return
     */
    @RequestMapping("/weChatToken")
    public String index(String signature, String timestamp, String nonce, String echostr) throws NoSuchAlgorithmException {
        if (CheckUtils.checkSignature(signature, timestamp, nonce)) {
            log.info(echostr);
            return echostr;
        }
        log.error(signature + " : " + " : " + timestamp + " : " + nonce + " : " + echostr);
        return null;
    }

    /**
     * 发送文本消息.
     * @param request
     * @return
     */
    @PostMapping("/weChatToken")
    public String sendMessage(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = MessageUtil.messageToMap(request);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");

        String message = null;
        if ("text".equals(msgType)) { // 判断是否为文本类型
            TextMessage text = new TextMessage();
            text.setFromUserName(toUserName);
            text.setToUserName(fromUserName);
            text.setContent("发送的消息为： " + content);
            text.setCreateTime(System.currentTimeMillis());
            text.setMsgType("text");
            message = MessageUtil.textMessageToXml(text);
        }
        return message;

    }
}

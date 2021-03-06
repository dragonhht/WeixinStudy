package hht.weixin.study.controller;

import hht.weixin.study.model.*;
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

        String message = null;
        // 判断是否为文本类型
        if ("text".equals(msgType)) {
            String content = map.get("Content");
            TextMessage text = new TextMessage();
            text.setFromUserName(toUserName);
            text.setToUserName(fromUserName);
            text.setContent("发送的消息为： " + content);
            text.setCreateTime(System.currentTimeMillis());
            text.setMsgType("text");
            message = MessageUtil.messageToXml(text);
        }
        // 消息为图片类型
        if ("image".equals(msgType)) {
            String PicUrl = map.get("PicUrl");
            log.info("图片路径为： " + PicUrl);
            String MediaId = map.get("MediaId");
            MediaMessage media = new MediaMessage();
            media.setFromUserName(toUserName);
            media.setToUserName(fromUserName);
            media.setCreateTime(System.currentTimeMillis());
            media.setMsgType("image");
            media.setImage(new MediaId(MediaId));
            message = MessageUtil.messageToXml(media);
        }
        // 消息为语音
        if ("voice".equals(msgType)) {
            String MediaId = map.get("MediaId");
            VoiceMessage voice = new VoiceMessage();
            voice.setFromUserName(toUserName);
            voice.setToUserName(fromUserName);
            voice.setCreateTime(System.currentTimeMillis());
            voice.setMsgType("voice");
            voice.setVoice(new MediaId(MediaId));
            message = MessageUtil.messageToXml(voice);
        }
        // 消息为视频
        if ("video".equals(msgType)) {
            String MediaId = map.get("MediaId");
            VideoMessage video = new VideoMessage();
            video.setFromUserName(toUserName);
            video.setToUserName(fromUserName);
            video.setCreateTime(System.currentTimeMillis());
            video.setMsgType("video");
            Video v = new Video();
            v.setDescription("返回的视频描述");
            v.setTitle("视频标题");
            v.setMediaId(MediaId);
            video.setVideo(v);
            message = MessageUtil.messageToXml(video);
        }
        // 关注与取关事件
        if ("event".equals(msgType)) {
            String eventType = map.get("Event");
            if ("subscribe".equals(eventType)) {
                TextMessage text = new TextMessage();
                text.setToUserName(fromUserName);
                text.setFromUserName(toUserName);
                text.setCreateTime(System.currentTimeMillis());
                text.setMsgType("text");
                text.setContent("感谢您的关注!!!");
                message = MessageUtil.messageToXml(text);
            }
            if ("unsubscribe".equals(eventType)) {
                log.info(fromUserName , "已取消关注");
            }
        }
        return message;

    }
}

package hht.weixin.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 文本消息model.
 *
 * @author: huang
 * Date: 17-12-2
 */
@Getter
@Setter
@ToString
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;
}

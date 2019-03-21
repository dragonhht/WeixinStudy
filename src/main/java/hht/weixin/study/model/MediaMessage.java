package hht.weixin.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * .
 *
 * @author: huang
 * @Date: 2019-3-21
 */
@Getter
@Setter
@ToString
public class MediaMessage extends Message {
    private MediaId Image;
}

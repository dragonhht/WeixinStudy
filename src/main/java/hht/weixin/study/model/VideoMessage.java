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
public class VideoMessage extends Message {
    private Video Video;
}

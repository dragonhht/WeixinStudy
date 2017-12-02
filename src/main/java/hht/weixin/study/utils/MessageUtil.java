package hht.weixin.study.utils;

import com.thoughtworks.xstream.XStream;
import hht.weixin.study.model.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类.
 *
 * @author: huang
 * Date: 17-12-2
 */
@Slf4j
public class MessageUtil {

    /**
     * 将消息的xml格式转换为map.
     * @param request
     * @return
     */
    public static Map<String, String> messageToMap(HttpServletRequest request) throws IOException, DocumentException {
        request.setCharacterEncoding("UTF-8");
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();

        InputStream input = request.getInputStream();
        Document document = reader.read(input);

        Element root = document.getRootElement();

        List<Element> elementList = root.elements();

        for (Element element : elementList) {
            map.put(element.getName(), element.getText());
        }
        input.close();
        return map;
    }

    /**
     * 将文本消息对象转换为xml.
     * @param message
     * @return
     */
    public static String textMessageToXml(TextMessage message) {
        XStream xStream = new XStream();
        xStream.alias("xml", message.getClass());
        return xStream.toXML(message);
    }

}

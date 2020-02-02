import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Package:PACKAGE_NAME
 * Description:
 *
 * @Date:2020/1/31 22:42
 * @Author:xuyewei
 */

public class Test3 {
    public static void main(String[] args) {
        String a = "<script>alert('Hello World');</script>";
        a = StringEscapeUtils.escapeHtml4(a);
        System.out.println(a);
    }
}

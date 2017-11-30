import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class test {
    @Test
    public void test() throws Exception{

        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板路径
        configuration.setClassForTemplateLoading(this.getClass(),"/ftl");
        //设置模板文件的字符集
        configuration.setDefaultEncoding("UTF-8");
        //加载模板，创建模板对象
        Template template = configuration.getTemplate("1.ftl");
        //
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","ymd");
        //创建write对象
        Writer out = new FileWriter("g:/ft/1.html");

        template.process(map,out);

        out.close();
    }
}

package vknife.compiler;

import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

/**
 * 作者： mooney
 * 日期： 2018/1/31
 * 邮箱： shili_yan@sina.com
 * 描述：
 */

public class VknifeProxy extends AbstractProxy {

    public VknifeProxy(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    @Override
    public void process(Set<? extends Element> elements) {
        for (Element element : elements) {

        }
    }

}

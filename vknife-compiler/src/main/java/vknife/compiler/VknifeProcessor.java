package vknife.compiler;

import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

import vknife.annotations.BindView;
import vknife.compiler.bean.FileBean;
import vknife.compiler.bean.ViewBean;


/**
 * 作者： mooney
 * 日期： 2018/1/23
 * 邮箱： shili_yan@sina.com
 * 描述： 模块插槽代码生成器
 */
@AutoService(Processor.class)
public class VknifeProcessor extends AbstractProcessor {


    private Messager messager;
    private VknifeProxy mProxy;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        messager = processingEnv.getMessager();
        mProxy=new VknifeProxy(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {

        if (set.isEmpty()) {
            return false;
        }

        Set<? extends Element> viewElements = roundEnv.getElementsAnnotatedWith(BindView.class);


        Map<String, FileBean> fileCache=new HashMap<>();

        for (Element element: viewElements){

            VariableElement variableElement= (VariableElement) element;
            /**
             * viewId
             */
            int viewId=element.getAnnotation(BindView.class).value();

            /**
             * 获取类名
             */
            TypeElement classElement = (TypeElement) variableElement.getEnclosingElement();
            String clzFullName = classElement.getQualifiedName().toString();

            /**
             * 变量名
             */
            String fieldName=variableElement.getSimpleName().toString();

            /**
             * 变量类名
             */
            TypeMirror elementType = element.asType();
            String fieldType =elementType.toString();

            ViewBean viewBean =new ViewBean();
            viewBean.setClzName(clzFullName);
            viewBean.setFieldName(fieldName);
            viewBean.setFieldType(fieldType);
            viewBean.setViewId(viewId);

            FileBean fileBean=fileCache.get(clzFullName);
            if (fileBean==null){
                fileBean=new FileBean();
                fileBean.setClzName(clzFullName);
            }
            fileBean.addViewBean(viewBean);
            log("\nclzFullName=="+clzFullName
                    +"\nfieldTypeName=="+fieldType
                    + "\nfieldName=="+fieldName
                    + "\nviewId=="+viewId);
        }


        Iterator<Map.Entry<String, FileBean>> iterator=fileCache.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, FileBean> map= iterator.next();
            String fileName=map.getKey();
            FileBean fileBean=map.getValue();


        }


        return false;
    }


    /************************************编译时日志函数*****************************************/
    protected void log(String str) {
        l(Diagnostic.Kind.NOTE, str);
    }

    protected void logE(String str) {
        l(Diagnostic.Kind.ERROR, str);
    }

    protected void logW(String str) {
        l(Diagnostic.Kind.WARNING, str);
    }

    private void l(Diagnostic.Kind kind, String str){

        str=getClass().getName()+"-->"+str;
        //输出日志
        messager.printMessage(kind, str);
    }
}
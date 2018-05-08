package com.leozkt.compiler;

import com.google.auto.service.AutoService;
import com.leozkt.annotations.BindItem;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

/**
 * @author zhengkaituo
 * @date 2018/4/17
 */
@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {

    private Filer mFiler; //文件相关的辅助类
    private Elements mElementUtils; //元素相关的辅助类
    private Messager mMessager; //日志相关的辅助类
    private final String PREFIX = "gt";
    private final String SUFFIX = "_ItemViewBinding";
    // 存放同一个Class下的所有注解信息
    Map<String, List<VariableInfo>> classMap = new LinkedHashMap<>();
    // 存放Class对应的信息：TypeElement
    Map<String, TypeElement> classTypeElement = new LinkedHashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("BindViewProcessor");
        collectInfo(roundEnvironment);

        return true;
    }


    void collectInfo(RoundEnvironment roundEnvironment) {
        classMap.clear();
        classTypeElement.clear();
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindItem.class);
        int index = 0;
        for (Element element : elements) {
            index++;
            // 获取 BindItem 注解的值
            int viewId = element.getAnnotation(BindItem.class).layout();
            TypeMirror typeMirror = null;
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    String key = entry.getKey().getSimpleName().toString();
                    Object value = entry.getValue().getValue();
                    switch (key) {
                        case "layout":
                            viewId = (Integer) value;
                            break;
                        case "value":
                            typeMirror = (TypeMirror) value;
                        default:
                            break;
                    }
                }
            }

            // 代表被注解的元素
            VariableElement variableElement = (VariableElement) element;

            // 注解元素所在的Class
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            // Class的完整路径
            String classFullName = typeElement.getQualifiedName().toString();
//            System.out.println(classFullName + "/" + element.getSimpleName() + "/" + viewId + "/" + typeMirror.toString());

            // 收集Class中所有被注解的元素
            List<VariableInfo> variableList = classMap.get(classFullName);
            if (variableList == null) {
                variableList = new ArrayList<>();
                classMap.put(classFullName, variableList);

                // 保存Class对应要素（名称、完整路径等）
                classTypeElement.put(classFullName, typeElement);
            }
            VariableInfo variableInfo = new VariableInfo();
            variableInfo.setVariableElement(variableElement);
            variableInfo.setLayoutId(viewId);
            variableInfo.setEntityClass(typeMirror.toString());
            variableList.add(variableInfo);

        }
        if (classMap != null) {
            for (Map.Entry<String, List<VariableInfo>> entry : classMap.entrySet()) {

                if (entry.getValue() != null && entry.getValue().size() > 0) {
                    VariableElement firstElement = entry.getValue().get(0).mVariableElement;

                    TypeElement typeElement = (TypeElement) firstElement.getEnclosingElement();

                    FieldSpec.Builder target = FieldSpec.builder(ClassName.get(typeElement.asType()), "target")
                            .addModifiers(Modifier.PRIVATE);

                    MethodSpec.Builder getItemClass = MethodSpec.methodBuilder("getItemClass")
                            .addModifiers(Modifier.PUBLIC)
                            .returns(TypeName.INT)
                            .addStatement("return 1");

                    /**
                     * 创建构造方法
                     */
                    MethodSpec.Builder init = MethodSpec.constructorBuilder()
                            .addModifiers(Modifier.PUBLIC)
                            .addStatement("this.target=activity")
                            .addParameter(ClassName.get(typeElement.asType()), "activity");


                    MethodSpec.Builder unbind = MethodSpec.methodBuilder("unbind")
                            .addModifiers(Modifier.PUBLIC)
                            .addAnnotation(Override.class);

                    for (VariableInfo variableInfo : entry.getValue()) {
                        VariableElement bindElement = variableInfo.mVariableElement;
                        init.addStatement(String.format("activity.%s=%s.getBinder(%d,%s.class)", bindElement.getSimpleName(), ClassName.get("com.leozkt.chameleonadapterlib", "BindingUtils").toString(), variableInfo.layoutId, variableInfo.entityClass));
                        unbind.addStatement(String.format("target.%s=null", bindElement.getSimpleName()));

                    }

                    /**
                     * 创建类
                     */
                    TypeSpec typeSpec = TypeSpec.classBuilder(typeElement.getSimpleName() + SUFFIX)
                            .addJavadoc("DO NOT MODIFY!IT IS AUTO GENERATED")
                            .addSuperinterface(ClassName.get("com.leozkt.chameleonadapterlib", "Unbinder"))
                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                            .addMethod(getItemClass.build())
                            .addMethod(init.build())
                            .addMethod(unbind.build())
                            .addField(target.build())
                            .build();
                    JavaFile javaFile = JavaFile.builder(getPackageName(typeElement), typeSpec).build();
                    try {
                        javaFile.writeTo(processingEnv.getFiler());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
        mMessager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(BindItem.class.getCanonicalName());
        return annotationTypes;
    }

    private String getPackageName(TypeElement type) {
        return mElementUtils.getPackageOf(type).getQualifiedName().toString();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}

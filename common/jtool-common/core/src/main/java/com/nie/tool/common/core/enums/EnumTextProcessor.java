package com.nie.tool.common.core.enums;

import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.Set;

/**
 * @author njy
 * @since 2024/7/22 09:17
 */
@SupportedAnnotationTypes("com.nie.tool.common.core.enums.EnumText")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EnumTextProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(EnumText.class)) {
            if (element.getKind() == ElementKind.FIELD) {
                EnumText enumText = element.getAnnotation(EnumText.class);
                String value = enumText.value();

                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                String className = enclosingElement.getSimpleName().toString();
                String packageName = processingEnv.getElementUtils().getPackageOf(enclosingElement).getQualifiedName().toString();

                String fieldName = element.getSimpleName().toString();
                String methodName = "get" + capitalize(fieldName) + capitalize(value);

                MethodSpec getterMethod = MethodSpec.methodBuilder(methodName)
                        .addModifiers(Modifier.PUBLIC)
                        .returns(String.class)
                        .addStatement("return $N.$N()", fieldName, value)
                        .build();

                TypeSpec classSpec = TypeSpec.classBuilder(className + "Generated")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(getterMethod)
                        .build();

                JavaFile javaFile = JavaFile.builder(packageName, classSpec)
                        .build();

                try {
                    JavaFileObject file = processingEnv.getFiler()
                            .createSourceFile(packageName + "." + className + "Generated");
                    javaFile.writeTo(file.openWriter());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

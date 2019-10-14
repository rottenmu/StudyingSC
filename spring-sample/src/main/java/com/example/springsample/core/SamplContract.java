package com.example.springsample.core;

import com.example.springsample.annotation.SamplRequestMapping;
import com.example.springsample.annotation.SampleAnnotatedParamProcessor;
import com.example.springsample.annotation.SampleRequestParamParameterProcessor;
import feign.Contract;
import feign.Feign;
import feign.MethodMetadata;
import feign.Param;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static feign.Util.checkState;
import static feign.Util.emptyToNull;
import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

public class SamplContract extends Contract.BaseContract implements ResourceLoaderAware {

    private static final String ACCEPT = "Accept";

    private static final String CONTENT_TYPE = "Content-Type";

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    private final Map<Class<? extends Annotation>, SampleAnnotatedParamProcessor> annotatedArgumentProcessors;
    private final Map<String, Method> processedMethods = new HashMap<>();

    private final ConversionService conversionService;
    private final Param.Expander expander;
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    public SamplContract() {
        this(Collections.emptyList());
    }

    public SamplContract(
            List<SampleAnnotatedParamProcessor> annotatedParameterProcessors) {
        this(annotatedParameterProcessors, new DefaultConversionService());
    }


    public SamplContract(List<SampleAnnotatedParamProcessor> annotatedParameterProcessors, ConversionService conversionService) {
        Assert.notNull(annotatedParameterProcessors,
                "Parameter processors can not be null.");
        Assert.notNull(conversionService, "ConversionService can not be null.");

        List<SampleAnnotatedParamProcessor> processors;
        if (!annotatedParameterProcessors.isEmpty()) {
            processors = new ArrayList<>(annotatedParameterProcessors);
        }
        else {
            processors = getDefaultAnnotatedArgumentsProcessors();
        }
        this.annotatedArgumentProcessors = toAnnotatedArgumentProcessorMap(processors);
        this.conversionService = conversionService;
        this.expander = new ConvertingExpander(conversionService);
    }
    @Override
    protected void processAnnotationOnClass(MethodMetadata methodMetadata, Class<?> aClass) {

    }
    @Override
    public MethodMetadata parseAndValidateMetadata(Class<?> targetType, Method method) {
        this.processedMethods.put(Feign.configKey(targetType, method), method);
        MethodMetadata md = super.parseAndValidateMetadata(targetType, method);

        RequestMapping classAnnotation = findMergedAnnotation(targetType,
                RequestMapping.class);
        if (classAnnotation != null) {
            // produces - use from class annotation only if method has not specified this
            if (!md.template().headers().containsKey(ACCEPT)) {
                parseProduces(md, method, classAnnotation);
            }

            // consumes -- use from class annotation only if method has not specified this
            if (!md.template().headers().containsKey(CONTENT_TYPE)) {
                parseConsumes(md, method, classAnnotation);
            }

            // headers -- class annotation is inherited to methods, always write these if
            // present
            parseHeaders(md, method, classAnnotation);
        }
        return md;
    }
    @Override
    protected void processAnnotationOnMethod(MethodMetadata methodMetadata, Annotation annotation, Method method) {

        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (!RequestMapping.class.isInstance(annotation) && !annotation
                .annotationType().isAnnotationPresent(RequestMapping.class)) {
            return;
        }
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (Objects.nonNull(requestMapping)) {
            System.out.println( "requestMapping - >"+ requestMapping.toString());

        }
        Annotation annotation1 = AnnotationUtils.findAnnotation(method, annotation.annotationType());
        System.out.println("annotation1 - >" +annotation1.toString());
        RequestMapping methodMapping = findMergedAnnotation(method, RequestMapping.class);
        // HTTP Method
        RequestMethod[] methods = methodMapping.method();
        if (methods.length == 0) {
            methods = new RequestMethod[] { RequestMethod.GET };
        }
        checkOne(method, methods, "method");
        methodMetadata.template().method(methods[0].name());

        // path
        checkAtMostOne(method, methodMapping.value(), "value");
        if (methodMapping.value().length > 0) {
            String pathValue = emptyToNull(methodMapping.value()[0]);
            if (pathValue != null) {
                pathValue = resolve(pathValue);
                // Append path from @RequestMapping if value is present on method
                if (!pathValue.startsWith("/")
                        && !methodMetadata.template().toString().endsWith("/")) {
                    pathValue = "/" + pathValue;
                }
                methodMetadata.template().append(pathValue);
            }
        }

        // produces
        parseProduces(methodMetadata, method, methodMapping);

        // consumes
        parseConsumes(methodMetadata, method, methodMapping);

        // headers
        parseHeaders(methodMetadata, method, methodMapping);

        methodMetadata.indexToExpander(new LinkedHashMap<Integer, Param.Expander>());
    }

    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata methodMetadata, Annotation[] annotations, int i) {


        return false;
    }

    private List<SampleAnnotatedParamProcessor> getDefaultAnnotatedArgumentsProcessors() {

        List<SampleAnnotatedParamProcessor> annotatedArgumentResolvers = new ArrayList<>();
     //   annotatedArgumentResolvers.add(new PathVariableParameterProcessor());
        annotatedArgumentResolvers.add(new SampleRequestParamParameterProcessor());
      //  annotatedArgumentResolvers.add(new RequestHeaderParameterProcessor());

        return annotatedArgumentResolvers;
    }


    private Map<Class<? extends Annotation>, SampleAnnotatedParamProcessor> toAnnotatedArgumentProcessorMap(
            List<SampleAnnotatedParamProcessor> processors) {
        Map<Class<? extends Annotation>, SampleAnnotatedParamProcessor> result = new HashMap<>();
        for (SampleAnnotatedParamProcessor processor : processors) {
            result.put(processor.getAnnotationType(), processor);
        }
        return result;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
    }

    public static class ConvertingExpander implements Param.Expander {
        private final ConversionService conversionService;
        public ConvertingExpander(ConversionService conversionService) {
            this.conversionService = conversionService;
        }
        @Override
        public String expand(Object value) {
            return this.conversionService.convert(value, String.class);
        }

    }

    private String resolve(String value) {
        if (StringUtils.hasText(value)
                && this.resourceLoader instanceof ConfigurableApplicationContext) {
            return ((ConfigurableApplicationContext) this.resourceLoader).getEnvironment()
                    .resolvePlaceholders(value);
        }
        return value;
    }
    private void parseHeaders(MethodMetadata md, Method method,
                              RequestMapping annotation) {
        // TODO: only supports one header value per key
        if (annotation.headers() != null && annotation.headers().length > 0) {
            for (String header : annotation.headers()) {
                int index = header.indexOf('=');
                if (!header.contains("!=") && index >= 0) {
                    md.template().header(resolve(header.substring(0, index)),
                            resolve(header.substring(index + 1).trim()));
                }
            }
        }
    }

    private void parseProduces(MethodMetadata md, Method method,
                               RequestMapping annotation) {
        String[] serverProduces = annotation.produces();
        String clientAccepts = serverProduces.length == 0 ? null
                : emptyToNull(serverProduces[0]);
        if (clientAccepts != null) {
            md.template().header(ACCEPT, clientAccepts);
        }
    }

    private void parseConsumes(MethodMetadata md, Method method,
                               RequestMapping annotation) {
        String[] serverConsumes = annotation.consumes();
        String clientProduces = serverConsumes.length == 0 ? null
                : emptyToNull(serverConsumes[0]);
        if (clientProduces != null) {
            md.template().header(CONTENT_TYPE, clientProduces);
        }
    }


    private void checkAtMostOne(Method method, Object[] values, String fieldName) {
        checkState(values != null && (values.length == 0 || values.length == 1),
                "Method %s can only contain at most 1 %s field. Found: %s",
                method.getName(), fieldName,
                values == null ? null : Arrays.asList(values));
    }

    private void checkOne(Method method, Object[] values, String fieldName) {
        checkState(values != null && values.length == 1,
                "Method %s can only contain 1 %s field. Found: %s", method.getName(),
                fieldName, values == null ? null : Arrays.asList(values));
    }
}

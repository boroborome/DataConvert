package com.happy3w.util.convert;

import com.lenovo.scct.trackingapi.TrackingApiApplication;
import com.lenovo.scct.trackingapi.utils.convert.primitive.IPrimitiveConverter;
import com.lenovo.scct.trackingapi.utils.convert.primitive.PrimitiveConverter;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConvertFactoryProcessor implements BeanFactoryPostProcessor, Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Reflections reflections = new Reflections(TrackingApiApplication.class.getPackage().getName());
        reflections.getSubTypesOf(IPrimitiveConverter.class).forEach(c -> {
            try {
                IPrimitiveConverter instance = c.newInstance();
                PrimitiveConverter.getInstance().register(instance);
            } catch (Exception e) {
                log.error("Failed in registering Converter:{}", c.getName());
            }
        });
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

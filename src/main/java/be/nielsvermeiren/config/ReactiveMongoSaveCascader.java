package be.nielsvermeiren.config;

import be.nielsvermeiren.tag.Technology;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@Log4j2
class ReactiveMongoSaveCascader extends AbstractMongoEventListener<Object> {
    private ReactiveMongoOperations operations;
    private boolean idFound;

    @Autowired
    public ReactiveMongoSaveCascader(ReactiveMongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            if (!field.isAnnotationPresent(CascadeSave.class)) return;
            getFieldValueAsList(source, field).forEach(o -> {
                SaveCascadedField(field, o);
            });
        });
    }

    private List<Object> getFieldValueAsList(Object source, Field field) throws IllegalAccessException {
        Object src = field.get(source);
        List<Object> sourceObjects = new ArrayList<>();
        if (!(src instanceof Collection)) {
            sourceObjects.add(src);
            return sourceObjects;
        }
        sourceObjects.addAll((Collection<?>) src);
        return sourceObjects;
    }

    private boolean targetObjectHasIdField(Object o) {
        idFound = false;
        ReflectionUtils.doWithFields(o.getClass(), field -> {
            if(field.isAnnotationPresent(Id.class)) idFound = true;
        });
        return idFound;
    }

    private void SaveCascadedField(Field field, Object o) {
        if (targetObjectHasIdField(o)) operations.save((Technology) o).subscribe(ok->{
            log.info(String.format("Field %s cascaded successfully", field.getName()));
        }, (error)->{
            log.error(String.format("Failed cascading field %s", field.getName()));
        });
    }
}

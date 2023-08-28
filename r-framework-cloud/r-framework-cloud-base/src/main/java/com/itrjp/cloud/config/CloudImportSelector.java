package com.itrjp.cloud.config;

import com.itrjp.cloud.annotation.EnableCloudService;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class CloudImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableCloudService.class.getName(), false));

        Enum<?> type = attributes.getEnum("type");
        return new String[0];
    }
}

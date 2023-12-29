package com.itrjp.form.handler.field;

import com.itrjp.form.pojo.model.ExtendedDTO;
import com.itrjp.form.pojo.model.ImageExtendedDTO;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/25 16:35
 */
@Service
public class ImageFieldParser extends AbstractFieldParser {
    @Override
    protected Class<? extends ExtendedDTO> getExtensionSubClass() {
        return ImageExtendedDTO.class;
    }
}

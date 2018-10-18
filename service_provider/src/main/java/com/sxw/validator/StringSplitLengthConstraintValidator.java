
package com.sxw.validator;

import com.sxw.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringSplitLengthConstraintValidator implements ConstraintValidator<StringSplitLength, String> {

    private static final Logger LOG = LoggerFactory.getLogger(StringSplitLengthConstraintValidator.class);
    
    private int max;
    private String separator;
    private String message;

    /**
     *  初始化
     * @param descriptionsLength
     */
    @Override
    public void initialize(StringSplitLength descriptionsLength) {
        max = descriptionsLength.max();
        separator = descriptionsLength.separator();
        message = descriptionsLength.message();
    }

    /**
     * 校验
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {

        //如果字符串为空校验不通过 :是因为如果null，则会有@NotNull进行提示
        if(StringUtils.isEmpty(value)) {
            return false;
        }
        //按照指定字符串分割大于max也校验不通过
        if(value.split(separator).length >= max){
            return false;
        }
        return true;
    }
    
    private void validateParameters() {
        if ( max < 0 ) {
            LOG.error(message);
            throw new ValidatorException("9999",message);
        }
    }
}
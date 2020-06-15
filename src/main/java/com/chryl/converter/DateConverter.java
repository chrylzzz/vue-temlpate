package com.chryl.converter;

import com.chryl.util.Date8Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * java 8 的日期类型的DateConverter
 * <p>
 * 后台Date接收前端传递的string date类型(直接Date接收String date),
 * 不影响json接收Date类型的数据,但需要在字段上加上两个注解(入参和出参时间注解),或者在.properties文件里配置
 */
@Component
public class DateConverter implements Converter<String, LocalDate> {//这里使用 java 8 的日期类型
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//    @Override
//    public Date convert(String source) {
//        try {
//            return sdf.parse(source);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String dateFormatInterStandard = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";//国际标准时间,转为东八区的时间
    private static final String shortDateFormat = "yyyy-MM-dd";

    @Override
    public LocalDate convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            if (source.contains("-")) {
                SimpleDateFormat formatter;
                if (source.contains(":")) {
                    if (source.contains("Z")) {//T表示分隔符，Z表示的是UTC。UTC：世界标准时间，在标准时间上加上8小时，即东八区时间，也就是北京时间。
                        source = source.replace("Z", " UTC");
                        formatter = new SimpleDateFormat(dateFormatInterStandard);
                    } else {

                        formatter = new SimpleDateFormat(dateFormat);
                    }
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                return Date8Util.toLocalDate(formatter.parse(source));//格林威治时间转为北京时间
            } else if (source.matches("^\\d+$")) {
                Long lDate = new Long(source);
                return Date8Util.toLocalDate(new Date(lDate));
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", source));
    }


}

package com.ymkj.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 格式化时间
 *
 * @author Xiaohao
 * @date 2019/03/02
 */
public class RelativeDateFormat {

    private static final long ONE_DAY = 3600000L * 24L;
    private static final long TWO_DAY = 3600000L * 48L;
    private static final long THREE_DAY = 3600000L * 72L;


    /**
     * 文章发表时间格式化后的字符串
     *
     * @param date '文章发表时间'
     * @return formatToString
     */
    public static String formatToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("MM月dd日HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        long delta = calendar.getTime().getTime() - date.getTime();
        if (delta < ONE_DAY) {
            return "今天" + format.format(date);
        }
        if (delta < TWO_DAY) {
            return "昨天" + format.format(date);
        }
        if (delta < THREE_DAY) {
            return "前天" + format.format(date);
        } else {
            return format1.format(date);
        }

    }

}

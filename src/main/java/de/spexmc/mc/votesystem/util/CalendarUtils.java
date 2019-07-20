package de.spexmc.mc.votesystem.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public class CalendarUtils {

  public static short getDayOfDate(Date date) {
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return (short) calendar.get(Calendar.DAY_OF_YEAR);
  }
}

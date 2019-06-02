package utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.joda.time.Interval;

public class TimeUtils {
	public static Date convertDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate convertDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static long getCommonDaysBetweenPeriods(LocalDate period1Start, LocalDate period1End, LocalDate period2Start,
			LocalDate period2End) {
		
		Interval period1 = new Interval(
				period1Start.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 
				period1End.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()); 
		
		Interval period2 = new Interval(
				period2Start.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli(), 
				period2End.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
		
		if (period1.overlaps(period2)) {
			return period1.overlap(period2).toDuration().getStandardDays();
		}
		
		return 0;
	}
}

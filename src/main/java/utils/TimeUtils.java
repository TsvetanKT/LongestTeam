package utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.joda.time.Interval;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

public class TimeUtils {
	
	private static final String TODAY_KEYWORD = "NULL";
	
	private static Parser parser;
	
	private static Parser getParser() {
		if (parser == null) {
			Logger.getRootLogger().setLevel(Level.OFF);
			parser = new Parser();
		}
		
		return parser;
	}
	
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
	
	public static LocalDate parseLocalDate(String date) {
		date = date.trim();
		if (date.equals(TODAY_KEYWORD)) {
			return LocalDate.now();
		}
		
		DateGroup group = getParser().parse(date).get(0);
		Date parsedDate = group.getDates().get(0);
		
		return convertDate(parsedDate);
	}
}

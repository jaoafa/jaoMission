package com.jaoafa.jaoMission.Lib;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//http://uuniy.hatenablog.jp/entry/2016/10/25/125149
public class DateUtil {
	/**
	 * 対象日付の同一週の指定曜日の日付(00:00:00)を取得する。
	 *
	 * @param targetDate
	 *            対象日付
	 * @param firstDayOfWeek
	 *            週の開始曜日
	 * @param dayOfWeek
	 *            取得指定曜日
	 * @return 指定曜日の日付
	 */
	public static Calendar getDayOfWeek_FIRST(Date targetDate, int firstDayOfWeek, int dayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.setTime(targetDate);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.setFirstDayOfWeek(firstDayOfWeek);
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return cal;
	}
	/**
	 * 対象日付の同一週の指定曜日の日付(23:59:59)を取得する。
	 *
	 * @param targetDate
	 *            対象日付
	 * @param firstDayOfWeek
	 *            週の開始曜日
	 * @param dayOfWeek
	 *            取得指定曜日
	 * @return 指定曜日の日付
	 */
	public static Calendar getDayOfWeek_END(Date targetDate, int firstDayOfWeek, int dayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.setTime(targetDate);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		cal.setFirstDayOfWeek(firstDayOfWeek);
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return cal;
	}

	public static Calendar getDay_FIRST(Date targetDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.setTime(targetDate);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return cal;
	}

	public static Calendar getDay_END(Date targetDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.setTime(targetDate);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return cal;
	}
}
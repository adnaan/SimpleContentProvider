package edu.mit.mobile.android.content;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AndroidVersions {
	public static final String SQLITE_VERSION = getSqliteVersion();
	private static final long SQLITE_VERSION_CODE = versionToCode(SQLITE_VERSION);

	/**
	 * if true, the installed version of SQLite supports foreign keys
	 */
	public static final boolean SQLITE_SUPPORTS_FOREIGN_KEYS = SQLITE_VERSION_CODE >= versionToCode("3.6.19");

	/**
	 * @return the version string returned by sqlite_version()
	 * @see <a href="http://stackoverflow.com/questions/2421189/version-of-sqlite-used-in-android">StackOverflow Version of SQLite used in Android?</a>
	 */
	private static final String getSqliteVersion(){
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
		final Cursor cursor = db.rawQuery("select sqlite_version() AS sqlite_version", null);
		String sqliteVersion = "";
		if(cursor.moveToNext()){
		   sqliteVersion += cursor.getString(0);
		}
		cursor.close();
		db.close();
		return sqliteVersion;
	}

	private static final String VER_STRING_DELIMITER = "\\.";

	/**
	 * Converts a version code, eg. "3.4.2" into a number that can be compared. The newer the version, the higher the resulting integer
	 * @param version a version string
	 * @return a number that can be compared against the output of this function
	 */
	private static final long versionToCode(String version){
		long vernum = 0;
		for (final String verCode : version.split(VER_STRING_DELIMITER)){
			vernum += Integer.parseInt(verCode);
			vernum *= 1000;
		}
		return vernum;
	}
}

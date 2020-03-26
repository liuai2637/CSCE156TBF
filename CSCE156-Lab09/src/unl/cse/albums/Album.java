package unl.cse.albums;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class Album {

	private Integer albumId;
	private String title;
	private Integer year;
	private Band band;
	private Integer albumNumber;
	private List<String> songTitles = new ArrayList<String>();

	public Album(Integer albumId, String title, Integer year, Band band, Integer albumNumber) {
		super();
		this.albumId = albumId;
		this.title = title;
		this.year = year;
		this.band = band;
		this.albumNumber = albumNumber;
	}

	public Album(String title, Integer year, String bandName) {
		this(null, title, year, new Band(bandName), null);
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public String getTitle() {
		return title;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getAlbumNumber() {
		return albumNumber;
	}

	public Band getBand() {
		return band;
	}

	public List<String> getSongTitles() {
		return songTitles;
	}

	public void addSong(String songTitle) {
		this.songTitles.add(songTitle);
	}

	/**
	 * This method returns a {@link #Album} instance loaded from the database
	 * corresponding to the given <code>albumId</code>. Throws an
	 * {@link IllegalStateException} upon an invalid <code>albumId</code>. All
	 * fields are loaded with this method.
	 * 
	 * @param albumId
	 * @return
	 */
	public static Album getDetailedAlbum(int albumId) {

		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		Album a = null;
		Band b = null;
		// 1. Load the JDBC Driver
		try {
			Class.forName(DRIVER_CLASS).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 2. Create a connection to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 3. Create query

		String query = "select *" + " from Album " + " where albumId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn.prepareStatement(query);
			ps.setInt(1, albumId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String title = rs.getString("title");
				int year = rs.getInt("year");
				int number = rs.getInt("number");
				int bandId = rs.getInt("bandId");
				b = Band.getBand(bandId);
				a = new Album(albumId, title, year, b, number);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query2 = "select s.title" +
				" from Album a" + 
				" join AlbumSong g" +
				" on a.albumId = g.albumId" +
				" join Song s" +
				" on s.songId = g.songId" +
				" where a.albumId = ?;";

		try {
			List<String> songtitles;
			ps = conn.prepareStatement(query2);
			ps.setInt(1, albumId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				a.addSong(title);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return a;
	}

	/**
	 * Returns a list of all albums in the database. However, this is only a summary
	 * so only the following items need to be loaded from the database:
	 * <ul>
	 * <li>Album ID</li>
	 * <li>Album Title</li>
	 * <li>Album Year</li>
	 * <li>Band ID</li>
	 * <li>Band Name</li>
	 * </ul>
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<Album> getAlbumSummaries() {

		List<Album> albums = new ArrayList<>();

		String DRIVER_CLASS = "com.mysql.jdbc.Driver";

		// 1. Load the JDBC Driver
		try {
			Class.forName(DRIVER_CLASS).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 2. Create a connection to the database
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 3. Create/Prepare query
		String query = "select" + " a.albumId, a.title, a.year, a.number, b.bandId, b.name" + " from Album a"
				+ " left join Band b" + " on a.bandId = b.bandId";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				// Get all relevant columns
				int albumId = rs.getInt("albumId");
				String title = rs.getString("title");
				int year = rs.getInt("year");
				int number = rs.getInt("number");
				int bandId = rs.getInt("bandId");
				String name = rs.getString("name");

				// Construct Band
				Band b = new Band(bandId, name);

				// Construct Album
				Album a = new Album(albumId, title, year, b, number);
				albums.add(a);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// 5. Close resources (connection)
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return albums;

	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", title=" + title + ", year=" + year + ", band=" + band + ", albumNumber="
				+ albumNumber + ", songTitles=" + songTitles + "]";
	}

}

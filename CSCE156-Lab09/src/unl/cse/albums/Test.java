package unl.cse.albums;

import java.sql.SQLException;
import java.util.List;

public class Test {

	public static void main(String args[]) {
		
		Album.getDetailedAlbum(2);
		System.out.println(Album.getDetailedAlbum(2));
		List<Album> disc;
		
			disc = Album.getAlbumSummaries();
		
			// TODO Auto-generated catch block
	

		for(Album a : disc) {
			System.out.println(a.getTitle() + " (id = "+a.getAlbumId()+") by " + a.getBand().getName() + " (id = "+a.getBand().getBandId()+"), " + a.getYear());
		}
		
	}
	
}


<%@ page import="unl.cse.albums.Band" %>
<%@ page import="unl.cse.albums.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Band Detail</title>
</head>
<body>

<% 
  int bandId = -1;
  try {
	  bandId = Integer.parseInt(request.getParameter("bandId"));
  } catch(Exception e) {
	  bandId = -1;
  }
  Band b = Band.getBand(bandId);
  
  List<Album> bandAlbums = Album.getAlbumSummaries().stream().filter(a -> a.getBand().equals(b)).sorted(Comparator.comparingInt(Album::getYear)).collect(Collectors.toList());
%>
<h1><%=b.getName()%></h1>

<h2>Members</h2>

<ul>
  <%for(String member : b.getMembers()) {%>
    <li><%=member%></li>
  <%}%>
</ul>

<hr>

<h3>Albums</h3>

<ul>
  <%for (Album album : bandAlbums) {%>
  <li><a href="albumDetail.jsp?albumId=<%=album.getAlbumId()%>"><%=album.getTitle()%> (<%=album.getYear()%>)</a>
  </li>
  <%}%>
</ul>

</body>
</html>
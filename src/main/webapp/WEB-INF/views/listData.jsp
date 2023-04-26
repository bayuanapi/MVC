<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Master Karyawan</h1>
<p>List Karyawan </p>
<p><a href="/MVC/Create">Tambah</a></p>
	<div id=divcontact >
		<table id="tableContact" border="1" >
		<tr>
			<th>ID</th><th>Nama</th><th>Tanggal Lahir</th>
			<th>Jabatan</th><th>NIP</th><th>Jenis Kelamin</th><th>Aksi</th>
		</tr>
		<c:set var = "i" value ="0"/>	
		<c:forEach var="item" items="${list}">	
		<tr>
			<td width="50px" align="right">${i+1}</td>
			<td width="100px">${item.name}</td>
			<td width="100px">${item.birth_date}</td>
			<td width="100px">${item.positionName}</td>
			<td width="100px" align="right">${item.idNumber}</td>
			<td width="100px">${item.genderDesc}</td>
			<td width="120px">
			<a href="/MVC/Edit?id=${item.id}">
			<button>Edit&nbsp;</button>
			</a>&nbsp;&nbsp;
			<a href="/MVC/Delete?id=${item.id}">
			<button onclick="clicked(event);">Delete</button></a>
			</td>
		</tr>	
		<c:set var = "i" value ="${i + 1}"/>	
		</c:forEach>	
		</table>
	</div>


</body>


<script>
function clicked(e)
{
    if(!confirm('Apakah Anda Akan Menghapus Data Ini ?'))e.preventDefault();
}
</script>
</html>
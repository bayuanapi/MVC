<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Master Karyawan</title>
</head>
<body>


<div id="test" style="width=100px; border=100px">
<form action="/MVC/Edit/Action" method="POST">
<h1>Master Karyawan</h1>
<p>Edit Data Karyawan</p>
<input type="hidden" name="id" value="${obj.id}">
<table border=0>
<tr>
	<td>Nama</td>
	<td>:</td>
	<td><input type="text" name="name" value="${obj.name}"></td>
</tr>

<tr>
	<td>Tanggal Lahir</td>
	<td>:</td>
	<td><input type="date" name="birthDate" placeholder="dd-mm-yyyy" value="${obj.birth_date}"></td>
</tr>

<tr>
	<td>Jabatan</td>
	<td>:</td>
	<td>
		<select name="position">
		<c:forEach var="position" items="${position}">
			<option value="${position.id}" value="${obj.positionId}">${position.name}</option>
		</c:forEach>
		</select>
	</td>
</tr>
<tr>
	<td>NIP</td>
	<td>:</td>
	<td><input type="number" name="nip" value="${obj.idNumber}"></td>
</tr>

<tr>
	<td>Jenis Kelamin</td>
	<td>:</td>
	<td><input type="radio" name="gender" value="1">Pria <input type="radio" name="gender" value="2">Wanita</td>
</tr>

</table>  
<button type="submit" formaction="/MVC/ListData">Kembali</button>
<button type="submit" formaction="/MVC/Edit/Action" onclick="return confirm('Apakah Anda Akan Menyimpan Data Ini ?')">Simpan</button>
 </form>
</div>

</body>
</html>
<html>
<head>
  <title>Catalog Report</title>
</head>
<body>
  <h1>Catalog Report</h1>
  <table>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Title</th>
      <th>Author</th>
      <th>Year</th>
      <th>Publishing Date</th>
    </tr>
    <#list documents as doc>
      <tr>
        <td>${doc.id}</td>
        <td>${doc.name}</td>
        <td>${doc.title!""}</td>
        <td>${doc.author!""}</td>
        <td>${doc.year!""}</td>
        <td>${doc.publishingDate!""}</td>
      </tr>
    </#list>
  </table>
</body>
</html>
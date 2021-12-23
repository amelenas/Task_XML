
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>MainPage</title>
</head>
<body>
<div align="center">
  <h3>File Upload:</h3>
  Select a file to upload: <br />
  <form action = "FileUploadHandler" method = "post" enctype = "multipart/form-data">
    xml file:
    <input type = "file" name = "xml" size = "50" accept=".xml" /> <br />
    <br/>
    <input type = "submit" value = "Upload File"/>
  </form>
</div>
</body>
</html>

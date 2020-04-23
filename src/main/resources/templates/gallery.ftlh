<!DOCTYPE HTML>
<html>
<head>
    <title>Image Gallery</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>Gallery</h1>

<#list images as image >
    <div>
        <p>${image.name}</p>
        <img src="${image.location}">
        <p>URL: ${image.location}</p>
        <a href="/gallery/${image.id}"> Single View</a>
    </div>
</#list>


</body>
</html>
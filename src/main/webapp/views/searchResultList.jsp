<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/application.css">
    <title>Marvel Character Search Results</title>
  </head>
  <body>
    <div align="center">
      <form action="/marvel/find" method='GET'>
        <input type="text" name="searchString" value="" autofocus="true" size="25"/>
        <input type="submit" value="Search">
      </form>
      <table>
        <c:forEach items="${searchResultList}" var="searchResult">
          <tr>
            <td>
              <a href="/marvel/character/${searchResult.id}">
                <img src="${searchResult.thumbnail.path}/portrait_medium.${searchResult.thumbnail.extension}">
              </a><br/>
              <a href="/marvel/character/${searchResult.id}">${searchResult.name}</a><br/>
              ${searchResult.description}
            </td>
          </tr>
        </c:forEach>
      </table>
      <div><br/>
        <form action="/marvel/find" method='GET'>
          <input type="text" name="searchString" value="" autofocus="true" size="25"/>
          <input type="submit" value="Search">
        </form>
        Data provided by Marvel. &copy; 2017 Marvel
      </div>
    </div>
  </body>
</html>
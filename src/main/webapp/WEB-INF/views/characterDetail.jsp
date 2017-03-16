<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/application.css" />
    <title>Marvel Character Details</title>
  </head>
  <body>
    <div align="center">
      <form action="/marvel/find" method='GET'>
        <input type="text" name="searchString" value="" autofocus="true" size="25"/>
        <input type="submit" value="Search">
      </form>
      <table>
        <tr>
          <td align="center" valign="top">
            <img src="${marvelCharacter.thumbnail.path}/portrait_incredible.${marvelCharacter.thumbnail.extension}">
          </td>
          <td>
            <table width="100%">
              <tr style="background-color:lightyellow"><td align="right"><b>Name:</b></td><td>${marvelCharacter.name}</td></tr>
              <tr><td align="right"><b>Real Name:</b></td><td>${marvelCharacter.realName}</td></tr>
              <tr><td align="right"><b>Aliases:</b></td><td>${marvelCharacter.aliases}</td></tr>
              <tr><td align="right"><b>Identity:</b></td><td>${marvelCharacter.identity}</td></tr>
              <tr><td align="right"><b>Citizenship:</b></td><td>${marvelCharacter.citizenship}</td></tr>
              <tr><td align="right"><b>Place of Birth:</b></td><td>${marvelCharacter.placeOfBirth}</td></tr>
              <tr><td align="right"><b>Education:</b></td><td>${marvelCharacter.education}</td></tr>
              <tr><td align="right"><b>First Appearance:</b></td><td>${marvelCharacter.firstAppearance}</td></tr>
              <tr><td align="right"><b>Relatives:</b></td><td>${marvelCharacter.relatives}</td></tr>
              <tr><td align="right"><b>Physical Attributes:</b></td><td>${marvelCharacter.physicalAttributes}</td></tr>
              <tr><td align="right"><b>Group Affiliation:</b></td><td>${marvelCharacter.groupAffiliation}</td></tr>
              <tr><td align="right"><b>Origin:</b></td><td>${marvelCharacter.origin}</td></tr>
            </table>
          </td>
        </tr>
        <tr><td align="right"><b>Description:</b></td><td>${marvelCharacter.description}</td></tr>
        <tr><td align="right"><b>Occupation:</b></td><td>${marvelCharacter.occupation}</td></tr>
        <tr><td align="right"><b>Powers:</b></td><td>${marvelCharacter.powers}</td></tr>
        <tr><td align="right"><b>Paraphernalia:</b></td><td>${marvelCharacter.paraphernalia}</td></tr>
        <tr style="background-color:lightyellow"><td align="center" colspan="2"><b>Power Ratings</b></td></tr>
        <tr align="center">
          <td colspan="2">
            <table width="30%">
              <tr style="background-color:lightyellow">
                <td align="right"><b>Intelligence</b></td><td>:</td><td>${marvelCharacter.powerRatings.get("intelligence")}</td>
                <td>|</td>
                <td align="right"><b>Durability</b></td><td>:</td><td>${marvelCharacter.powerRatings.get("durability")}
              </tr>
              <tr>
                <td align="right"><b>Strength</b></td><td>:</td><td>${marvelCharacter.powerRatings.get("strength")}</td>
                <td>|</td>
                <td align="right"><b>Speed</b></td><td>:</td><td>${marvelCharacter.powerRatings.get("speed")}</td>
              </tr>
              <tr style="background-color:lightyellow">
                <td align="right"><b>Energy</b></td><td>:</td><td>${marvelCharacter.powerRatings.get("energy")}</td>
                <td>|</td>
                <td align="right"><b>Fighting</b></td><td>:</td><td>${marvelCharacter.powerRatings.get("fighting")}</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <div><br/>
        <form action="/marvel/find" method='GET'>
          <input type="text" name="searchString" value="" autofocus="true" size="25"/>
          <input type="submit" value="Search">
        </form>
        <a href="https://github.com/embelardo/marvel-character-browser">Source code on GitHub</a><br/>
        Data provided by Marvel. &copy; 2017 Marvel
      </div>
    </div>
  </body>
</html>
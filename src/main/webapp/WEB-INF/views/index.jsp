<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="/application.css" />
    <title>Marvel Character Browser</title>
  </head>
  <body>
    <div align="center">
      <form action="/marvel/find" method='GET'>
        <input type="text" name="searchString" value="" autofocus="true" size="25"/>
        <input type="submit" value="Search">
      </form>
      <table>
        <tr>
          <td align="center">
            <a href="/marvel/character/1010801"><img src="http://i.annihil.us/u/prod/marvel/i/mg/e/20/52696868356a0/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1010801">Ant-Man</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009282"><img src="http://i.annihil.us/u/prod/marvel/i/mg/5/f0/5261a85a501fe/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009282">Doctor Strange</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009189"><img src="http://i.annihil.us/u/prod/marvel/i/mg/f/30/50fecad1f395b/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009189">Falcon</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009220"><img src="http://i.annihil.us/u/prod/marvel/i/mg/3/50/537ba56d31087/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009220">Falcon</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009297"><img src="http://i.annihil.us/u/prod/marvel/i/mg/f/b0/5111505fb7009/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009297">Falcon</a>
          </td>
        </tr>
        <tr>
          <td align="center">
            <a href="/marvel/character/1009338"><img src="http://i.annihil.us/u/prod/marvel/i/mg/e/90/50fecaf4f101b/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009338">Hawkeye</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009351"><img src="http://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009351">Hulk</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009368"><img src="http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009368">Iron Man</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1011335"><img src="http://i.annihil.us/u/prod/marvel/i/mg/a/00/535ff3f5397cb/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1011335">Maria Hill</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009524"><img src="http://i.annihil.us/u/prod/marvel/i/mg/6/f0/53176ffc42f58/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009524">Quicksilver</a>
          </td>
        </tr>
        <tr>
          <td align="center">
            <a href="/marvel/character/1009562"><img src="http://i.annihil.us/u/prod/marvel/i/mg/6/70/5261a7d7c394b/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009562">Scarlet Witch</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009610"><img src="http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009610">Spider-Man</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009664"><img src="http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009664">Thor</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009697"><img src="http://i.annihil.us/u/prod/marvel/i/mg/9/d0/5111527040594/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009697">Vision</a>
          </td>
        </tr>
        <tr>
          <td align="center">
            <a href="/marvel/character/1009407"><img src="http://i.annihil.us/u/prod/marvel/i/mg/d/90/526547f509313/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009407">Loki</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1009685"><img src="http://i.annihil.us/u/prod/marvel/i/mg/3/70/5261a838e93c0/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1009685">Ultron</a>
          </td>
          <td align="center">
            <a href="/marvel/character/1010740"><img src="http://i.annihil.us/u/prod/marvel/i/mg/d/03/5265478293c1e/portrait_medium.jpg"></a><br/>
            <a href="/marvel/character/1010740">Winter Soldier</a>
          </td>
        </tr>
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
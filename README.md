# marvel-character-web-app

A web application that allows a user to search for characters from the Marvel Universe and get detailed information about them.

### webpage

https://marvel-character-browser.herokuapp.com/

### implementation

This web application is implemented as a Spring Boot MVC project.

It acquires all its information from the [Marvel API](http://developer.marvel.com/docs). Unfortunately, most character details are buried in a wiki page. Therefore, character data is aggregated by first parsing the JSON data returned by the Marvel API, and then scraping the specified wiki page for the rest of the data.

### screenshots

- home page

![](https://github.com/embelardo/marvel-character-web-app/blob/master/homePage.png)

- character details page

![](https://github.com/embelardo/marvel-character-web-app/blob/master/characterDetail.png)

- character search results page

![](https://github.com/embelardo/marvel-character-web-app/blob/master/searchResultList.png)
